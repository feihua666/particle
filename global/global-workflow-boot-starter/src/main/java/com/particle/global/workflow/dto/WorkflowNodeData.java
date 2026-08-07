package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 工作流节点数据 — 对应前端 node.data
 * <p>
 * 与前端 {@code WorkflowNodeData} 接口字段一一对应。
 * typeName/name 是前端 UI 展示用，后端不使用但保留（JSON 反序列化不丢失）。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowNodeData {

    /** 节点类型名称（如"文本节点"），前端展示用 */
    private String typeName;

    /** 节点自定义名称，用户可编辑 */
    private String name;

    /** 节点角色: DATA / PROCESSOR */
    private String nodeRole;

    /** 节点执行类型: TEXT / IMAGE / VIDEO / HTTP / AI 等 */
    private String nodeType;

    /** 输入端口声明 */
    private Map<String,WorkflowNodePort> inputPorts;

    /** 输出端口声明 */
    private Map<String,WorkflowNodePort> outputPorts;

    /** 预设值端口（有值） */
    private Map<String,WorkflowNodePort> valuePorts;

    /** 节点行为配置（不含数据值） */
    private Map<String, Object> config;
}
