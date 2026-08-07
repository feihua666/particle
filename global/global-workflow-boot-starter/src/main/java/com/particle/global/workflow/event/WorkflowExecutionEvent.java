package com.particle.global.workflow.event;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 工作流执行事件模型
 * <p>
 * 由 WorkflowExecutionListener 接收，用于日志/指标/调试等扩展。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
@Data
public class WorkflowExecutionEvent {

    public enum Type {
        /** 执行记录创建 */
        EXECUTION_CREATED,
        /** 执行开始 */
        EXECUTION_STARTED,
        /** 执行成功完成 */
        EXECUTION_COMPLETED,
        /** 执行失败 */
        EXECUTION_FAILED,
        /** 执行被暂停 */
        EXECUTION_PAUSED,
        /** 执行已恢复 */
        EXECUTION_RESUMED,
        /** 执行被停止 */
        EXECUTION_STOPPED,
        /** 节点开始执行 */
        NODE_STARTED,
        /** 节点执行成功 */
        NODE_COMPLETED,
        /** 节点执行失败 */
        NODE_FAILED,
        /** 节点被跳过 */
        NODE_SKIPPED
    }

    /** 事件类型 */
    private Type type;

    /** 持久化执行记录ID */
    private Long executionId;

    /** 工作流定义ID */
    private Long definitionId;

    /** 节点ID（节点级事件时有效） */
    private String nodeId;

    /** 节点类型（节点级事件时有效） */
    private String nodeType;

    /** 事件时间 */
    private LocalDateTime timestamp;

    /** 附加数据（如 inputJson、outputJson） */
    private Object data;

    /** 异常（失败时有效） */
    private Throwable error;

    public static WorkflowExecutionEvent create(Type type,
                                                 Long executionId,
                                                 Long definitionId,
                                                 LocalDateTime timestamp,
                                                Object data,Throwable error) {
        WorkflowExecutionEvent workflowExecutionEvent = new WorkflowExecutionEvent();
        workflowExecutionEvent.type = type;
        workflowExecutionEvent.executionId = executionId;
        workflowExecutionEvent.definitionId = definitionId;
        workflowExecutionEvent.timestamp = timestamp;
        workflowExecutionEvent.data = data;
        workflowExecutionEvent.error = error;
        return workflowExecutionEvent;
    }

    public static WorkflowExecutionEvent create(Type type,
                                                Long executionId,
                                                String nodeId,
                                                String nodeType,
                                                LocalDateTime timestamp,
                                                Object  data,
                                                Throwable error) {
        WorkflowExecutionEvent workflowExecutionEvent = new WorkflowExecutionEvent();
        workflowExecutionEvent.type = type;
        workflowExecutionEvent.executionId = executionId;
        workflowExecutionEvent.nodeId = nodeId;
        workflowExecutionEvent.nodeType = nodeType;
        workflowExecutionEvent.timestamp = timestamp;
        workflowExecutionEvent.data = data;
        workflowExecutionEvent.error = error;
        return workflowExecutionEvent;
    }
}
