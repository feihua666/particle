package com.particle.global.workflow.listener;

import com.particle.global.workflow.event.WorkflowExecutionEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志工作流执行监听器
 * <p>
 * 将所有执行事件输出到日志，默认不启用（需手动注册）。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
@Slf4j
public class LoggingWorkflowExecutionListener implements WorkflowExecutionListener {

    @Override
    public void onEvent(WorkflowExecutionEvent event) {
        switch (event.getType()) {
            case EXECUTION_CREATED:
                log.info("[Workflow] 执行记录创建: executionId={}, definitionId={}",
                        event.getExecutionId(), event.getDefinitionId());
                break;
            case EXECUTION_STARTED:
                log.info("[Workflow] 执行开始: executionId={}", event.getExecutionId());
                break;
            case EXECUTION_COMPLETED:
                log.info("[Workflow] 执行完成: executionId={}", event.getExecutionId());
                break;
            case EXECUTION_FAILED:
                log.error("[Workflow] 执行失败: executionId={}, error={}",
                        event.getExecutionId(),
                        event.getError() != null ? event.getError().getMessage() : "unknown");
                break;
            case EXECUTION_STOPPED:
                log.info("[Workflow] 执行停止: executionId={}", event.getExecutionId());
                break;
            case NODE_STARTED:
                log.debug("[Workflow] 节点开始: executionId={}, nodeId={}, nodeType={}",
                        event.getExecutionId(), event.getNodeId(), event.getNodeType());
                break;
            case NODE_COMPLETED:
                log.debug("[Workflow] 节点完成: executionId={}, nodeId={}",
                        event.getExecutionId(), event.getNodeId());
                break;
            case NODE_FAILED:
                log.warn("[Workflow] 节点失败: executionId={}, nodeId={}, error={}",
                        event.getExecutionId(), event.getNodeId(),
                        event.getError() != null ? event.getError().getMessage() : "unknown");
                break;
            case NODE_SKIPPED:
                log.debug("[Workflow] 节点跳过: executionId={}, nodeId={}",
                        event.getExecutionId(), event.getNodeId());
                break;
        }
    }
}
