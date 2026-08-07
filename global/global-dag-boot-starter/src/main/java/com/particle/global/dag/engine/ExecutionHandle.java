package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.DagExecutionStatus;
import com.particle.global.dag.runtime.ExecutionContext;

import java.time.LocalDateTime;

/**
 * 执行句柄接口
 * <p>
 * 用于控制 DAG 执行过程和获取执行状态。
 * execute() 为同步方法，调用返回时 DAG 已执行完毕，
 * 因此本接口主要用于执行过程中的 pause/resume/stop 控制，
 * 以及执行完毕后获取最终上下文和状态信息。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public interface ExecutionHandle {

    /**
     * 获取执行状态
     *
     * @return 执行状态
     */
    DagExecutionStatus getStatus();

    /**
     * 获取执行上下文（完成后可获取最终结果）
     *
     * @return 执行上下文
     */
    ExecutionContext getContext();

    /**
     * 暂停执行（通过 DagExecutionController 落地到数据库）
     */
    void pause();

    /**
     * 恢复执行
     */
    void resume();

    /**
     * 停止执行（通过 DagExecutionController 落地到数据库）
     */
    void stop();

    /**
     * 获取执行开始时间
     *
     * @return 执行开始时间
     */
    LocalDateTime getStartTime();

    /**
     * 获取执行结束时间（执行完成后返回）
     *
     * @return 执行结束时间
     */
    LocalDateTime getEndTime();

    /**
     * 获取执行持续时间（毫秒）
     *
     * @return 执行持续时间（毫秒）
     */
    long getDurationInMs();
}
