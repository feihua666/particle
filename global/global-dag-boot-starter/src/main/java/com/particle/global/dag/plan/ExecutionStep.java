package com.particle.global.dag.plan;

import com.particle.global.dag.model.DagNode;

import java.util.List;

/**
 * <p>
 * 执行步骤
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class ExecutionStep {

    /**
     * 步骤ID
     */
    private final String id;

    /**
     * 要执行的节点列表
     */
    private final List<DagNode> nodes;

    /**
     * 执行模式
     */
    private final ExecutionMode executionMode;

    /**
     * 并行度
     */
    private final int parallelism;

    public ExecutionStep(String id, List<DagNode> nodes, ExecutionMode executionMode, int parallelism) {
        this.id = id;
        this.nodes = nodes;
        this.executionMode = executionMode;
        this.parallelism = parallelism;
    }

    // Getters
    public String getId() {
        return id;
    }

    public List<DagNode> getNodes() {
        return nodes;
    }

    public ExecutionMode getExecutionMode() {
        return executionMode;
    }

    public int getParallelism() {
        return parallelism;
    }

}