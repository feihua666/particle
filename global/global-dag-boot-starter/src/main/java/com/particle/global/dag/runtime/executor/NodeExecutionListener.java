package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.model.NodeOutput;
import com.particle.global.dag.runtime.ExecutionContext;

/**
 * 节点执行监听器接口
 * <p>
 * 纯事件通知，不可干预执行流程。
 * 在 Interceptor 处理完之后调用。
 * 典型用途：日志、指标采集、调试。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public interface NodeExecutionListener {

    /**
     * 节点开始执行
     */
    default void onNodeStarted(DagNode node, ExecutionContext context) {
    }

    /**
     * 节点执行成功
     */
    default void onNodeSuccess(DagNode node, ExecutionContext context,
                               NodeOutput output) {
    }

    /**
     * 节点执行失败
     */
    default void onNodeFailed(DagNode node, ExecutionContext context,
                              Throwable error) {
    }

    /**
     * 节点被跳过（条件不满足或跳过已完成）
     */
    default void onNodeSkipped(DagNode node, ExecutionContext context) {
    }
}
