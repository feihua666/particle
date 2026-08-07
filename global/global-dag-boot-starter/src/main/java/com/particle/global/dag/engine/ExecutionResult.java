package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;

import java.time.LocalDateTime;

/**
 * 执行结果类
 * <p>
 * 表示 DAG 执行完成后的最终结果信息。
 * executionId 是业务层概念，由编排层管理，dag 层不持有。
 * </p>
 *
 * @author particle
 * @since 2026-01-12 14:15:00
 */
public class ExecutionResult {

    /**
     * 最终执行状态
     */
    private final DagExecutionStatus status;

    /**
     * 执行开始时间
     */
    private final LocalDateTime startTime;

    /**
     * 执行结束时间
     */
    private final LocalDateTime endTime;

    /**
     * 执行持续时间（毫秒）
     */
    private final long durationInMs;

    /**
     * 执行上下文（包含节点执行结果等）
     */
    private final ExecutionContext context;

    public ExecutionResult(DagExecutionStatus status,
                          LocalDateTime startTime,
                          LocalDateTime endTime,
                          long durationInMs,
                          ExecutionContext context) {
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInMs = durationInMs;
        this.context = context;
    }

    public DagExecutionStatus getStatus() {
        return status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getDurationInMs() {
        return durationInMs;
    }

    public ExecutionContext getContext() {
        return context;
    }

    public boolean isSuccessful() {
        return DagExecutionStatus.COMPLETED.equals(status);
    }

    public boolean isFailed() {
        return DagExecutionStatus.FAILED.equals(status);
    }
}
