package com.particle.global.dag.runtime;

/**
 * DAG 执行状态枚举
 * <p>
 * 只保留引擎本身关心的 4 个状态。
 * PAUSED / STOPPED 是业务层（workflow）的概念，由 DagExecutionController 处理，
 * dag 引擎不需要自己的暂停/停止状态——执行到一半 return 即为中断。
 * </p>
 *
 * @author particle
 * @since 2026-01-12 11:28:00
 */
public enum DagExecutionStatus {
    /** 待执行 */
    PENDING("待执行"),
    /** 运行中 */
    RUNNING("运行中"),
    /** 已完成 */
    COMPLETED("已完成"),
    /** 执行失败 */
    FAILED("执行失败");

    private final String description;

    DagExecutionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 是否为最终状态（执行已结束）
     */
    public boolean isFinal() {
        return this == COMPLETED || this == FAILED;
    }
}
