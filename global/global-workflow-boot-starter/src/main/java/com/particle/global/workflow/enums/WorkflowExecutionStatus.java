package com.particle.global.workflow.enums;

import com.particle.global.dag.runtime.DagExecutionStatus;

/**
 * 工作流执行状态枚举
 * 前面的和 {@link DagExecutionStatus} 枚举一致，
 */
public enum WorkflowExecutionStatus{
    /** 待执行 */
    PENDING("待执行"),
    /** 运行中 */
    RUNNING("运行中"),
    /** 已完成 */
    COMPLETED("已完成"),
    /** 执行失败 */
    FAILED("执行失败"),
    /** 已暂停 */
    PAUSED("已暂停"),
    /** 已停止 */
    STOPPED("已停止");

    private final String description;

    WorkflowExecutionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 是否为最终状态（执行已结束）
     */
    public boolean isFinal() {
        return this == COMPLETED || this == FAILED || this == STOPPED;
    }

    /**
     * 转换为 DAG 执行状态
     */
    public DagExecutionStatus toDagExecutionStatus() {
        switch (this) {
            case PENDING:
                return DagExecutionStatus.PENDING;
            case RUNNING:
                return DagExecutionStatus.RUNNING;
            case COMPLETED:
                return DagExecutionStatus.COMPLETED;
            case FAILED:
                return DagExecutionStatus.FAILED;
            default:
                throw new IllegalArgumentException("无法转换状态: " + this);
        }
    }
}
