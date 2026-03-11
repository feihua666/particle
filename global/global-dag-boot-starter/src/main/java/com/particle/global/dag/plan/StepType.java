package com.particle.global.dag.plan;

/**
 * <p>
 * 执行步骤类型枚举
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public enum StepType {
    /** 并行执行步骤 */
    PARALLEL("并行执行步骤"),
    /** 串行执行步骤 */
    SEQUENTIAL("串行执行步骤"),
    /** 条件执行步骤 */
    CONDITIONAL("条件执行步骤"),
    /** 分支执行步骤 */
    BRANCH("分支执行步骤");

    private final String description;

    /**
     * 构造函数
     * @param description 描述信息
     */
    StepType(String description) {
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