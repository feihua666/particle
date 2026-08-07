package com.particle.global.workflow.listener;

import com.particle.global.workflow.event.WorkflowExecutionEvent;

/**
 * 工作流执行事件监听器接口（可选扩展）
 * <p>
 * 纯事件通知，不可干预执行流程。
 * 典型用途：日志、指标采集、调试。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public interface WorkflowExecutionListener {

    /**
     * 接收执行事件
     *
     * @param event 执行事件
     */
    default void onEvent(WorkflowExecutionEvent event) {
    }
}
