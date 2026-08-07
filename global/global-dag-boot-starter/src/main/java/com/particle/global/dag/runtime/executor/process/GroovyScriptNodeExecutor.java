package com.particle.global.dag.runtime.executor.process;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.*;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.dag.runtime.executor.BaseNodeExecutor;
import com.particle.global.tool.script.GroovyTool;

import javax.script.Bindings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Groovy 脚本节点执行器
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 14:50:00
 */
public class GroovyScriptNodeExecutor extends BaseNodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return NodeTypeConstants.GROOVY_SCRIPT.equalsIgnoreCase(node.getType());
    }

    @Override
    public NodeExecutionResult doExecute(DagNode node, ExecutionContext context, Map<String, NodePort> inputMap) throws Exception {
        try {
            String script = (String) getDataFromValuePorts(node, NodePort.CONTENT_PORT_NAME);
            if (script == null || script.trim().isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("Groovy script content is required"));
            }

            Map<String, Object> bindingsMap = new HashMap<>();
            bindingsMap.put("context", context);
            bindingsMap.put("inputPort", inputMap);

            // 构建 Groovy bindings：context 变量 + inputMap 端口值
            Bindings bindings = GroovyTool.createBindings(bindingsMap);


            // 执行 Groovy 脚本
            Object result = GroovyTool.compileAndEval(script, bindings, true);

            // 包装为 NodeOutput
            NodeOutput output = new NodeOutput();
            List<DataType> outputDataTypes = getOutputPortDataType(node,NodePort.OUTPUT_PORT_NAME);

            PortType outputPortPortType = getOutputPortPortType(node,NodePort.OUTPUT_PORT_NAME);
            output.addPort(NodePort.INPUT_PORT_NAME, result,outputPortPortType, outputDataTypes);

            return NodeExecutionResult.success(output);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }
}
