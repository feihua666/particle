package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 工作流节点 — 对应前端 VueFlow Node
 * <p>
 * 保留 data 包裹层，因为 graphDataJson 中 VueFlow Node 就是这个结构。
 * 前端专属字段（position、width、height、selected 等）通过 {@code @JsonIgnoreProperties(ignoreUnknown = true)} 自动忽略。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowNode {

    /**
     * 节点唯一标识
     */
    private String id;

    /**
     * 节点数据（与前端 VueFlow Node.data 结构一致）
     */
    private WorkflowNodeData data;
}
