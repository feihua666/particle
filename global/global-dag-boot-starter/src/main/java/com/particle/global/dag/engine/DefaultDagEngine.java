package com.particle.global.dag.engine;

import com.particle.global.dag.exception.DAGExecutionException;
import com.particle.global.dag.exception.DAGRuntimeException;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagEdge;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.NodeOutput;
import com.particle.global.dag.model.NodePort;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.plan.DagExecutionPlan;
import com.particle.global.dag.plan.DefaultExecutionPlanner;
import com.particle.global.dag.plan.ExecutionStep;
import com.particle.global.dag.runtime.*;
import com.particle.global.dag.runtime.condition.ConditionEvaluatorManager;
import com.particle.global.dag.runtime.executor.*;
import com.particle.global.dag.runtime.executor.constantinput.HttpConfigConstantInputNodeExecutor;
import com.particle.global.dag.runtime.executor.constantinput.ImageConstantInputNodeExecutor;
import com.particle.global.dag.runtime.executor.constantinput.TextConstantInputNodeExecutor;
import com.particle.global.dag.runtime.executor.constantinput.VideoConstantInputNodeExecutor;
import com.particle.global.dag.runtime.executor.control.DelayNodeExecutor;
import com.particle.global.dag.runtime.executor.process.GroovyScriptNodeExecutor;
import com.particle.global.dag.runtime.executor.process.HttpRequestNodeExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * DAG引擎默认实现
 * <p>
 * execute() 为同步方法：阻塞调用线程，内部按拓扑分层执行节点。
 * 同层的多个节点通过线程池并行执行，层间串行。
 * 通过 DagExecutionController 扩展点支持暂停/停止等流程控制。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public class DefaultDagEngine implements DagEngine {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDagEngine.class);

    // 节点执行器注册表
    private NodeExecutorRegistry executorRegistry = new NodeExecutorRegistry();

    // 节点执行拦截器列表（可修改/中断执行流程）
    private final List<NodeExecutionInterceptor> interceptors = new CopyOnWriteArrayList<>();

    // 节点执行监听器列表（纯事件通知）
    private final List<NodeExecutionListener> listeners = new CopyOnWriteArrayList<>();

    // 有界线程池，用于并行执行同层节点
    private final ExecutorService executorService;

    // 执行控制器扩展点（由上层注入，支持暂停/停止）
    private volatile DagExecutionController executionController;

    public DefaultDagEngine() {
        this.executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                new ThreadFactory() {
                    private int count = 0;
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "dag-node-" + (count++));
                        t.setDaemon(true);
                        return t;
                    }
                }
        );
        registerDefaultNodeExecutors();
    }

    public DefaultDagEngine(ExecutorService executorService) {
        this.executorService = executorService;
        registerDefaultNodeExecutors();
    }

    /**
     * 注册默认的节点执行器
     */
    private void registerDefaultNodeExecutors() {
        // constant input
        executorRegistry.register(new TextConstantInputNodeExecutor());
        executorRegistry.register(new ImageConstantInputNodeExecutor());
        executorRegistry.register(new VideoConstantInputNodeExecutor());
        executorRegistry.register(new HttpConfigConstantInputNodeExecutor());
        // process
        executorRegistry.register(new GroovyScriptNodeExecutor());
        // control
        executorRegistry.register(new DelayNodeExecutor());
        // input
        // output
        executorRegistry.register(new HttpRequestNodeExecutor());


    }

    /**
     * 设置执行控制器（由上层编排服务注入）
     *
     * @param controller 执行控制器
     */
    public void setExecutionController(DagExecutionController controller) {
        this.executionController = controller;
    }

    // ==================== 执行 ====================

    @Override
    public ExecutionHandle execute(DagDefinition dagDefinition,
                                    ExecutionOptions options,
                                    ExecutionContext context) throws com.particle.global.dag.exception.DAGException {
        // 创建执行句柄
        DefaultExecutionHandle handle = new DefaultExecutionHandle(
                DagExecutionStatus.RUNNING, context, executionController);

        try {
            logger.info("Starting DAG execution: {}", dagDefinition.getId());

            // 1. 校验（validate 内部已包含环检测，只调一次）
            ValidationResult validationResult = validate(dagDefinition);
            if (!validationResult.isValid()) {
                throw new DAGRuntimeException("Invalid DAG definition: " + String.join(", ", validationResult.getErrors()));
            }

            // 2. 生成执行计划（拓扑分层，同层自动并行）
            DagExecutionPlan plan = new DefaultExecutionPlanner().plan(dagDefinition, context, options);

            // 3. 按计划执行
            executePlan(dagDefinition, plan, context, options);

            handle.updateStatus(DagExecutionStatus.COMPLETED);
            logger.info("DAG execution completed: {}", dagDefinition.getId());
        } catch (DAGExecutionException e) {
            logger.error("DAG execution failed: {}", dagDefinition.getId(), e);
            handle.updateStatus(DagExecutionStatus.FAILED);
            throw e;
        } catch (Exception e) {
            logger.error("DAG execution failed: {}", dagDefinition.getId(), e);
            handle.updateStatus(DagExecutionStatus.FAILED);
            throw new DAGRuntimeException("DAG execution failed", e);
        }

        return handle;
    }

    /**
     * 按执行计划逐步执行
     */
    private void executePlan(DagDefinition dagDefinition,
                              DagExecutionPlan plan,
                              ExecutionContext context,
                              ExecutionOptions options) throws DAGExecutionException {
        List<ExecutionStep> steps = plan.getSteps();
        String stopAfterNodeId = options.getStopAfterNodeId();

        for (ExecutionStep step : steps) {
            // 每个 step 执行前检查 controller（支持暂停/停止）
            if (executionController != null && !executionController.shouldContinue(context)) {
                logger.info("DAG execution interrupted at step: {}", step.getId());
                return;
            }

            List<DagNode> nodes = step.getNodes();
            if (nodes.size() == 1) {
                // 单节点：当前线程串行执行
                executeNodeIfApplicable(dagDefinition, nodes.get(0), context, options);
            } else {
                // 多节点：提交到线程池并行执行
                executeNodesInParallel(dagDefinition, nodes, context, options);
            }

            // 检查是否执行到了目标停止节点
            if (stopAfterNodeId != null && isStopTargetReached(stopAfterNodeId, nodes, context)) {
                logger.info("DAG execution stopped after target node: {}", stopAfterNodeId);
                return;
            }
        }
    }

    /**
     * 检查目标停止节点是否已执行完成
     */
    private boolean isStopTargetReached(String stopAfterNodeId, List<DagNode> nodes, ExecutionContext context) {
        for (DagNode node : nodes) {
            if (stopAfterNodeId.equals(node.getId())) {
                NodeExecution nodeExecution = context.getNodeExecution(node.getId());
                if (nodeExecution != null &&
                        (nodeExecution.getStatus() == NodeExecutionStatus.SUCCESS ||
                         nodeExecution.getStatus() == NodeExecutionStatus.SKIPPED)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 并行执行节点列表
     */
    private void executeNodesInParallel(DagDefinition dagDefinition,
                                         List<DagNode> nodes,
                                         ExecutionContext context,
                                         ExecutionOptions options) throws DAGExecutionException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (DagNode node : nodes) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    executeNodeIfApplicable(dagDefinition, node, context, options);
                } catch (Exception e) {
                    if (!options.isFaultTolerant()) {
                        throw new CompletionException(e);
                    }
                }
            }, executorService);
            futures.add(future);
        }

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } catch (CompletionException e) {
            if (e.getCause() instanceof DAGExecutionException) {
                throw (DAGExecutionException) e.getCause();
            }
            throw new DAGExecutionException("Parallel execution failed", e);
        }
    }

    /**
     * 根据条件执行单个节点
     */
    private void executeNodeIfApplicable(DagDefinition dagDefinition,
                                          DagNode node,
                                          ExecutionContext context,
                                          ExecutionOptions options) throws DAGExecutionException {
        if (shouldExecuteNode(dagDefinition, node, context)) {
            if (options.isSkipSuccessful()) {
                NodeExecution existingExecution = context.getNodeExecution(node.getId());
                if (existingExecution != null && existingExecution.getStatus() == NodeExecutionStatus.SUCCESS) {
                    logger.info("Skipping already successful node: {}", node.getId());
                    notifyNodeSkipped(node, context);
                    return;
                }
            }

            if (options.isRetryFailedOnly()) {
                NodeExecution existingExecution = context.getNodeExecution(node.getId());
                if (existingExecution != null && existingExecution.getStatus() != NodeExecutionStatus.FAILED) {
                    logger.info("Skipping node {} (not FAILED, retryFailedOnly enabled)", node.getId());
                    notifyNodeSkipped(node, context);
                    return;
                }
            }

            try {
                executeSingleNode(dagDefinition, node, context);
            } catch (DAGExecutionException e) {
                if (options.isFaultTolerant()) {
                    logger.warn("Node {} failed but continuing (fault tolerant)", node.getId(), e);
                } else {
                    throw e;
                }
            }
        } else {
            logger.info("Skipping node {} (unmet conditions)", node.getId());
            NodeExecution nodeExecution = new NodeExecution(node.getId());
            nodeExecution.markSkipped();
            context.setNodeExecution(node.getId(), nodeExecution);
            context.setVariable(ExecutionContextConstants.getNodeSkippedVariableName(node.getId()), true);
            notifyNodeSkipped(node, context);
        }
    }

    /**
     * 执行单个节点（端口模式）
     */
    private void executeSingleNode(DagDefinition dagDefinition, DagNode node, ExecutionContext context) throws DAGExecutionException {
        NodeExecution nodeExecution = new NodeExecution(node.getId());
        nodeExecution.markRunning();
        context.setNodeExecution(node.getId(), nodeExecution);

        try {
            logger.info("Executing node: {}", node.getId());

            // === 执行前：valuePorts 注入 context ===
            Map<String, NodePort> valuePorts = node.getValuePorts();
            if (valuePorts != null) {
                valuePorts.values().forEach(vp -> {
                    String key = node.getId() + "." + vp.getName();
                    context.setVariable(key, vp.getData());
                    logger.debug("Injected valuePort: {} = {}", key, vp.getData());
                });
            }

            // === 执行前：根据 incoming edges 组装 inputMap（端口路由） ===
            Map<String, NodePort> inputMap = new HashMap<>();
            List<DagEdge> allEdges = dagDefinition.getEdges();
            if (allEdges != null) {
                for (DagEdge edge : allEdges) {
                    if (node.getId().equals(edge.getToNodeId())) {
                        String sourceKey = edge.getFromNodeId() + "." + edge.getFromPort();
                        Object data = context.getVariable(sourceKey);
                        if (data != null && edge.getToPort() != null) {
                            inputMap.put(edge.getToPort(), new NodePort(edge.getToPort(),null, null, data));
                            logger.debug("Routed port: {} -> {}.{} = {}", edge.getFromNodeId(), edge.getToPort(), node.getId(), data);
                        }
                    }
                }
            }

            // === [Interceptor] beforeNode ===
            for (NodeExecutionInterceptor interceptor : interceptors) {
                interceptor.beforeNode(node, context, inputMap);
            }
            // === [Listener] onNodeStarted ===
            for (NodeExecutionListener listener : listeners) {
                listener.onNodeStarted(node, context);
            }

            // === 执行节点 ===
            NodeExecutor executor = executorRegistry.getExecutor(node);
            NodeExecutionResult result = executor.execute(node, context, inputMap);

            if (result.isSuccess()) {
                NodeOutput output = result.getOutput();
                nodeExecution.markSuccess(output);

                for (NodeExecutionInterceptor interceptor : interceptors) {
                    interceptor.afterNodeSuccess(node, context, output);
                }
                for (NodeExecutionListener listener : listeners) {
                    listener.onNodeSuccess(node, context, output);
                }

                // 按端口逐个存入 context
                if (output != null && output.getPorts() != null) {
                    for (Map.Entry<String, NodePort> port : output.getPorts().entrySet()) {
                        String portKey = node.getId() + "." + port.getKey();
                        context.setVariable(portKey, port.getValue().getData());
                    }
                }
                context.setVariable(ExecutionContextConstants.getNodeExecutedVariableName(node.getId()), true);

                logger.info("Node {} executed successfully", node.getId());
            } else {
                Throwable error = result.getError();
                nodeExecution.markFailed(error);

                boolean rethrow = true;
                for (NodeExecutionInterceptor interceptor : interceptors) {
                    if (!interceptor.afterNodeFailed(node, context, error)) {
                        rethrow = false;
                    }
                }
                for (NodeExecutionListener listener : listeners) {
                    listener.onNodeFailed(node, context, error);
                }

                logger.error("Node {} execution failed", node.getId(), error);
                if (rethrow) {
                    throw new DAGExecutionException("Node execution failed: " + error.getMessage(), error);
                }
            }
        } catch (DAGExecutionException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Node {} execution failed", node.getId(), e);

            boolean rethrow = true;
            for (NodeExecutionInterceptor interceptor : interceptors) {
                if (!interceptor.afterNodeFailed(node, context, e)) {
                    rethrow = false;
                }
            }
            for (NodeExecutionListener listener : listeners) {
                listener.onNodeFailed(node, context, e);
            }

            nodeExecution.markFailed(e);
            if (rethrow) {
                throw new DAGExecutionException("Node execution failed: " + node.getId(), e);
            }
        }
    }

    /**
     * 判断节点是否应该执行（检查传入边的条件）
     */
    private boolean shouldExecuteNode(DagDefinition dagDefinition, DagNode node, ExecutionContext context) {
        if (dagDefinition.getEdges() == null) {
            return true;
        }

        List<DagEdge> incomingEdges = new ArrayList<>();
        for (DagEdge edge : dagDefinition.getEdges()) {
            if (node.getId().equals(edge.getToNodeId())) {
                incomingEdges.add(edge);
            }
        }

        if (incomingEdges.isEmpty()) {
            return true;
        }

        boolean hasConditionalEdge = false;
        boolean anyConditionMet = false;

        for (DagEdge edge : incomingEdges) {
            if (edge.getCondition() != null && !edge.getCondition().trim().isEmpty()) {
                hasConditionalEdge = true;
                boolean conditionMet = evaluateCondition(edge.getCondition(), context);

                if (conditionMet) {
                    anyConditionMet = true;
                    logger.debug("Edge condition '{}' met for node {}: {} -> {}",
                            edge.getCondition(), node.getId(), edge.getFromNodeId(), edge.getToNodeId());
                } else {
                    logger.debug("Edge condition '{}' not met for node {}: {} -> {}",
                            edge.getCondition(), node.getId(), edge.getFromNodeId(), edge.getToNodeId());
                }
            }
        }

        if (hasConditionalEdge) {
            return anyConditionMet;
        } else {
            return true;
        }
    }

    /**
     * 评估条件表达式
     */
    private boolean evaluateCondition(String condition, ExecutionContext context) {
        if (condition == null || condition.trim().isEmpty()) {
            return true;
        }

        try {
            return ConditionEvaluatorManager.evaluate(condition, context);
        } catch (Exception e) {
            logger.error("Failed to evaluate condition: {}", condition, e);
            return false;
        }
    }

    // ==================== 拦截器/监听器管理 ====================

    @Override
    public void addInterceptor(NodeExecutionInterceptor interceptor) {
        if (interceptor != null) {
            this.interceptors.add(interceptor);
        }
    }

    /**
     * 移除节点执行拦截器
     */
    public void removeInterceptor(NodeExecutionInterceptor interceptor) {
        this.interceptors.remove(interceptor);
    }

    /**
     * 获取所有拦截器
     */
    public List<NodeExecutionInterceptor> getInterceptors() {
        return new ArrayList<>(interceptors);
    }

    @Override
    public void addListener(NodeExecutionListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    /**
     * 移除节点执行监听器
     */
    public void removeListener(NodeExecutionListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * 获取所有监听器
     */
    public List<NodeExecutionListener> getListeners() {
        return new ArrayList<>(listeners);
    }

    // ==================== 校验 ====================

    @Override
    public NodeExecutorRegistry getNodeExecutorRegistry() {
        return executorRegistry;
    }

    @Override
    public ValidationResult validate(DagDefinition dagDefinition) {
        List<String> errors = new ArrayList<>();

        if (dagDefinition == null) {
            errors.add("DAG definition is null");
            return ValidationResult.invalid(errors);
        }

        if (dagDefinition.getNodes() == null || dagDefinition.getNodes().isEmpty()) {
            errors.add("DAG definition has no nodes");
            return ValidationResult.invalid(errors);
        }

        // 检查节点ID唯一性
        Set<String> nodeIds = new HashSet<>();
        for (DagNode node : dagDefinition.getNodes()) {
            if (node.getId() == null || node.getId().trim().isEmpty()) {
                errors.add("Node has null or empty ID");
            } else if (nodeIds.contains(node.getId())) {
                errors.add("Duplicate node ID: " + node.getId());
            } else {
                nodeIds.add(node.getId());
            }
        }

        // 检查边的有效性
        if (dagDefinition.getEdges() != null) {
            for (DagEdge edge : dagDefinition.getEdges()) {
                if (edge.getFromNodeId() == null || edge.getToNodeId() == null) {
                    errors.add("Edge has null from or to node ID");
                } else {
                    if (!nodeIds.contains(edge.getFromNodeId())) {
                        errors.add("Edge has non-existent source node: " + edge.getFromNodeId());
                    }
                    if (!nodeIds.contains(edge.getToNodeId())) {
                        errors.add("Edge has non-existent target node: " + edge.getToNodeId());
                    }
                    if (edge.getFromNodeId().equals(edge.getToNodeId())) {
                        errors.add("Edge cannot connect a node to itself: " + edge.getFromNodeId());
                    }
                }
            }
        }

        // 检测循环依赖
        try {
            if (hasCycle(dagDefinition)) {
                errors.add("Cycle detected in DAG");
            }
        } catch (Exception e) {
            errors.add("Error detecting cycles in DAG: " + e.getMessage());
        }

        if (!errors.isEmpty()) {
            return ValidationResult.invalid(errors);
        }

        return ValidationResult.valid();
    }

    /**
     * 检测DAG中是否存在循环依赖
     */
    private boolean hasCycle(DagDefinition dagDefinition) {
        if (dagDefinition.getNodes() == null) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (DagNode node : dagDefinition.getNodes()) {
            if (!visited.contains(node.getId())) {
                if (hasCycleUtil(node.getId(), dagDefinition, visited, recursionStack)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycleUtil(String nodeId, DagDefinition dagDefinition,
                                  Set<String> visited, Set<String> recursionStack) {
        visited.add(nodeId);
        recursionStack.add(nodeId);

        List<DagNode> successors = dagDefinition.getSuccessorNodes(nodeId);
        for (DagNode successor : successors) {
            if (!visited.contains(successor.getId())) {
                if (hasCycleUtil(successor.getId(), dagDefinition, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(successor.getId())) {
                return true;
            }
        }

        recursionStack.remove(nodeId);
        return false;
    }

    /**
     * 通知所有监听器节点被跳过
     */
    private void notifyNodeSkipped(DagNode node, ExecutionContext context) {
        for (NodeExecutionListener listener : listeners) {
            listener.onNodeSkipped(node, context);
        }
    }
}
