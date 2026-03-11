package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * <p>
 * 默认执行句柄实现，提供DAG执行过程的状态跟踪和控制功能
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Getter
public class DefaultExecutionHandle implements ExecutionHandle {

    /**
     * 执行ID
     */
    private final String executionId;

    /**
     * 当前执行状态
     */
    private volatile DagExecutionStatus status;

    /**
     * 开始执行时间
     */
    private final LocalDateTime startTime;

    /**
     * 结束执行时间
     */
    private LocalDateTime endTime;

    /**
     * 执行上下文
     */
    private ExecutionContext context;

    /**
     * 构造函数
     * @param executionId 执行ID
     * @param initialStatus 初始状态
     * @param context 执行上下文
     */
    public DefaultExecutionHandle(String executionId, DagExecutionStatus initialStatus, ExecutionContext context) {
        this.executionId = executionId;
        this.status = initialStatus;
        this.context = context;
        this.startTime = LocalDateTime.now();
    }

    @Override
    public ExecutionContext getContext() {
        return context;
    }

    /**
     * 设置执行上下文
     * @param context 执行上下文
     */
    public void setContext(ExecutionContext context) {
        this.context = context;
    }

    @Override
    public void pause() {
        if (status == DagExecutionStatus.RUNNING) {
            status = DagExecutionStatus.PAUSED;
        }
    }

    @Override
    public void resume() {
        if (status == DagExecutionStatus.PAUSED) {
            status = DagExecutionStatus.RUNNING;
        }
    }

    @Override
    public void stop() {
        status = DagExecutionStatus.STOPPED;
        if (endTime == null) {
            endTime = LocalDateTime.now();
        }
    }

    /**
     * 更新执行状态
     * @param newStatus 新状态
     */
    public void updateStatus(DagExecutionStatus newStatus) {
        this.status = newStatus;
        if (newStatus.isFinal() && endTime == null) {
            this.endTime = LocalDateTime.now();
        }
    }

    @Override
    public long getDurationInMs() {
        if (endTime != null) {
            return java.time.Duration.between(startTime, endTime).toMillis();
        } else {
            return java.time.Duration.between(startTime, LocalDateTime.now()).toMillis();
        }
    }

    /**
     * 获取执行开始时间
     * @return 执行开始时间
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * 获取执行结束时间
     * @return 执行结束时间
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
}
