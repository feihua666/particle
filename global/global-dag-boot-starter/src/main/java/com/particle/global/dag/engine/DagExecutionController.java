package com.particle.global.dag.engine;

import com.particle.global.dag.runtime.ExecutionContext;

/**
 * DAG 执行控制器扩展点
 * <p>
 * 由上层（如 workflow 编排层）实现并注入到 dag 引擎。
 * dag 引擎在每个节点执行前调用 {@link #shouldContinue(ExecutionContext)}，
 * 查询外部状态（如数据库）决定是否继续执行，从而支持暂停/停止等流程控制。
 * </p>
 *
 * <p>典型实现流程：</p>
 * <ol>
 *   <li>编排层实现此接口，shouldContinue() 内部查询数据库中的执行记录状态</li>
 *   <li>编排层通过 pause()/stop() 更新数据库状态为 PAUSED/STOPPED</li>
 *   <li>dag 引擎在每个 step 执行前调用 shouldContinue()，读到非 RUNNING 状态时中断执行</li>
 * </ol>
 *
 * @author particle
 * @since 2026-04-30
 */
public interface DagExecutionController {

    /**
     * 节点执行前调用，决定是否继续执行
     *
     * @param context 当前执行上下文
     * @return true 继续执行，false 中断执行（暂停或停止）
     */
    boolean shouldContinue(ExecutionContext context);

    /**
     * 执行被中断时回调（暂停或停止时触发）
     * <p>
     * 用于保存上下文快照等清理工作。默认为空操作。
     * </p>
     *
     * @param context 当前执行上下文
     * @param reason  中断原因（如 "PAUSED"、"STOPPED"）
     */
    default void onInterrupted(ExecutionContext context, String reason) {
    }
}
