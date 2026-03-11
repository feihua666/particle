package com.particle.global.dag.runtime;

/**
 * <p>
 * DAG执行状态枚举
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 11:28:00
 */
public enum DagExecutionStatus {
    /** 待执行 */
    PENDING("待执行"),
    /** 运行中 */
    RUNNING("运行中"),
    /** 已暂停 */
    PAUSED("已暂停"),
    /** 已完成 */
    COMPLETED("已完成"),
    /** 执行失败 */
    FAILED("执行失败"),
    /** 已停止 */
    STOPPED("已停止");

    private final String description;

    /**
     * 构造函数
     * @param description 描述信息
     */
    DagExecutionStatus(String description) {
        this.description = description;
    }

    /**
     * 获取描述信息
     * @return 描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 是否为最终状态
     * @return 是否为最终状态
     */
    public boolean isFinal() {
        return this == COMPLETED || this == FAILED || this == STOPPED;
    }
}