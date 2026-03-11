package com.particle.global.dag.engine;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.executor.NodeExecutorRegistry;

/**
 * <p>
 * DAG引擎接口
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public interface DagEngine {

    /**
     * 全量执行 DAG（从根节点）
     * @param dagDefinition DAG定义
     * @param context 执行上下文
     * @return 执行句柄
     */
    ExecutionHandle execute(
            DagDefinition dagDefinition,
            ExecutionContext context
    );

    /**
     * 使用执行选项执行 DAG
     * @param dagDefinition DAG定义
     * @param options 执行选项
     * @param context 执行上下文
     * @return 执行句柄
     */
    ExecutionHandle execute(
            DagDefinition dagDefinition,
            ExecutionOptions options,
            ExecutionContext context
    );

    /**
     * 校验 DAG 定义
     * @param dagDefinition DAG定义
     * @return 验证结果
     */
    ValidationResult validate(DagDefinition dagDefinition);

    /**
     * 获取节点执行器注册表
     * @return 节点执行器注册表
     */
    NodeExecutorRegistry getNodeExecutorRegistry();

    /**
     * 设置自定义线程池
     * @param executorService 自定义线程池
     */
    void setExecutorService(java.util.concurrent.ExecutorService executorService);

    /**
     * 根据执行ID获取执行句柄
     * @param executionId 执行ID
     * @return 执行句柄，如果不存在则返回null
     */
    ExecutionHandle getExecutionHandle(String executionId);
}
