package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.NodeOutput;
import com.particle.global.dag.model.NodePort;
import com.particle.global.dag.runtime.ExecutionContext;

import java.util.Map;

/**
 * 节点执行拦截器接口
 * <p>
 * 可在节点执行前后介入，修改执行状态或中断执行流程。
 * 典型用途：编排层通过拦截器实现持久化回调。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public interface NodeExecutionInterceptor {

    /**
     * 节点执行前
     * <p>
     * 可修改 inputMap 中的数据或设置 context attribute。
     *
     * @param node     当前节点
     * @param context  执行上下文
     * @param inputMap 输入端口映射（可修改）
     */
    default void beforeNode(DagNode node, ExecutionContext context,
                            Map<String, NodePort> inputMap) {
    }

    /**
     * 节点执行成功后
     * <p>
     * 可修改 output 中的数据。
     *
     * @param node    当前节点
     * @param context 执行上下文
     * @param output  节点输出（可修改）
     */
    default void afterNodeSuccess(DagNode node, ExecutionContext context,
                                  NodeOutput output) {
    }

    /**
     * 节点执行失败后
     * <p>
     * 可决定是否继续向上抛出异常。
     *
     * @param node   当前节点
     * @param context 执行上下文
     * @param error  异常
     * @return true 继续抛出异常（默认），false 吞掉异常继续执行
     */
    default boolean afterNodeFailed(DagNode node, ExecutionContext context,
                                    Throwable error) {
        return true;
    }
}
