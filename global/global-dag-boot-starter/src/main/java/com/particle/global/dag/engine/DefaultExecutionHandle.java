package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * 默认执行句柄实现
 * <p>
 * pause/stop 通过 DagExecutionController 落地到外部存储（如数据库），
 * dag 引擎在每个 step 执行前通过 controller.shouldContinue() 读取状态，
 * 从而真正响应暂停/停止操作。
 * </p>
 * <p>
 * 注意：dag 引擎的 execute() 为同步方法，返回时 DAG 已执行完毕。
 * pause/stop 通常在 DAG 执行过程中由 controller.shouldContinue() 间接触发，
 * 此时执行线程在 dag 引擎内部。handle 本身仍暴露这些方法以备上层使用。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public class DefaultExecutionHandle implements ExecutionHandle {

    private volatile DagExecutionStatus status;
    private final LocalDateTime startTime;
    private volatile LocalDateTime endTime;
    private final ExecutionContext context;
    private final DagExecutionController controller;

    /**
     * 构造函数
     *
     * @param initialStatus 初始状态
     * @param context       执行上下文
     * @param controller    执行控制器（可为 null，表示不启用外部流程控制）
     */
    public DefaultExecutionHandle(DagExecutionStatus initialStatus,
                                   ExecutionContext context,
                                   DagExecutionController controller) {
        this.status = initialStatus;
        this.context = context;
        this.controller = controller;
        this.startTime = LocalDateTime.now();
    }

    @Override
    public DagExecutionStatus getStatus() {
        return status;
    }

    @Override
    public ExecutionContext getContext() {
        return context;
    }

    @Override
    public void pause() {
        if (status == DagExecutionStatus.RUNNING) {
            if (controller != null) {
                controller.onInterrupted(context, "paused by handle");
            }
            // dag 层没有 PAUSED 状态，标记为 FAILED（执行被中断）
            // PAUSED 是 workflow 层的概念，由 callback 管理
            status = DagExecutionStatus.FAILED;
            endTime = LocalDateTime.now();
        }
    }

    @Override
    public void resume() {
        // dag 层的 resume 由 workflow 层重新调用 execute() 实现
        // dag handle 本身不支持恢复（因为同步执行已结束）
    }

    @Override
    public void stop() {
        if (controller != null) {
            controller.onInterrupted(context, "stopped by handle");
        }
        // dag 层没有 STOPPED 状态，标记为 FAILED
        status = DagExecutionStatus.FAILED;
        if (endTime == null) {
            endTime = LocalDateTime.now();
        }
    }

    /**
     * 更新执行状态
     *
     * @param newStatus 新状态
     */
    public void updateStatus(DagExecutionStatus newStatus) {
        this.status = newStatus;
        if (newStatus.isFinal() && endTime == null) {
            this.endTime = LocalDateTime.now();
        }
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public long getDurationInMs() {
        if (endTime != null) {
            return Duration.between(startTime, endTime).toMillis();
        } else {
            return Duration.between(startTime, LocalDateTime.now()).toMillis();
        }
    }
}
