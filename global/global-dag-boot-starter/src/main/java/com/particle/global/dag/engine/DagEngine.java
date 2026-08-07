package com.particle.global.dag.engine;

import com.particle.global.dag.exception.DAGException;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.executor.NodeExecutionInterceptor;
import com.particle.global.dag.runtime.executor.NodeExecutionListener;
import com.particle.global.dag.runtime.executor.NodeExecutorRegistry;

/**
 * DAG 引擎接口
 * <p>
 * execute() 为同步方法，阻塞调用线程直到 DAG 执行完成。
 * 调用方如需异步，自行包装 CompletableFuture。
 * 内部线程池仅用于并行执行 DAG 中同层的多个节点。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public interface DagEngine {

    /**
     * 使用执行选项同步执行 DAG，阻塞调用线程直到执行完成
     *
     * @param dagDefinition DAG定义
     * @param options       执行选项
     * @param context       执行上下文
     * @return 执行句柄
     */
    ExecutionHandle execute(DagDefinition dagDefinition,
                            ExecutionOptions options,
                            ExecutionContext context) throws DAGException;

    /**
     * 同步执行 DAG（全量，从根节点）
     *
     * @param dagDefinition DAG定义
     * @param context       执行上下文
     * @return 执行句柄
     */
    default ExecutionHandle execute(DagDefinition dagDefinition,
                                    ExecutionContext context) throws DAGException {
        return execute(dagDefinition, ExecutionOptions.full(), context);
    }

    /**
     * 校验 DAG 定义（包含节点ID唯一性、边有效性、环检测）
     *
     * @param dagDefinition DAG定义
     * @return 验证结果
     */
    ValidationResult validate(DagDefinition dagDefinition);

    /**
     * 获取节点执行器注册表
     *
     * @return 节点执行器注册表
     */
    NodeExecutorRegistry getNodeExecutorRegistry();

    /**
     * 添加节点执行拦截器（可修改/中断执行流程）
     *
     * @param interceptor 拦截器
     */
    void addInterceptor(NodeExecutionInterceptor interceptor);

    /**
     * 添加节点执行监听器（纯事件通知）
     *
     * @param listener 监听器
     */
    void addListener(NodeExecutionListener listener);
}
