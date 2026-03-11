package com.particle.global.dag.plan;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.ExecutionContext;

import java.util.List;

/**
 * <p>
 * 执行计划接口，定义DAG执行计划的生成和管理
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface ExecutionPlan {

    /**
     * 生成执行计划
     * @param dagDefinition DAG定义
     * @param options 执行选项
     * @param context 执行上下文
     * @return 执行计划步骤列表
     */
    List<ExecutionStep> generatePlan(DagDefinition dagDefinition, ExecutionOptions options, ExecutionContext context);

    /**
     * 获取计划名称
     * @return 计划名称
     */
    String getName();

    /**
     * 获取计划描述
     * @return 计划描述
     */
    String getDescription();
}