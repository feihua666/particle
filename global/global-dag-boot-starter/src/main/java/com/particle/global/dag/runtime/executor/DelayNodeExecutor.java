package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.util.Map;

/**
 * <p>
 * 延迟节点执行器
 * </p>
 * <p>
 * 用于在DAG执行中添加延迟，支持配置延迟时间
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:45:00
 */
public class DelayNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        String nodeType = node.getType();
        return NodeTypeConstants.DELAY.equalsIgnoreCase(nodeType);
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            Map<String, Object> config = node.getConfig();
            long delayMs = 1000L; // 默认1秒

            if (config != null) {
                Object delayObj = config.get("delay");
                if (delayObj != null) {
                    if (delayObj instanceof Number) {
                        delayMs = ((Number) delayObj).longValue();
                    } else {
                        delayMs = Long.parseLong(delayObj.toString());
                    }
                }
            }

            // 等待指定时间
            Thread.sleep(delayMs);

            return NodeExecutionResult.success("Delayed for " + delayMs + " milliseconds");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NodeExecutionResult.failure(e);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }
}
