package com.particle.global.dag.model;

import lombok.Data;

import javax.sound.sampled.Port;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 节点输出 — executor 执行完返回的端口化输出
 * </p>
 * <p>
 * 每个端口名对应一个 NodePort，引擎按此结构逐端口存入 context。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
@Data
public class NodeOutput {

    /**
     * 输出端口集合，key = 端口名
     */
    private Map<String, NodePort> ports = new LinkedHashMap<>();

    public NodeOutput() {}

    /**
     * 添加一个输出端口
     *
     * @param name     端口名
     * @param data    端口值
     * @param dataTypes 数据类型
     */
    public void addPort(String name, Object data, PortType portType, List<DataType> dataTypes) {
        ports.put(name, new NodePort(name,portType, dataTypes, data));
    }

    /**
     * 获取指定端口的值
     *
     * @param portName 端口名
     * @return 端口值，不存在返回 null
     */
    public Object getPortData(String portName) {
        NodePort port = ports.get(portName);
        return port != null ? port.getData() : null;
    }

    /**
     * 便捷工厂方法：创建只有一个输出端口的 NodeOutput
     */
    public static NodeOutput of(String portName, Object data, PortType portType, List<DataType> dataTypes) {
        NodeOutput output = new NodeOutput();
        output.addPort(portName, data,portType, dataTypes);
        return output;
    }
}
