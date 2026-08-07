package com.particle.global.dag.runtime.executor.constantinput;

import com.particle.global.dag.model.*;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.dag.runtime.executor.BaseNodeExecutor;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 常量输入节点执行器基类
 * </p>
 * <p>
 * 数据节点的特点是：不进行复杂计算，只是透传数据。
 * <ul>
 *   <li>其次从 valuePorts 取值（用户预设）</li>
 *   <li>取到的值直接通过 outputPorts 输出</li>
 * </ul>
 * </p>
 * <p>
 * 子类只需实现 {@link #supports(DagNode)} 指定支持的节点类型即可。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public abstract class BaseConstantInputNodeExecutor extends BaseNodeExecutor {
    @Override
    public NodeExecutionResult doExecute(DagNode node, ExecutionContext context, Map<String, NodePort> inputMap) throws Exception {

        NodeOutput output = new NodeOutput();

        // 确定输出数据类型
        List<DataType> outputDataTypes = getOutputPortDataType(node,NodePort.OUTPUT_PORT_NAME);

        PortType outputPortPortType = getOutputPortPortType(node,NodePort.OUTPUT_PORT_NAME);
        // 从 valuePorts 取值（用户预设）
        Object value = getDataFromValuePorts(node, NodePort.CONTENT_PORT_NAME);

        output.addPort(NodePort.OUTPUT_PORT_NAME, value,outputPortPortType, outputDataTypes);

        return NodeExecutionResult.success(output);
    }
}
