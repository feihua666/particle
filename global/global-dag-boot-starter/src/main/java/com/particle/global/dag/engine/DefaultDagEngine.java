package com.particle.global.dag.engine;

import com.particle.global.dag.exception.CycleDetectedException;
import com.particle.global.dag.exception.DAGExecutionException;
import com.particle.global.dag.exception.DAGRuntimeException;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagEdge;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.plan.*;
import com.particle.global.dag.runtime.*;
import com.particle.global.dag.runtime.condition.ConditionEvaluatorManager;
import com.particle.global.dag.runtime.executor.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * <p>
 * DAG引擎默认实现
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class DefaultDagEngine implements DagEngine {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDagEngine.class);

    // 节点执行器注册表
    private NodeExecutorRegistry executorRegistry = new NodeExecutorRegistry();


    // 线程池用于并行执行节点
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public DefaultDagEngine() {
        // 注册默认的节点执行器
        registerDefaultNodeExecutors();
    }

    public DefaultDagEngine(ExecutorService executorService) {
        this.executorService = executorService;
        // 注册默认的节点执行器
        registerDefaultNodeExecutors();
    }

    /**
     * 注册默认的节点执行器
     */
    private void registerDefaultNodeExecutors() {
        executorRegistry.register(new HttpRequestNodeExecutor());
        executorRegistry.register(new GroovyScriptNodeExecutor());
        executorRegistry.register(new DelayNodeExecutor());
        executorRegistry.register(new DataProcessNodeExecutor());
        // DatabaseNodeExecutor is not registered by default to avoid requiring database dependencies
        // It should be registered manually when database functionality is needed
    }

    @Override
    public DefaultExecutionHandle execute(DagDefinition dagDefinition, ExecutionContext context) {
        return execute(dagDefinition, ExecutionOptions.full(), context);
    }

    @Override
    public DefaultExecutionHandle execute(DagDefinition dagDefinition, ExecutionOptions options, ExecutionContext context) {
        String executionId = UUID.randomUUID().toString();
        context.setCurrentExecutionId(executionId);

        // 创建执行句柄，直接使用 ExecutionContext
        DefaultExecutionHandle handle = new DefaultExecutionHandle(executionId, DagExecutionStatus.RUNNING, context);

        // 在单独的线程中执行DAG
        CompletableFuture.runAsync(() -> {
            try {
                logger.info("Starting DAG execution with ID: {}", executionId);

                // 验证DAG定义
                ValidationResult validationResult = validate(dagDefinition);
                if (!validationResult.isValid()) {
                    throw new DAGRuntimeException("Invalid DAG definition: " + String.join(", ", validationResult.getErrors()));
                }

                // 检测循环依赖
                if (hasCycle(dagDefinition)) {
                    throw new CycleDetectedException("Cycle detected in DAG: " + dagDefinition.getId());
                }

                // 1. 使用新的规划系统生成执行计划
                ExecutionPlanner planner = new DefaultExecutionPlanner();
                DagExecutionPlan plan = planner.plan(dagDefinition, context, options);

                // 2. 根据执行计划执行节点
                executeNodesAccordingToPlan(dagDefinition, plan, context, options);

                handle.updateStatus(DagExecutionStatus.COMPLETED);
                logger.info("DAG execution completed with ID: {}", executionId);
            } catch (Exception e) {
                logger.error("DAG execution failed with ID: {}", executionId, e);
                handle.updateStatus(DagExecutionStatus.FAILED);
            }
        }, executorService);

        return handle;
    }

    /**
     * 同步执行DAG并返回执行结果
     * @param dagDefinition DAG定义
     * @param context 执行上下文
     * @return 执行结果
     */
    public ExecutionResult executeAndWait(DagDefinition dagDefinition, ExecutionContext context) {
        return executeAndWait(dagDefinition, ExecutionOptions.full(), context);
    }

    /**
     * 同步执行DAG并返回执行结果
     * @param dagDefinition DAG定义
     * @param options 执行选项
     * @param context 执行上下文
     * @return 执行结果
     */
    public ExecutionResult executeAndWait(DagDefinition dagDefinition, ExecutionOptions options, ExecutionContext context) {
        String executionId = UUID.randomUUID().toString();
        context.setCurrentExecutionId(executionId);

        // 创建执行句柄，直接使用 ExecutionContext
        DefaultExecutionHandle handle = new DefaultExecutionHandle(executionId, DagExecutionStatus.RUNNING, context);

        try {
            logger.info("Starting DAG execution with ID: {}", executionId);

            // 验证DAG定义
            ValidationResult validationResult = validate(dagDefinition);
            if (!validationResult.isValid()) {
                throw new DAGRuntimeException("Invalid DAG definition: " + String.join(", ", validationResult.getErrors()));
            }

            // 检测循环依赖
            if (hasCycle(dagDefinition)) {
                throw new CycleDetectedException("Cycle detected in DAG: " + dagDefinition.getId());
            }

            // 1. 使用新的规划系统生成执行计划
            ExecutionPlanner planner = new DefaultExecutionPlanner();
            DagExecutionPlan plan = planner.plan(dagDefinition, context, options);

            // 2. 根据执行计划执行节点
            executeNodesAccordingToPlan(dagDefinition, plan, context, options);

            handle.updateStatus(DagExecutionStatus.COMPLETED);
            logger.info("DAG execution completed with ID: {}", executionId);
        } catch (Exception e) {
            logger.error("DAG execution failed with ID: {}", executionId, e);
            handle.updateStatus(DagExecutionStatus.FAILED);
        }

        // 等待执行完成
        long startTime = System.currentTimeMillis();
        while (!handle.getStatus().isFinal() && (System.currentTimeMillis() - startTime) < 30000) { // 30秒超时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // 创建并返回执行结果
        return ExecutionResult.fromHandle(handle);
    }


    /**
     * 根据执行计划执行节点
     */
    private void executeNodesAccordingToPlan(DagDefinition dagDefinition,
                                          DagExecutionPlan plan,
                                          ExecutionContext context,
                                          ExecutionOptions options) throws DAGExecutionException {
        List<ExecutionStep> steps = plan.getSteps();

        // 按步骤顺序执行
        for (ExecutionStep step : steps) {
            executeStep(dagDefinition, step, context, options);
        }
    }

    /**
     * 执行单个步骤
     */
    private void executeStep(DagDefinition dagDefinition,
                           ExecutionStep step,
                           ExecutionContext context,
                           ExecutionOptions options) throws DAGExecutionException {
        List<DagNode> nodes = step.getNodes();

        if (step.getExecutionMode() == ExecutionMode.PARALLEL) {
            // 并行执行步骤中的节点
            executeNodesInParallel(dagDefinition, nodes, context, options, step.getParallelism());
        } else {
            // 串行执行步骤中的节点
            for (DagNode node : nodes) {
                executeNodeIfApplicable(dagDefinition, node, context, options);
            }
        }
    }

    /**
     * 并行执行节点列表
     */
    private void executeNodesInParallel(DagDefinition dagDefinition,
                                     List<DagNode> nodes,
                                     ExecutionContext context,
                                     ExecutionOptions options,
                                     int parallelism) throws DAGExecutionException {
        // 使用信号量控制并发数
        Semaphore semaphore = new Semaphore(parallelism);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (DagNode node : nodes) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    semaphore.acquire();
                    try {
                        executeNodeIfApplicable(dagDefinition, node, context, options);
                    } finally {
                        semaphore.release();
                    }
                } catch (Exception e) {
                    if (!options.isFaultTolerant()) {
                        throw new CompletionException(e);
                    }
                }
            }, executorService);
            futures.add(future);
        }

        // 等待所有任务完成
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
        // Check if this node should be executed based on incoming edge conditions
        if (shouldExecuteNode(dagDefinition, node, context)) {
            // 检查是否需要跳过已成功的节点
            if (options.isSkipSuccessful()) {
                NodeExecution existingExecution = context.getNodeExecution(node.getId());
                if (existingExecution != null && existingExecution.getStatus() == NodeExecutionStatus.SUCCESS) {
                    logger.info("Skipping already successful node: {}", node.getId());
                    return; // 跳过已成功的节点
                }
            }

            // 检查是否只重试失败的节点
            if (options.isRetryFailedOnly()) {
                NodeExecution existingExecution = context.getNodeExecution(node.getId());
                if (existingExecution != null && existingExecution.getStatus() != NodeExecutionStatus.FAILED) {
                    logger.info("Skipping node {} as it is not in FAILED state and retryFailedOnly is enabled", node.getId());
                    return; // 只重试失败的节点
                }
            }

            try {
                // Execute the node
                executeSingleNode(node, context);
            } catch (DAGExecutionException e) {
                // 如果启用了容错执行，则记录错误但不停止整个DAG
                if (options.isFaultTolerant()) {
                    logger.warn("Node {} failed but continuing execution due to fault tolerance setting", node.getId(), e);
                    // 节点执行失败但DAG继续执行
                } else {
                    // 如果没有启用容错，则重新抛出异常
                    throw e;
                }
            }
        } else {
            logger.info("Skipping node {} due to unmet incoming edge conditions", node.getId());
            // Mark node as skipped in the execution context
            NodeExecution nodeExecution = new NodeExecution(node.getId());
            nodeExecution.markSkipped(); // 使用专门的跳过状态而不是失败状态
            context.setNodeExecution(node.getId(), nodeExecution);
            context.setVariable(ExecutionContextConstants.getNodeSkippedVariableName(node.getId()), true);
        }
    }

    /**
     * 执行单个节点
     */
    private void executeSingleNode(DagNode node, ExecutionContext context) throws DAGExecutionException {
        NodeExecution nodeExecution = new NodeExecution(node.getId());
        nodeExecution.markRunning();

        // Store the node execution in the context
        context.setNodeExecution(node.getId(), nodeExecution);

        try {
            logger.info("Executing node: {}", node.getId());

            // Get the appropriate executor and execute the node
            NodeExecutor executor = executorRegistry.getExecutor(node);
            NodeExecutionResult result = executor.execute(node, context);

            if (result.isSuccess()) {
                nodeExecution.markSuccess(result.getOutput());
                // Store node execution result in context if needed
                context.setVariable(ExecutionContextConstants.getNodeOutputVariableName(node.getId()), result.getOutput());
                context.setVariable(ExecutionContextConstants.getNodeExecutedVariableName(node.getId()), true);
                // Also store the node result so it can be referenced by condition expressions
                context.setVariable(ExecutionContextConstants.getNodeResultVariableName(node.getId()), result.getOutput());

                logger.info("Node {} executed successfully", node.getId());
            } else {
                nodeExecution.markFailed(result.getError());
                logger.error("Node {} execution failed", node.getId(), result.getError());
                throw new DAGExecutionException("Node execution failed: " + result.getError().getMessage(), result.getError());
            }
        } catch (Exception e) {
            logger.error("Node {} execution failed", node.getId(), e);
            // Mark the node execution as failed with the caught exception
            nodeExecution.markFailed(e);
            // 包装原始异常为DAG执行异常，避免暴露底层实现细节
            throw new DAGExecutionException("Node execution failed: " + node.getId(), e);
        }
    }

    /**
     * 判断节点是否应该执行（检查传入边的条件）
     * @param dagDefinition DAG定义
     * @param node 节点
     * @param context 执行上下文
     * @return 节点是否应该执行
     */
    private boolean shouldExecuteNode(DagDefinition dagDefinition, DagNode node, ExecutionContext context) {
        if (dagDefinition.getEdges() == null) {
            return true; // If no edges, just execute the node
        }

        // Find all incoming edges to this node
        List<DagEdge> incomingEdges = new ArrayList<>();
        for (DagEdge edge : dagDefinition.getEdges()) {
            if (node.getId().equals(edge.getToNodeId())) {
                incomingEdges.add(edge);
            }
        }

        // If no incoming edges, execute the node
        if (incomingEdges.isEmpty()) {
            return true;
        }

        // Check if any incoming conditional edge allows execution
        // For a node to execute, at least one of its incoming conditional edges must have been satisfied
        boolean hasConditionalEdge = false;
        boolean anyConditionMet = false;

        for (DagEdge edge : incomingEdges) {
            if (edge.getCondition() != null && !edge.getCondition().trim().isEmpty()) {
                hasConditionalEdge = true;
                boolean conditionMet = evaluateCondition(edge.getCondition(), context);

                if (conditionMet) {
                    anyConditionMet = true;
                    logger.debug("Incoming edge condition '{}' met for node {}: {} -> {}",
                               edge.getCondition(), node.getId(), edge.getFromNodeId(), edge.getToNodeId());
                } else {
                    logger.debug("Incoming edge condition '{}' not met for node {}: {} -> {}",
                               edge.getCondition(), node.getId(), edge.getFromNodeId(), edge.getToNodeId());
                }
            }
        }

        // If there are conditional edges, execute the node if any condition is met
        // If there are no conditional edges, just execute the node
        if (hasConditionalEdge) {
            return anyConditionMet;
        } else {
            return true;
        }
    }

    /**
     * 评估条件表达式
     * @param condition 条件表达式
     * @param context 执行上下文
     * @return 条件是否满足
     */
    private boolean evaluateCondition(String condition, ExecutionContext context) {
        if (condition == null || condition.trim().isEmpty()) {
            return true; // Empty condition is always true
        }

        try {
            // 使用条件评估器管理器来评估条件
            return ConditionEvaluatorManager.evaluate(condition, context);
        } catch (Exception e) {
            logger.error("Failed to evaluate condition: {}", condition, e);
            // 如果条件评估失败，我们默认不执行该分支，以避免因条件评估错误导致意外执行
            return false;
        }
    }


    @Override
    public NodeExecutorRegistry getNodeExecutorRegistry() {
        return executorRegistry;
    }

    @Override
    public void setExecutorService(ExecutorService executorService) {
        if (executorService != null) {
            // Properly shut down the old executor service if it's the default one
            if (this.executorService != null && this.executorService instanceof java.util.concurrent.ThreadPoolExecutor) {
                this.executorService.shutdown();
            }
            this.executorService = executorService;
        }
    }

    @Override
    public ExecutionHandle getExecutionHandle(String executionId) {
        // Since we no longer maintain a registry of execution handles,
        // this method returns null to indicate that handles cannot be retrieved by ID
        // The caller must retain the ExecutionHandle returned from execute() method
        return null;
    }

    @Override
    public ValidationResult validate(DagDefinition dagDefinition) {
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();

        if (dagDefinition == null) {
            errors.add("DAG definition is null");
            return ValidationResult.invalid(errors);
        }

        if (dagDefinition.getNodes() == null || dagDefinition.getNodes().isEmpty()) {
            errors.add("DAG definition has no nodes");
            return ValidationResult.invalid(errors);
        }

        // 检查节点ID是否唯一
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
     * @param dagDefinition DAG定义
     * @return 是否存在循环依赖
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

    /**
     * 辅助方法：检测循环依赖
     * @param nodeId 节点ID
     * @param dagDefinition DAG定义
     * @param visited 已访问节点集合
     * @param recursionStack 递归栈
     * @return 是否存在循环依赖
     */
    private boolean hasCycleUtil(String nodeId, DagDefinition dagDefinition, Set<String> visited, Set<String> recursionStack) {
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

}
