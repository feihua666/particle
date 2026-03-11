package com.particle.global.dag.plan.strategy;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.plan.DagExecutionPlan;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.options.ExecutionOptions;

/**
 * <p>
 * 规划策略接口
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface PlanningStrategy {

    /**
     * 应用规划策略生成执行计划
     * @param dagDefinition DAG定义
     * @param executionContext 执行上下文（包含已执行节点的状态）
     * @param options 执行选项
     * @return 执行计划
     */
    DagExecutionPlan plan(DagDefinition dagDefinition, ExecutionContext executionContext, ExecutionOptions options);

    /**
     * 策略名称
     * @return 策略名称
     */
    String getName();

    /**
     * 策略描述
     * @return 策略描述
     */
    String getDescription();
}
