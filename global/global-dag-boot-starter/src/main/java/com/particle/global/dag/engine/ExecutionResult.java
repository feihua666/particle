package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;

import java.time.LocalDateTime;

/**
 * <p>
 * 执行结果类 - 表示DAG执行的最终结果
 * </p>
 * <p>
 * 此类专注于存储和表示DAG执行的结果信息，与ExecutionHandle分离，
 * ExecutionHandle负责执行控制和实时状态跟踪，ExecutionResult负责结果表示。
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 14:15:00
 */
public class ExecutionResult {

    /**
     * 执行ID
     */
    private final String executionId;

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

    /**
     * 构造函数
     * @param executionId 执行ID
     * @param status 最终执行状态
     * @param startTime 执行开始时间
     * @param endTime 执行结束时间
     * @param durationInMs 执行持续时间（毫秒）
     * @param context 执行上下文
     */
    public ExecutionResult(String executionId,
                          DagExecutionStatus status,
                          LocalDateTime startTime,
                          LocalDateTime endTime,
                          long durationInMs,
                          ExecutionContext context) {
        this.executionId = executionId;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInMs = durationInMs;
        this.context = context;
    }

    /**
     * 从ExecutionHandle创建ExecutionResult
     * @param handle ExecutionHandle实例
     * @return ExecutionResult实例
     */
    public static ExecutionResult fromHandle(ExecutionHandle handle) {
        return new ExecutionResult(
            handle.getExecutionId(),
            handle.getStatus(),
            handle.getStartTime(),
            handle.getEndTime(),
            handle.getDurationInMs(),
            handle.getContext()
        );
    }

    /**
     * 获取执行ID
     * @return 执行ID
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * 获取最终执行状态
     * @return 最终执行状态
     */
    public DagExecutionStatus getStatus() {
        return status;
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

    /**
     * 获取执行持续时间（毫秒）
     * @return 执行持续时间（毫秒）
     */
    public long getDurationInMs() {
        return durationInMs;
    }

    /**
     * 获取执行上下文
     * @return 执行上下文
     */
    public ExecutionContext getContext() {
        return context;
    }

    /**
     * 检查执行是否成功完成
     * @return 如果执行成功完成则返回true，否则返回false
     */
    public boolean isSuccessful() {
        return DagExecutionStatus.COMPLETED.equals(status);
    }

    /**
     * 检查执行是否失败
     * @return 如果执行失败则返回true，否则返回false
     */
    public boolean isFailed() {
        return DagExecutionStatus.FAILED.equals(status);
    }

    /**
     * 检查执行是否被停止
     * @return 如果执行被停止则返回true，否则返回false
     */
    public boolean isStopped() {
        return DagExecutionStatus.STOPPED.equals(status);
    }
}