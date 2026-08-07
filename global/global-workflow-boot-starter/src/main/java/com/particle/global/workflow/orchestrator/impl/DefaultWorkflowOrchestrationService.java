package com.particle.global.workflow.orchestrator.impl;

import com.particle.global.dag.engine.DagExecutionController;
import com.particle.global.dag.engine.DagEngine;
import com.particle.global.dag.engine.DefaultDagEngine;
import com.particle.global.dag.engine.ExecutionHandle;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.NodeOutput;
import com.particle.global.dag.model.NodePort;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.executor.NodeExecutionInterceptor;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.workflow.command.WorkflowExecuteCommand;
import com.particle.global.workflow.command.WorkflowExecuteFromNodeCommand;
import com.particle.global.workflow.command.WorkflowExecuteUpToNodeCommand;
import com.particle.global.workflow.command.WorkflowExecutionControlCommand;
import com.particle.global.workflow.dto.ExecutionDTO;
import com.particle.global.workflow.dto.NodeExecutionDTO;
import com.particle.global.workflow.enums.WorkflowExecutionStatus;
import com.particle.global.workflow.event.WorkflowExecutionEvent;
import com.particle.global.workflow.listener.WorkflowExecutionListener;
import com.particle.global.workflow.orchestrator.WorkflowOrchestrationService;
import com.particle.global.workflow.converter.WorkflowGraphConverter;
import com.particle.global.workflow.dto.WorkflowGraphDTO;
import com.particle.global.workflow.repository.WorkflowExecutionRepository;
import com.particle.global.workflow.vo.ExecutionDetailVO;
import com.particle.global.workflow.vo.ExecutionVO;
import com.particle.global.workflow.vo.NodeExecutionDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 工作流编排服务默认实现
 * <p>
 * 串联 dag 执行 + 持久化（通过 WorkflowExecutionRepository）+ 事件监听。
 * 所有执行接口统一返回 executionId（ExecutionVO），通过 sync 参数控制同步/异步。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public class DefaultWorkflowOrchestrationService implements WorkflowOrchestrationService {

    private static final Logger log = LoggerFactory.getLogger(DefaultWorkflowOrchestrationService.class);

    private final DagEngine dagEngine;
    private final WorkflowExecutionRepository repository;
    private final WorkflowGraphConverter graphConverter;
    private final List<WorkflowExecutionListener> listeners = new ArrayList<>();
    private final ExecutorService asyncExecutor;

    public DefaultWorkflowOrchestrationService(DagEngine dagEngine,
                                               WorkflowExecutionRepository repository,
                                               WorkflowGraphConverter graphConverter) {
        this.dagEngine = dagEngine;
        this.repository = repository;
        this.graphConverter = graphConverter != null ? graphConverter : new com.particle.global.workflow.converter.DefaultWorkflowGraphConverter();
        this.asyncExecutor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r, "workflow-async-" + r.hashCode());
            t.setDaemon(true);
            return t;
        });

        // 注入 DagExecutionController 和拦截器
        if (dagEngine instanceof DefaultDagEngine) {
            DefaultDagEngine defaultDagEngine = (DefaultDagEngine) dagEngine;
            defaultDagEngine.setExecutionController(new WorkflowDagExecutionController());
            defaultDagEngine.addInterceptor(new NodeCallbackInterceptor());
        }
    }

    /**
     * 添加事件监听器
     */
    public void addListener(WorkflowExecutionListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    // ==================== 完整执行 ====================

    @Override
    public SingleResponse<ExecutionVO> execute(WorkflowExecuteCommand command) {
        Long executionId = repository.createExecution(
                command.getDefinitionId(), command.getHistoryId(),
                command.getTriggerType(), null);

        if (Boolean.TRUE.equals(command.getSync())) {
            executeInternal(executionId,
                    command.getDefinitionId(), command.getHistoryId(),
                    command.getInputVariables());
        } else {
            CompletableFuture.runAsync(() -> {
                try {
                    executeInternal(executionId,
                            command.getDefinitionId(), command.getHistoryId(),
                            command.getInputVariables());
                } catch (Exception e) {
                    log.error("异步执行失败: executionId={}", executionId, e);
                }
            }, asyncExecutor);
        }
        return SingleResponse.of(buildExecutionVO(executionId));
    }

    // ==================== 运行到此节点 ====================

    @Override
    public SingleResponse<ExecutionVO> executeUpToNode(WorkflowExecuteUpToNodeCommand command) {
        Long executionId = repository.createExecution(
                command.getDefinitionId(), command.getHistoryId(),
                command.getTriggerType(), command.getCopiedExecutionId());

        if (Boolean.TRUE.equals(command.getSync())) {
            executeUpToNodeInternal(executionId,
                    command.getDefinitionId(), command.getHistoryId(),
                    command.getInputVariables(),
                    command.getEndNodeId());
        } else {
            CompletableFuture.runAsync(() -> {
                try {
                    executeUpToNodeInternal(executionId,
                            command.getDefinitionId(), command.getHistoryId(),
                            command.getInputVariables(),
                            command.getEndNodeId());
                } catch (Exception e) {
                    log.error("异步运行到此节点失败: executionId={}", executionId, e);
                }
            }, asyncExecutor);
        }
        return SingleResponse.of(buildExecutionVO(executionId));
    }

    // ==================== 从此节点运行 ====================

    @Override
    public SingleResponse<ExecutionVO> executeFromNode(WorkflowExecuteFromNodeCommand command) {
        // 1. 创建新执行实例（copiedExecutionId 传入，由 Repository 层负责拷贝数据）
        Long newExecutionId = repository.createExecution(
                command.getDefinitionId(), command.getHistoryId(),
                command.getTriggerType(), command.getCopiedExecutionId());

        // 2. 构建 context（从来源实例拷贝 + 合并新输入变量）
        ExecutionContext context = new ExecutionContext();
        if (command.getCopiedExecutionId() != null) {
            ExecutionDTO sourceExecution = repository.getExecution(command.getCopiedExecutionId());
            if (sourceExecution != null && sourceExecution.getContext() != null) {
                sourceExecution.getContext().forEach(context::setVariable);
            }
        }
        if (command.getInputVariables() != null) {
            command.getInputVariables().forEach(context::setVariable);
        }

        // 3. 加载 graphData 并转换为 DagDefinition
        WorkflowGraphDTO graphDTO = repository.getWorkflowGraph(
                command.getDefinitionId(), command.getHistoryId());
        DagDefinition dagDefinition = graphConverter.convert(graphDTO);

        context.setAttribute("executionId", newExecutionId);
        context.setAttribute("definitionId", command.getDefinitionId());

        repository.updateExecutionStatus(newExecutionId, WorkflowExecutionStatus.RUNNING.name());
        publishEvent(WorkflowExecutionEvent.create(WorkflowExecutionEvent.Type.EXECUTION_STARTED,
                        newExecutionId,
                        command.getDefinitionId(),
                        LocalDateTime.now(),
                        null,
                        null
                ));

        // 4. 构建执行选项
        ExecutionOptions options;
        if (command.getEndNodeId() != null) {
            options = ExecutionOptions.fromTo(command.getStartNodeId(), command.getEndNodeId());
        } else {
            options = ExecutionOptions.partial(command.getStartNodeId());
        }

        // 5. 同步或异步执行
        if (Boolean.TRUE.equals(command.getSync())) {
            try {
                dagEngine.execute(dagDefinition, options, context);
                handleExecutionResult(newExecutionId, context, WorkflowExecutionStatus.COMPLETED.name());
            } catch (Exception e) {
                handleExecutionResult(newExecutionId, context, WorkflowExecutionStatus.FAILED.name());
                log.error("从此节点运行失败: executionId={}", newExecutionId, e);
            }
        } else {
            CompletableFuture.runAsync(() -> {
                try {
                    dagEngine.execute(dagDefinition, options, context);
                    handleExecutionResult(newExecutionId, context, WorkflowExecutionStatus.COMPLETED.name());
                } catch (Exception e) {
                    handleExecutionResult(newExecutionId, context, WorkflowExecutionStatus.FAILED.name());
                    log.error("从此节点运行失败: executionId={}", newExecutionId, e);
                }
            }, asyncExecutor);
        }
        return SingleResponse.of(buildExecutionVO(newExecutionId));
    }

    // ==================== 执行控制 ====================

    @Override
    public Response pause(WorkflowExecutionControlCommand command) {
        repository.updateExecutionStatus(command.getExecutionId(), WorkflowExecutionStatus.PAUSED.name());
        publishEvent(WorkflowExecutionEvent.create(WorkflowExecutionEvent.Type.EXECUTION_PAUSED,
                command.getExecutionId(),
                        null,
                        LocalDateTime.now(),
                null,
                null
                ));
        return Response.buildSuccess();
    }

    @Override
    public Response resume(WorkflowExecutionControlCommand command) {
        Long executionId = command.getExecutionId();

        // 1. 从 Repository 加载执行记录（含 context）
        ExecutionDTO executionDTO = repository.getExecution(executionId);
        if (executionDTO == null) {
            return Response.buildFailure(null, "无法恢复执行：执行记录不存在");
        }

        // 2. 构建 context
        ExecutionContext context = new ExecutionContext();
        if (executionDTO.getContext() != null) {
            executionDTO.getContext().forEach(context::setVariable);
        }

        // 3. 加载 graphData 并转换为 DagDefinition
        WorkflowGraphDTO graphDTO = repository.getWorkflowGraph(executionDTO.getDefinitionId(), null);
        DagDefinition dagDefinition = graphConverter.convert(graphDTO);

        context.setAttribute("executionId", executionId);
        context.setAttribute("definitionId", executionDTO.getDefinitionId());

        repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.RUNNING.name());

        // 异步恢复执行（不阻塞 resume 调用）
        CompletableFuture.runAsync(() -> {
            try {
                ExecutionHandle handle = dagEngine.execute(dagDefinition, context);
                String finalStatus = handle.getStatus() == com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED
                        ? WorkflowExecutionStatus.COMPLETED.name()
                        : WorkflowExecutionStatus.FAILED.name();
                repository.updateExecutionContext(executionId, context.getVariables());
                repository.updateExecutionStatus(executionId, finalStatus);
                publishEvent(WorkflowExecutionEvent.Type.EXECUTION_COMPLETED.equals(finalStatus)
                        ? WorkflowExecutionEvent.create(
                        WorkflowExecutionEvent.Type.EXECUTION_COMPLETED,
                        executionId,
                        null,
                        LocalDateTime.now(),
                        null,
                        null)
                        : WorkflowExecutionEvent.create(
                        WorkflowExecutionEvent.Type.EXECUTION_FAILED,
                        executionId,
                        null,
                        LocalDateTime.now(),
                        null,
                        null
                ));
            } catch (Exception e) {
                log.error("恢复执行失败: executionId={}", executionId, e);
                repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.FAILED.name());
                publishEvent(WorkflowExecutionEvent.create(
                        WorkflowExecutionEvent.Type.EXECUTION_FAILED,
                        executionId,
                        null,
                        LocalDateTime.now(),
                        null,
                        null
                ));
            }
        }, asyncExecutor);

        publishEvent(WorkflowExecutionEvent.create(
                WorkflowExecutionEvent.Type.EXECUTION_RESUMED,
                executionId,
                null,
                LocalDateTime.now(),
                null,
                null
        ));

        return Response.buildSuccess();
    }

    @Override
    public Response stop(WorkflowExecutionControlCommand command) {
        repository.updateExecutionStatus(command.getExecutionId(), WorkflowExecutionStatus.STOPPED.name());
        publishEvent(WorkflowExecutionEvent.create(
                WorkflowExecutionEvent.Type.EXECUTION_STOPPED,
                command.getExecutionId(),
                null,
                LocalDateTime.now(),
                null,
                null
        ));
        return Response.buildSuccess();
    }

    // ==================== 查询 ====================

    @Override
    public SingleResponse<ExecutionDetailVO> getExecutionInfo(Long executionId) {
        ExecutionDTO executionDTO = repository.getExecution(executionId);
        if (executionDTO == null) {
            ExecutionDetailVO vo = new ExecutionDetailVO();
            vo.setExecutionId(executionId);
            vo.setStatusValue(WorkflowExecutionStatus.STOPPED.name());
            vo.setStatusName(WorkflowExecutionStatus.STOPPED.getDescription());
            vo.setErrorMsg("执行记录不存在");
            return SingleResponse.of(vo);
        }

        ExecutionDetailVO vo = toDetailVO(executionDTO);

        // 查询节点执行状态
        List<NodeExecutionDTO> nodeExecutions = repository.getNodeExecutions(executionId);
        if (nodeExecutions != null && !nodeExecutions.isEmpty()) {
            Map<String, NodeExecutionDetailVO> nodeResultMap = new HashMap<>();
            for (NodeExecutionDTO ne : nodeExecutions) {
                NodeExecutionDetailVO nodeVO = new NodeExecutionDetailVO();
                nodeVO.setNodeId(ne.getNodeId());
                nodeVO.setNodeType(ne.getNodeType());
                nodeVO.setStatusValue(ne.getStatusValue());
                nodeVO.setStatusName(ne.getStatusName());
                nodeVO.setOutput(ne.getOutput());
                nodeVO.setErrorMsg(ne.getErrorMsg());
                nodeVO.setStartAt(ne.getStartAt());
                nodeVO.setEndAt(ne.getEndAt());
                nodeResultMap.put(ne.getNodeId(), nodeVO);
            }
            vo.setNodeExecutions(nodeResultMap);
        }

        return SingleResponse.of(vo);
    }

    // ==================== 内部执行逻辑 ====================

    /**
     * 完整执行（同步阻塞）
     */
    private void executeInternal(Long executionId, Long definitionId, Long historyId,
                                 Map<String, Object> inputVariables) {
        WorkflowGraphDTO graphDTO = repository.getWorkflowGraph(definitionId, historyId);
        DagDefinition dagDefinition = graphConverter.convert(graphDTO);

        ExecutionContext context = buildContext(executionId, definitionId, inputVariables);

        repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.RUNNING.name());
        publishEvent(WorkflowExecutionEvent.create(
                WorkflowExecutionEvent.Type.EXECUTION_STARTED,
                executionId,
                definitionId,
                LocalDateTime.now(),
                null,
                null
        ));

        ExecutionHandle handle;
        try {
            handle = dagEngine.execute(dagDefinition, context);
        } catch (Exception e) {
            repository.updateExecutionContext(executionId, context.getVariables());
            repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.FAILED.name());
            publishEvent(WorkflowExecutionEvent.create(
                    WorkflowExecutionEvent.Type.EXECUTION_FAILED,
                    executionId,
                    null ,
                    LocalDateTime.now(),
                    null,
                    e
            ));
            throw new RuntimeException(e);
        }

        finalizeExecution(executionId, context, handle);
    }

    /**
     * 运行到此节点（同步阻塞）
     */
    private void executeUpToNodeInternal(Long executionId, Long definitionId, Long historyId,
                                         Map<String, Object> inputVariables, String endNodeId) {
        WorkflowGraphDTO graphDTO = repository.getWorkflowGraph(definitionId, historyId);
        DagDefinition dagDefinition = graphConverter.convert(graphDTO);

        ExecutionContext context = buildContext(executionId, definitionId, inputVariables);

        repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.RUNNING.name());
        publishEvent(WorkflowExecutionEvent.create(
                WorkflowExecutionEvent.Type.EXECUTION_STARTED,
                executionId,
                definitionId,
                LocalDateTime.now(),
                null,
                null
        ));

        ExecutionHandle handle;
        try {
            ExecutionOptions options = ExecutionOptions.upTo(endNodeId);
            handle = dagEngine.execute(dagDefinition, options, context);
        } catch (Exception e) {
            repository.updateExecutionContext(executionId, context.getVariables());
            repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.FAILED.name());
            publishEvent(WorkflowExecutionEvent.create(
                    WorkflowExecutionEvent.Type.EXECUTION_FAILED,
                    executionId,
                    null,
                    LocalDateTime.now(),
                    null,
                    e
            ));
            throw new RuntimeException(e);
        }

        finalizeExecution(executionId, context, handle);
    }

    /**
     * 处理执行结果（成功/失败）
     */
    private void handleExecutionResult(Long executionId, ExecutionContext context, String finalStatus) {
        repository.updateExecutionContext(executionId, context.getVariables());
        repository.updateExecutionStatus(executionId, finalStatus);

        if (WorkflowExecutionStatus.COMPLETED.name().equals(finalStatus)) {
            publishEvent(WorkflowExecutionEvent.create(
                    WorkflowExecutionEvent.Type.EXECUTION_COMPLETED,
                    executionId,
                    null,
                    LocalDateTime.now(),
                    null,
                    null
            ));
        } else {
            publishEvent(WorkflowExecutionEvent.create(
                    WorkflowExecutionEvent.Type.EXECUTION_FAILED,
                    executionId,
                    null,
                    LocalDateTime.now(),
                    null,
                     null
            ));
        }
    }

    /**
     * 完成执行后处理
     */
    private void finalizeExecution(Long executionId, ExecutionContext context, ExecutionHandle handle) {
        repository.updateExecutionContext(executionId, context.getVariables());

        if (handle.getStatus() == com.particle.global.dag.runtime.DagExecutionStatus.COMPLETED) {
            repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.COMPLETED.name());
            publishEvent(WorkflowExecutionEvent.create(
                    WorkflowExecutionEvent.Type.EXECUTION_COMPLETED,
                    executionId,
                    null,
                    LocalDateTime.now(),
                    context.getVariables(),
                     null
            ));
        } else {
            repository.updateExecutionStatus(executionId, WorkflowExecutionStatus.FAILED.name());
            publishEvent(WorkflowExecutionEvent.create(
                    WorkflowExecutionEvent.Type.EXECUTION_FAILED,
                    executionId,
                    null,
                    LocalDateTime.now(),
                    null,
                    null
            ));
        }
    }

    /**
     * 构建执行上下文
     */
    private ExecutionContext buildContext(Long executionId, Long definitionId,
                                          Map<String, Object> inputVariables) {
        ExecutionContext context = new ExecutionContext();
        if (inputVariables != null) {
            inputVariables.forEach(context::setVariable);
        }
        context.setAttribute("executionId", executionId);
        context.setAttribute("definitionId", definitionId);
        return context;
    }

    // ==================== DagExecutionController 实现 ====================

    /**
     * 桥接 dag 引擎的流程控制到 workflow 层的数据库状态
     */
    private class WorkflowDagExecutionController implements DagExecutionController {

        @Override
        public boolean shouldContinue(ExecutionContext context) {
            Long executionId = (Long) context.getAttribute("executionId");
            if (executionId == null) return true;

            ExecutionDTO executionDTO = repository.getExecution(executionId);
            if (executionDTO == null) return true;

            boolean shouldContinue = WorkflowExecutionStatus.RUNNING.name().equals(executionDTO.getStatusValue());
            if (!shouldContinue) {
                log.info("执行被中断: executionId={}, status={}", executionId, executionDTO.getStatusValue());
            }
            return shouldContinue;
        }

        @Override
        public void onInterrupted(ExecutionContext context, String reason) {
            Long executionId = (Long) context.getAttribute("executionId");
            if (executionId == null) return;

            repository.updateExecutionContext(executionId, context.getVariables());
            repository.updateExecutionStatus(executionId, reason);
            log.info("执行中断，已保存上下文快照: executionId={}, reason={}", executionId, reason);
        }
    }

    // ==================== 节点拦截器 ====================

    /**
     * 节点执行拦截器：桥接 dag 的节点事件到 workflow 层的 Repository 和 Listener
     */
    private class NodeCallbackInterceptor implements NodeExecutionInterceptor {

        @Override
        public void beforeNode(DagNode node, ExecutionContext context,
                               Map<String, NodePort> inputMap) {
            Long executionId = (Long) context.getAttribute("executionId");
            if (executionId != null) {
                repository.updateNodeExecution(executionId, node.getId(),
                        WorkflowExecutionStatus.RUNNING.name(), null, null, null);
                publishEvent(WorkflowExecutionEvent.create(
                        WorkflowExecutionEvent.Type.NODE_STARTED,
                        executionId,
                        node.getId(),
                        node.getType(),
                        LocalDateTime.now(),
                        null,
                         null
                ));
            }
        }

        @Override
        public void afterNodeSuccess(DagNode node, ExecutionContext context,
                                    NodeOutput output) {
            Long executionId = (Long) context.getAttribute("executionId");
            if (executionId != null) {
                Map<String, Object> outputMap = output != null && output.getPorts() != null
                        ? output.getPorts().entrySet().stream()
                        .collect(HashMap::new,
                                (m, e) -> m.put(e.getKey(), e.getValue().getData()),
                                HashMap::putAll)
                        : null;
                repository.updateNodeExecution(executionId, node.getId(),
                        WorkflowExecutionStatus.COMPLETED.name(), null, outputMap, null);
                publishEvent(WorkflowExecutionEvent.create(
                        WorkflowExecutionEvent.Type.NODE_COMPLETED,
                        executionId,
                        node.getId(),
                        node.getType(),
                        LocalDateTime.now(),
                        output,
                         null
                ));
            }
        }

        @Override
        public boolean afterNodeFailed(DagNode node, ExecutionContext context,
                                      Throwable error) {
            Long executionId = (Long) context.getAttribute("executionId");
            if (executionId != null) {
                String errorMsg = error != null ? error.getMessage() : "unknown";
                repository.updateNodeExecution(executionId, node.getId(),
                        WorkflowExecutionStatus.FAILED.name(), null, null, errorMsg);
                publishEvent(WorkflowExecutionEvent.create(
                        WorkflowExecutionEvent.Type.NODE_FAILED,
                        executionId,
                        node.getId(),
                        node.getType(),
                        LocalDateTime.now(),
                        null,
                        error
                ));
            }
            return true;
        }
    }

    // ==================== VO/DTO 转换 ====================

    private ExecutionVO buildExecutionVO(Long executionId) {
        ExecutionVO vo = new ExecutionVO();
        vo.setExecutionId(executionId);
        return vo;
    }

    private ExecutionDetailVO toDetailVO(ExecutionDTO dto) {
        ExecutionDetailVO vo = new ExecutionDetailVO();
        vo.setExecutionId(dto.getExecutionId());
        vo.setDefinitionId(dto.getDefinitionId());
        vo.setStatusValue(dto.getStatusValue());
        vo.setStatusName(dto.getStatusName());
        vo.setTriggerTypeValue(dto.getTriggerTypeValue());
        vo.setCurrentNodeId(dto.getCurrentNodeId());
        vo.setStartAt(dto.getStartAt());
        vo.setEndAt(dto.getEndAt());
        vo.setErrorMsg(dto.getErrorMsg());
        vo.setCopiedExecutionId(dto.getCopiedExecutionId());
        return vo;
    }

    // ==================== 工具方法 ====================

    private void publishEvent(WorkflowExecutionEvent event) {
        for (WorkflowExecutionListener listener : listeners) {
            try {
                listener.onEvent(event);
            } catch (Exception e) {
                log.warn("事件监听器异常: {}", e.getMessage());
            }
        }
    }
}
