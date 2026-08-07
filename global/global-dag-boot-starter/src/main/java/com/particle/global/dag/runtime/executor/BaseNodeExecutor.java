package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.DataType;
import com.particle.global.dag.model.NodePort;
import com.particle.global.dag.model.PortType;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 节点执行器抽象基类
 * <p>
 * 提供节点执行的通用功能和模板方法，简化具体执行器的实现。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public abstract class BaseNodeExecutor implements NodeExecutor {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 执行节点的模板方法
     * <p>
     * 提供执行前后的日志记录和异常处理框架。
     * 子类只需实现 {@link #doExecute(DagNode, ExecutionContext, Map)} 方法。
     * </p>
     *
     * @param node     节点定义
     * @param context  执行上下文
     * @param inputMap 上游端口输入数据
     * @return 执行结果
     * @throws Exception 执行异常
     */
    @Override
    public final NodeExecutionResult execute(DagNode node, ExecutionContext context, Map<String, NodePort> inputMap) throws Exception {
        log.info("Executing {} node: {}", node.getType(), node.getId());

        try {
            NodeExecutionResult result = doExecute(node, context, inputMap);
            log.info("Node {} execution completed: success={}", node.getId(), result.isSuccess());
            return result;
        } catch (Exception e) {
            log.error("Node {} execution failed: {}", node.getId(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 执行节点的具体逻辑（由子类实现）
     *
     * @param node     节点定义
     * @param context  执行上下文
     * @param inputMap 上游端口输入数据
     * @return 执行结果
     * @throws Exception 执行异常
     */
    protected abstract NodeExecutionResult doExecute(DagNode node, ExecutionContext context, Map<String, NodePort> inputMap) throws Exception;

    /**
     * 获取端口的值
     *
     * @param inputMap 输入端口映射
     * @param portName 端口名称
     * @return 端口值，不存在返回 null
     */
    protected Object getDataFromInput(Map<String, NodePort> inputMap, String portName) {
        if (inputMap == null || inputMap.isEmpty()) {
            return null;
        }
        NodePort port = inputMap.get(portName);
        return port != null ? port.getData() : null;
    }

    /**
     * 从输入端口或配置端口获取值（优先输入端口）
     *
     * @param node      节点定义
     * @param inputMap  输入端口映射
     * @param inputPortName  输入端口名称
     * @param configPortName 配置端口名称
     * @return 端口值，都不存在返回 null
     */
    protected Object getDataFromInputOrConfig(DagNode node, Map<String, NodePort> inputMap,
                                              String inputPortName, String configPortName) {
        // 1. 优先从输入端口取值（上游注入）
        Object inputValue = getDataFromInput(inputMap, inputPortName);
        if (inputValue != null) {
            return inputValue;
        }

        // 2. 其次从配置端口取值（用户预设）
        return getNodeConfigValue(node, configPortName, null);

    }
    /**
     * 从输入端口或值端口获取值（优先输入端口）
     *
     * @param node      节点定义
     * @param inputMap  输入端口映射
     * @param inputPortName  输入端口名称
     * @param valuePortName 值端口名称
     * @return 端口值，都不存在返回 null
     */
    protected Object getDataFromInputOrValuePort(DagNode node, Map<String, NodePort> inputMap,
                                                 String inputPortName, String valuePortName) {
        // 1. 优先从输入端口取值（上游注入）
        Object inputValue = getDataFromInput(inputMap, inputPortName);
        if (inputValue != null) {
            return inputValue;
        }

        // 2. 其次从配置端口取值（用户预设）
        return getDataFromValuePorts(node, valuePortName);

    }
    /**
     * 从 inputPorts 取值
     */
    protected Object getDataFromInputPorts(DagNode node, String inputPortName) {
        // 从 inputPorts 取值（用户在画布上填写的预设值）
        return Optional.ofNullable(node.getInputPorts())
                .map(ports -> ports.get(inputPortName))
                .map(NodePort::getData)
                .orElse(null);
    }
    /**
     * 从 valuePorts 取值
     */
    protected Object getDataFromValuePorts(DagNode node, String valuePortName) {
        // 从 valuePorts 取值（用户在画布上填写的预设值）
        return Optional.ofNullable(node.getValuePorts())
                .map(ports -> ports.get(valuePortName))
                .map(NodePort::getData)
                .orElse(null);
    }
    /**
     * 获取输入端口数据类型
     */
    protected List<DataType> getInputPortDataType(DagNode node, String inputPortName) {
        // 从 inputPorts 声明中获取
        return Optional.ofNullable(node.getInputPorts())
                .map(ports -> ports.get(inputPortName))
                .map(NodePort::getDataTypes)
                .orElse(Collections.emptyList());
    }
    /**
     * 获取值端口数据类型
     */
    protected List<DataType> getValuePortDataType(DagNode node, String valuePortName) {
        // 从 valuePorts 声明中获取
        return Optional.ofNullable(node.getValuePorts())
                .map(ports -> ports.get(valuePortName))
                .map(NodePort::getDataTypes)
                .orElse(Collections.emptyList());
    }

    /**
     * 获取输出端口数据类型
     */
    protected List<DataType> getOutputPortDataType(DagNode node, String outputPortName) {
        // 从 outputPorts 声明中获取
        return Optional.ofNullable(node.getOutputPorts())
                .map(ports -> ports.get(outputPortName))
                .map(NodePort::getDataTypes)
                .orElse(Collections.emptyList());
    }

    /**
     * 获取输入端口类型
     */
    protected PortType getInputPortPortType(DagNode node, String inputPortName) {
        // 从 inputPorts 声明中获取
        return Optional.ofNullable(node.getInputPorts())
                .map(ports -> ports.get(inputPortName))
                .map(NodePort::getPortType)
                .orElse(null);
    }
    /**
     * 获取值端口类型
     */
    protected PortType getValuePortPortType(DagNode node, String valuePortName) {
        // 从 valuePorts 声明中获取
        return Optional.ofNullable(node.getValuePorts())
                .map(ports -> ports.get(valuePortName))
                .map(NodePort::getPortType)
                .orElse(null);
    }

    /**
     * 获取输出端口类型
     */
    protected PortType getOutputPortPortType(DagNode node, String outputPortName) {
        // 从 outputPorts 声明中获取
        return Optional.ofNullable(node.getOutputPorts())
                .map(ports -> ports.get(outputPortName))
                .map(NodePort::getPortType)
                .orElse(null);
    }
    /**
     * 获取节点的配置参数
     *
     * @param node 节点定义
     * @return 配置参数映射，不存在返回空映射
     */
    protected Map<String, Object> getNodeConfig(DagNode node) {
        return node.getConfig() != null ? node.getConfig() : null;
    }

    /**
     * 获取节点的指定配置项
     *
     * @param node       节点定义
     * @param configKey  配置键
     * @param defaultVal 默认值
     * @return 配置值，不存在返回默认值
     */
    @SuppressWarnings("unchecked")
    protected <T> T getNodeConfigValue(DagNode node, String configKey, T defaultVal) {
        Map<String, Object> config = getNodeConfig(node);
        if (config == null) {
            return defaultVal;
        }
        return (T) config.getOrDefault(configKey, defaultVal);
    }
}
