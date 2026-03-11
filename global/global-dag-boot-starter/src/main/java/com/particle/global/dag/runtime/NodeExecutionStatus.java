package com.particle.global.dag.runtime;

/**
 * <p>
 * 节点执行状态枚举
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 11:29:00
 */
public enum NodeExecutionStatus {
    /** 待执行 */
    PENDING("待执行"),
    /** 运行中 */
    RUNNING("运行中"),
    /** 执行成功 */
    SUCCESS("执行成功"),
    /** 执行失败 */
    FAILED("执行失败"),
    /** 已跳过 */
    SKIPPED("已跳过");

    private final String description;

    /**
     * 构造函数
     * @param description 描述信息
     */
    NodeExecutionStatus(String description) {
        this.description = description;
    }

    /**
     * 获取描述信息
     * @return 描述信息
     */
    public String getDescription() {
        return description;
    }
}
