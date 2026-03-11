package com.particle.global.dag.plan;

import java.util.List;

/**
 * <p>
 * DAG执行计划
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class DagExecutionPlan {

    /**
     * 执行计划ID
     */
    private final String id;

    /**
     * 执行步骤列表
     */
    private final List<ExecutionStep> steps;

    public DagExecutionPlan(String id, List<ExecutionStep> steps) {
        this.id = id;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public List<ExecutionStep> getSteps() {
        return steps;
    }
}