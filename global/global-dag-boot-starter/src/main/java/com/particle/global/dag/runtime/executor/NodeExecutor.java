package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.NodePort;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * 节点执行器接口
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface NodeExecutor {

    /**
     * 判断是否支持该节点类型
     */
    boolean supports(DagNode node);

    /**
     * 执行节点（端口模式）
     *
     * @param node     节点定义
     * @param context  执行上下文
     * @param inputMap 引擎自动注入的上游端口值，key = 端口名（对应 inputPorts 声明）
     * @return 端口化的执行结果（NodeOutput）
     * @throws Exception 执行异常
     */
    NodeExecutionResult execute(
            DagNode node,
            ExecutionContext context,
            Map<String, NodePort> inputMap
    ) throws Exception;
}
