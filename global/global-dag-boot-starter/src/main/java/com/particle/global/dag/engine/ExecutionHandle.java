package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;

import java.time.LocalDateTime;

/**
 * <p>
 * 执行句柄接口
 * 用于控制DAG执行过程和获取执行状态
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface ExecutionHandle {

    /**
     * 获取执行ID
     * @return 执行ID
     */
    String getExecutionId();

    /**
     * 获取执行状态
     * @return 执行状态
     */
    DagExecutionStatus getStatus();

    /**
     * 获取执行上下文
     * @return 执行上下文
     */
    ExecutionContext getContext();

    /**
     * 暂停执行
     */
    void pause();

    /**
     * 恢复执行
     */
    void resume();

    /**
     * 停止执行
     */
    void stop();

    /**
     * 获取执行持续时间（毫秒）
     * @return 执行持续时间（毫秒）
     */
    long getDurationInMs();

    /**
     * 获取执行开始时间
     * @return 执行开始时间
     */
    LocalDateTime getStartTime();

    /**
     * 获取执行结束时间
     * @return 执行结束时间
     */
    LocalDateTime getEndTime();

    /**
     * 等待执行完成并获取执行结果
     * @param timeoutMs 超时时间（毫秒）
     * @return 执行结果
     */
    default ExecutionResult awaitResult(long timeoutMs) {
        long startTime = System.currentTimeMillis();
        while (!getStatus().isFinal() && (System.currentTimeMillis() - startTime) < timeoutMs) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // 返回当前状态的执行结果
        return new ExecutionResult(
            getExecutionId(),
            getStatus(),
            getStartTime(),
            getEndTime(),
            getDurationInMs(),
            getContext()
        );
    }

    /**
     * 等待执行完成并获取执行结果（使用默认超时时间30秒）
     * @return 执行结果
     */
    default ExecutionResult awaitResult() {
        return awaitResult(30000); // 30秒默认超时
    }
}
