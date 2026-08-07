package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 工作流边数据 — 对应前端 edge.data
 * <p>
 * 与前端 {@code WorkflowEdgeData} 接口字段一一对应。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowEdgeData {

    /** 边类型: DEFAULT(默认) 或 CONDITIONAL(条件分支) */
    private String edgeType;

    /** 条件表达式 (CONDITIONAL 类型时使用) */
    private String condition;
}
