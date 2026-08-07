package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 工作流定义数据 — 节点和边的集合
 * <p>
 * 对应前端 WorkflowGraphData.definition（VueFlowGraphData 的 nodes + edges 部分）。
 * 不包含 viewport 等前端专属字段。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowData {

    /**
     * 节点列表
     */
    private List<WorkflowNode> nodes;

    /**
     * 边列表
     */
    private List<WorkflowEdge> edges;
}
