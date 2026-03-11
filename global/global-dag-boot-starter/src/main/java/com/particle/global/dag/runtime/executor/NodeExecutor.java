package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

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
     * 执行节点
     */
    NodeExecutionResult execute(
            DagNode node,
            ExecutionContext context
    ) throws Exception;
}
