package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;
import com.particle.global.tool.script.GroovyTool;

import javax.script.Bindings;

/**
 * <p>
 * Groovy脚本节点执行器
 * </p>
 * <p>
 * 用于执行Groovy脚本，支持访问执行上下文中的变量
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 14:50:00
 */
public class GroovyScriptNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        String nodeType = node.getType();
        return NodeTypeConstants.GROOVY_SCRIPT.equalsIgnoreCase(nodeType);
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            var config = node.getConfig();
            if (config == null) {
                return NodeExecutionResult.failure(new IllegalArgumentException("Groovy script node config is null"));
            }

            String script = (String) config.get("script");

            if (script == null || script.trim().isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("Groovy script content is required"));
            }

            // 将执行上下文变量转换为Bindings
            Bindings bindings = GroovyTool.createBindings(context.getVariables());

            // 使用GroovyTool的compileAndEval方法来执行Groovy脚本
            // 第三个参数true表示使用缓存以提高性能
            Object result = GroovyTool.compileAndEval(script, bindings, true);

            return NodeExecutionResult.success(result);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }
}