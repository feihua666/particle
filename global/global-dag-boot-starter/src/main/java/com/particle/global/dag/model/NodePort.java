package com.particle.global.dag.model;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 节点端口 — 声明节点的一个输入/输出/预设值端口
 * </p>
 * <p>
 * 三种用途：
 * <ul>
 *   <li>inputPorts: 声明节点需要什么输入，值由引擎从上游注入（无 value）</li>
 *   <li>outputPorts: 声明节点产生什么输出，值由 executor 执行后写入（无 value）</li>
 *   <li>valuePorts: 节点自身携带的预设值，由用户在画布上填写（有 value）</li>
 * </ul>
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
@Data
public class NodePort {

    public static final String INPUT_PORT_NAME = "input";
    public static final String OUTPUT_PORT_NAME = "output";
    public static final String CONTENT_PORT_NAME = "content";
    public static final String INPUTCONTROL_PORT_NAME = "'inputControl'";
    public static final String OUTPUTCONTROL_PORT_NAME = "'outputControl'";


    public NodePort(String name,PortType portType, List<DataType> dataType, Object data) {
        this.name = name;
        this.portType = portType;
        this.dataTypes = dataType;
        this.data = data;
    }

    /**
     * 端口名称，如 "output", "body", "statusCode", "prompt"
     */
    private String name;

    /**
     * 端口类型
     */
    private PortType portType;

    /**
     * 端口数据类型
     */
    private List<DataType> dataTypes;

    /**
     * 端口值
     * 仅 valuePorts 使用，inputPorts/outputPorts 为 null
     */
    private Object data;

    /**
     * 创建声明用端口（无 value），用于 inputPorts / outputPorts
     */
    public static NodePort declare(String name,PortType portType, List<DataType> dataType) {
        return new NodePort(name,portType, dataType, null);
    }

    /**
     * 创建值端口（有 value），用于 valuePorts
     */
    public static NodePort withData(String name,PortType portType, List<DataType> dataType, Object data) {
        return new NodePort(name, portType, dataType, data);
    }
}
