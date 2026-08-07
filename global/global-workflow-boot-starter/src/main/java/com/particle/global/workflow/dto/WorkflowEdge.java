package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 工作流边 — 对应前端 VueFlow Edge
 * <p>
 * 保留 data 包裹层，与前端 VueFlow Edge 结构一致。
 * 前端专属字段（animated、style、label 等）通过 {@code @JsonIgnoreProperties(ignoreUnknown = true)} 自动忽略。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowEdge {

    /**
     * 边的唯一标识
     */
    private String id;

    /**
     * 源节点ID
     */
    private String source;

    /**
     * 目标节点ID
     */
    private String target;

    /**
     * 源端口名 — 对应上游节点的 outputPort.name
     */
    private String sourceHandle;

    /**
     * 目标端口名 — 对应下游节点的 inputPort.name
     */
    private String targetHandle;

    /**
     * 边数据（与前端 edge.data 结构一致）
     */
    private WorkflowEdgeData data;
}
