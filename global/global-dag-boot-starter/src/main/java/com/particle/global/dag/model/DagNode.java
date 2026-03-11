package com.particle.global.dag.model;

import lombok.Data;
import lombok.Builder;

import java.util.Map;

/**
 * <p>
 * DAG节点模型，表示DAG中的一个节点
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Data
@Builder
public class DagNode {

    /**
     * 节点唯一标识
     */
    private String id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点类型，如: SEQUENTIAL, PARALLEL, CONDITIONAL, TASK等
     */
    private String type;

    /**
     * 节点配置信息
     */
    private Map<String, Object> config;

    // 无参构造函数
    public DagNode() {}

    // 全参构造函数（不含状态）
    public DagNode(String id, String name, String type, Map<String, Object> config) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.config = config;
    }
}