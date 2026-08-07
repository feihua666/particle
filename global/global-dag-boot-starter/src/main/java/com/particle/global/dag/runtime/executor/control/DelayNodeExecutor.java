package com.particle.global.dag.runtime.executor.control;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.*;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.dag.runtime.executor.BaseNodeExecutor;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 延迟节点执行器
 * </p>
 * <p>
 * 端口模式：
 * <ul>
 *   <li>inputPorts: input(可选, 来自上游，透传)</li>
 *   <li>outputPorts: output(ANY) — 如果有上游输入则透传，否则输出延迟信息</li>
 * </ul>
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:45:00
 */
public class DelayNodeExecutor extends BaseNodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return NodeTypeConstants.DELAY.equalsIgnoreCase(node.getType());
    }

    @Override
    public NodeExecutionResult doExecute(DagNode node, ExecutionContext context, Map<String, NodePort> inputMap) throws Exception {
        try {
            // delay: 优先从输入取值，否则从值端口取值
            long delayMs = 1000L;

            Object delayObj = getDataFromInput(inputMap, NodePort.INPUT_PORT_NAME);
            if (delayObj != null) {
                try {
                    delayMs = pasrseLong(delayObj);
                } catch (Exception e) {
                    // 说明输出不对，忽略
                }
            }
            delayObj = getDataFromInputPorts(node, NodePort.CONTENT_PORT_NAME);
            if (delayObj != null) {
                delayMs = pasrseLong(delayObj);
            }

            Thread.sleep(delayMs);

            // 确定输出数据类型
            List<DataType> outputDataTypes = getOutputPortDataType(node,NodePort.OUTPUT_PORT_NAME);

            PortType outputPortPortType = getOutputPortPortType(node,NodePort.OUTPUT_PORT_NAME);
            // 输出延迟毫秒数
            NodeOutput output = new NodeOutput();
            output.addPort(NodePort.INPUT_PORT_NAME, delayMs, outputPortPortType, outputDataTypes);

            return NodeExecutionResult.success(output);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NodeExecutionResult.failure(e);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }
    /**
     * 转换为Long
     * @param value
     * @return
     */
    protected Long pasrseLong(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else {
            return Long.parseLong(value.toString());
        }
    }
}
