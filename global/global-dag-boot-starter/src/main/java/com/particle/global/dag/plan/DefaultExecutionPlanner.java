package com.particle.global.dag.plan;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.plan.strategy.AggressivePlanningStrategy;
import com.particle.global.dag.plan.strategy.PlanningStrategy;
import com.particle.global.dag.plan.strategy.SequentialPlanningStrategy;
import com.particle.global.dag.runtime.ExecutionContext;

/**
 * <p>
 * 默认执行计划器实现
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class DefaultExecutionPlanner implements ExecutionPlanner {

    @Override
    public DagExecutionPlan plan(DagDefinition dagDefinition, ExecutionContext context, ExecutionOptions options) {
        // 使用策略生成执行计划
        PlanningStrategy strategy = selectStrategy(options);
        return strategy.plan(dagDefinition, context, options);
    }

    private PlanningStrategy selectStrategy(ExecutionOptions options) {
        if (options.isParallel()) {
            return new AggressivePlanningStrategy();
        } else {
            return new SequentialPlanningStrategy();
        }
    }
}
