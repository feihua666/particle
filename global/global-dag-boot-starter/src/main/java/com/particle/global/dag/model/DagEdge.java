package com.particle.global.dag.model;

import lombok.Data;
import lombok.Builder;

/**
 * <p>
 * DAG边模型，表示DAG中两个节点之间的依赖关系
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Data
@Builder
public class DagEdge {

    /**
     * 边的唯一标识
     */
    private String id;

    /**
     * 起始节点ID
     */
    private String fromNodeId;

    /**
     * 目标节点ID
     */
    private String toNodeId;

    /**
     * 边的类型，如: CONDITIONAL, DEFAULT等
     */
    private String type;

    /**
     * 边的条件表达式，用于条件边
     */
    private String condition;

    /**
     * 边的权重，用于某些算法计算
     */
    private int weight;

    /**
     * 边的配置信息，包含边级别的配置参数
     */
    private java.util.Map<String, Object> config;

    // 无参构造函数
    public DagEdge() {}

    // 全参构造函数
    public DagEdge(String id, String fromNodeId, String toNodeId, String type, String condition, int weight, java.util.Map<String, Object> config) {
        this.id = id;
        this.fromNodeId = fromNodeId;
        this.toNodeId = toNodeId;
        this.type = type;
        this.condition = condition;
        this.weight = weight;
        this.config = config;
    }
}