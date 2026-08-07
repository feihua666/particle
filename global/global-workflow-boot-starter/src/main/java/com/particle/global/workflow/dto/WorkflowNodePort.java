package com.particle.global.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.particle.global.dag.model.DataType;
import lombok.Data;

import java.util.List;

/**
 * 工作流节点端口 — 对应前端 PortDefinition
 * <p>
 * 与前端 {@code PortDefinition} 接口字段一一对应。
 * </p>
 *
 * @author particle
 * @since 2026-05-03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowNodePort {

    /** 端口名称，如 "output", "body", "statusCode" */
    private String name;

    /**
     * 端口类型
     */
    private String portType;
    /**
     *  端口数据类型: STRING / NUMBER / JSON 等
     *  对应{@link DataType}
     */
    @JsonDeserialize(using = StringOrArrayDeserializer.class)
    private List<String> dataTypes;

    /** 是否必填（仅对输入端口有效） */
    private Boolean required;

    /** 端口描述，用于 UI 提示 */
    private String description;

    /**
     * 端口值
     * <p>
     * valuePorts 使用：用户在画布上填写的预设值
     * inputPorts / outputPorts 通常为 null
     * </p>
     */
    private Object data;

}
