package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.util.Map;

/**
 * 延迟节点执行器
 */
public class DelayNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.DELAY.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        Map<String, Object> config = node.getConfig();

        // 获取延迟时间，默认为1秒
        Integer delayMs = (Integer) config.getOrDefault("delayMs", 1000);

        try {
            // 执行延迟
            Thread.sleep(delayMs);

            // 将延迟信息存储到执行上下文
            context.setVariable(node.getId() + "_delay_executed", true);
            context.setVariable(node.getId() + "_delay_time_ms", delayMs);

            Map<String, Object> result = Map.of("delayTimeMs", delayMs, "executed", true);
            return NodeExecutionResult.success(result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NodeExecutionResult.failure(e);
        }
    }
}