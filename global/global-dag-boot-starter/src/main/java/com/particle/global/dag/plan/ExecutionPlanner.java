package com.particle.global.dag.plan;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.options.ExecutionOptions;

/**
 * <p>
 * 执行计划器，负责根据DAG定义、执行上下文和执行选项生成执行计划
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface ExecutionPlanner {

    /**
     * 生成执行计划
     * @param dagDefinition DAG定义
     * @param context 执行上下文（包含已执行节点的状态）
     * @param options 执行选项
     * @return 执行计划
     */
    DagExecutionPlan plan(DagDefinition dagDefinition, ExecutionContext context, ExecutionOptions options);
}
