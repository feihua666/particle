package com.particle.global.dag.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * DAG 定义模型，表示一个完整的DAG（有向无环图）工作流定义
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Data
public class DagDefinition {

    /**
     * DAG 唯一标识
     */
    private String id;

    /**
     * DAG 名称
     */
    private String name;

    /**
     * DAG 节点列表，定义了工作流中的所有节点
     */
    private List<DagNode> nodes;

    /**
     * DAG 边列表，定义了节点间的依赖关系
     */
    private List<DagEdge> edges;

    /**
     * DAG 属性映射，包含 DAG 级别的配置属性
     */
    private Map<String, Object> properties;

    // 无参构造函数
    public DagDefinition() {}

    // 全参构造函数
    public DagDefinition(String id, String name, List<DagNode> nodes, List<DagEdge> edges, Map<String, Object> properties) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
        this.properties = properties;
    }

    /**
     * 添加边到 DAG
     * @param edge 要添加的边
     */
    public void addEdge(DagEdge edge) {
        if (this.edges == null) {
            this.edges = new ArrayList<>();
        }
        this.edges.add(edge);
    }

    /**
     * 根据起始节点和目标节点查找边
     * @param fromNodeId 起始节点ID
     * @param toNodeId 目标节点ID
     * @return 匹配的边，如果没有找到则返回null
     */
    public DagEdge getEdge(String fromNodeId, String toNodeId) {
        if (edges == null) {
            return null;
        }
        return edges.stream()
                .filter(edge -> fromNodeId.equals(edge.getFromNodeId()) &&
                               toNodeId.equals(edge.getToNodeId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据节点ID获取节点
     * @param nodeId 节点唯一标识
     * @return 节点对象，如果不存在则返回null
     */
    public DagNode getNodeById(String nodeId) {
        if (nodes == null) {
            return null;
        }
        return nodes.stream()
                .filter(node -> nodeId.equals(node.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取某个节点的所有前置依赖节点
     * @param nodeId 节点ID
     * @return 前置依赖节点列表
     */
    public List<DagNode> getPredecessorNodes(String nodeId) {
        if (edges == null || nodes == null) {
            return Collections.EMPTY_LIST;
        }

        // 找到所有指向该节点的边（即该节点的依赖边）
        List<String> dependentNodeIds = getPredecessorNodeIds(nodeId);

        // 根据ID找到对应的节点
        return nodes.stream()
                .filter(node -> dependentNodeIds.contains(node.getId()))
                .collect(Collectors.toList());
    }
    /**
     * 获取某个节点的所有前置依赖节点
     * @param nodeId 节点ID
     * @return 前置依赖节点列表
     */
    public List<String> getPredecessorNodeIds(String nodeId) {
        if (edges == null || nodes == null) {
            return Collections.EMPTY_LIST;
        }

        // 找到所有指向该节点的边（即该节点的依赖边）
        List<String> dependentNodeIds = edges.stream()
                .filter(edge -> nodeId.equals(edge.getToNodeId()))
                .map(DagEdge::getFromNodeId)
                .collect(Collectors.toList());

        return dependentNodeIds;
    }
    /**
     * 获取某个节点的入度（进入该节点的边的数量）
     * @param nodeId 节点ID
     * @return 入度数量
     */
    public int getPredecessorEdgeCount(String nodeId) {
        if (edges == null) {
            return 0;
        }

        return (int) edges.stream()
                .filter(edge -> nodeId.equals(edge.getToNodeId()))
                .count();
    }
    /**
     * 获取某个节点的所有后续节点
     * @param nodeId 节点 ID
     * @return 后续节点列表
     */
    public List<DagNode> getSuccessorNodes(String nodeId) {
        if (edges == null || nodes == null) {
            return Collections.EMPTY_LIST;
        }

        // 找到所有从该节点出发的边
        List<String> successorNodeIds = getSuccessorNodeIds(nodeId);

        // 根据ID找到对应的节点
        return nodes.stream()
                .filter(node -> successorNodeIds.contains(node.getId()))
                .collect(Collectors.toList());
    }



    /**
     * 获取某个节点的所有后续节点 ID 列表
     * @param nodeId 节点 ID
     * @return 后续节点 ID 列表
     */
    public List<String> getSuccessorNodeIds(String nodeId) {
        if (edges == null) {
            return Collections.EMPTY_LIST;
        }

        return edges.stream()
                .filter(edge -> nodeId.equals(edge.getFromNodeId()))
                .map(DagEdge::getToNodeId)
                .collect(Collectors.toList());
    }
    /**
     * 获取某个节点的出度（出该节点的边的数量）
     * @param nodeId 节点 ID
     * @return 入度数量
     */
    public int getSuccessorEdgeCount(String nodeId) {
        if (edges == null) {
            return 0;
        }

        return (int) edges.stream()
                .filter(edge -> nodeId.equals(edge.getFromNodeId()))
                .count();
    }
}
