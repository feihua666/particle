package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 工作流图数据 DTO — 顶层结构
 * <p>
 * 对应数据库 graphDataJson 的 JSON 反序列化结果。
 * 不依赖任何前端框架（VueFlow 等），是纯业务数据结构。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowGraphDTO {

    /**
     * 工作流定义数据
     */
    private WorkflowData definition;
}
