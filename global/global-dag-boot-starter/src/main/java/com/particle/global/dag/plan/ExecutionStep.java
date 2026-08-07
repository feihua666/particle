package com.particle.global.dag.plan;

import com.particle.global.dag.model.DagNode;

import java.util.List;

/**
 * 执行步骤
 * <p>
 * 每个步骤包含一组可同时执行的节点（DAG 拓扑分层中的同一层）。
 * 引擎根据节点数量自动决定串行或并行：
 * - 1 个节点 → 当前线程串行执行
 * - 多个节点 → 提交到线程池并行执行
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public class ExecutionStep {

    /**
     * 步骤ID（如 "layer-0"、"layer-1"）
     */
    private final String id;

    /**
     * 该层的节点列表（无依赖关系的节点）
     */
    private final List<DagNode> nodes;

    public ExecutionStep(String id, List<DagNode> nodes) {
        this.id = id;
        this.nodes = nodes;
    }

    public String getId() {
        return id;
    }

    public List<DagNode> getNodes() {
        return nodes;
    }
}
