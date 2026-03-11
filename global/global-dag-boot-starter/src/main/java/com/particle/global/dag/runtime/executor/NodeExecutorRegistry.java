package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.model.DagNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 节点执行器注册表
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class NodeExecutorRegistry {

    private final List<NodeExecutor> executors = new ArrayList<>();

    public void register(NodeExecutor executor) {
        executors.add(0, executor); // 添加到开头，确保新注册的优先级更高
    }

    public NodeExecutor getExecutor(DagNode node) {
        for (NodeExecutor executor : executors) {
            if (executor.supports(node)) {
                return executor;
            }
        }
        // 如果找不到合适的执行器，抛出异常
        throw new IllegalArgumentException("No executor found for node type: " + node.getType() + ", node id: " + node.getId());
    }
}
