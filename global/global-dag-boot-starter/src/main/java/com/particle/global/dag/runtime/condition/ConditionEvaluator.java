package com.particle.global.dag.runtime.condition;

import com.particle.global.dag.runtime.ExecutionContext;

/**
 * <p>
 * 条件评估器接口，用于评估条件表达式
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface ConditionEvaluator {

    /**
     * 评估条件
     * @param condition 条件字符串
     * @param context 执行上下文
     * @return 评估结果
     */
    boolean evaluate(String condition, ExecutionContext context);
}
