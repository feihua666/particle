package com.particle.global.dag.model;

import lombok.Data;

import java.util.Map;

/**
 * <p>
 * DAG 边定义模型，表示 DAG 中两个节点之间的依赖关系
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Data
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
    private Map<String, Object> config;

    /**
     * 源端口名 — 对应上游节点的 outputPort.name
     * 引擎根据此字段从 context 中取上游节点的特定端口值
     */
    private String fromPort;

    /**
     * 目标端口名 — 对应下游节点的 inputPort.name
     * 引擎根据此字段将值注入到下游节点的 inputMap 中
     */
    private String toPort;
}
