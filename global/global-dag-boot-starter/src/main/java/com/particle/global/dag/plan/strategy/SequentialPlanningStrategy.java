package com.particle.global.dag.plan.strategy;

import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.plan.DagExecutionPlan;
import com.particle.global.dag.plan.ExecutionMode;
import com.particle.global.dag.plan.ExecutionStep;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.NodeExecutionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 串行规划策略 - 按照拓扑排序顺序，每个步骤只包含一个节点
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class SequentialPlanningStrategy implements PlanningStrategy {

    @Override
    public DagExecutionPlan plan(DagDefinition dagDefinition, ExecutionContext executionContext, ExecutionOptions options) {
        // 获取拓扑排序后的节点列表
        List<DagNode> orderedNodes = topologicalSort(dagDefinition);

        // 根据ExecutionOptions过滤节点
        List<DagNode> filteredNodes = filterNodesByOptions(orderedNodes, executionContext, options);

        // 为每个节点创建一个执行步骤
        List<ExecutionStep> steps = new ArrayList<>();
        for (int i = 0; i < filteredNodes.size(); i++) {
            DagNode node = filteredNodes.get(i);

            // 确定前置依赖步骤（基于DAG的拓扑关系）
            List<String> dependencies = new ArrayList<>();
            if (i > 0) {
                dependencies.add("step-" + (i - 1)); // 前一个步骤
            }

            ExecutionStep step = new ExecutionStep(
                "step-" + i,
                Collections.singletonList(node),
                ExecutionMode.SEQUENTIAL,
                1
            );

            steps.add(step);
        }

        return new DagExecutionPlan("sequential-plan-" + System.currentTimeMillis(), steps);
    }

    /**
     * 拓扑排序
     */
    private List<DagNode> topologicalSort(DagDefinition dagDefinition) {
        // 简单的拓扑排序实现
        List<DagNode> result = new ArrayList<>();
        List<String> visited = new ArrayList<>();

        // 对每个节点进行深度优先搜索
        for (DagNode node : dagDefinition.getNodes()) {
            if (!visited.contains(node.getId())) {
                topologicalSortUtil(dagDefinition, node.getId(), visited, result);
            }
        }

        return result;
    }

    private void topologicalSortUtil(DagDefinition dagDefinition, String nodeId,
                                   List<String> visited, List<DagNode> result) {
        visited.add(nodeId);

        // 访问所有后继节点
        for (String successorId : dagDefinition.getSuccessorNodeIds(nodeId)) {
            if (!visited.contains(successorId)) {
                topologicalSortUtil(dagDefinition, successorId, visited, result);
            }
        }

        // 添加当前节点到结果
        result.add(dagDefinition.getNodeById(nodeId));
    }

    /**
     * 根据ExecutionOptions过滤节点
     */
    private List<DagNode> filterNodesByOptions(List<DagNode> nodes, ExecutionContext context, ExecutionOptions options) {
        List<DagNode> filteredNodes = new ArrayList<>();

        for (DagNode node : nodes) {
            boolean shouldInclude = true;

            // 跳过已成功的节点
            if (options.isSkipSuccessful()) {
                com.particle.global.dag.runtime.NodeExecution nodeExecution = context.getNodeExecution(node.getId());
                if (nodeExecution != null && nodeExecution.getStatus() == NodeExecutionStatus.SUCCESS) {
                    shouldInclude = false;
                }
            }

            // 只重试失败的节点
            if (options.isRetryFailedOnly()) {
                com.particle.global.dag.runtime.NodeExecution nodeExecution = context.getNodeExecution(node.getId());
                if (nodeExecution != null && nodeExecution.getStatus() != NodeExecutionStatus.FAILED) {
                    shouldInclude = false;
                }
            }

            if (shouldInclude) {
                filteredNodes.add(node);
            }
        }

        return filteredNodes;
    }

    @Override
    public String getName() {
        return "sequential-planning-strategy";
    }

    @Override
    public String getDescription() {
        return "串行规划策略 - 每个步骤只包含一个节点，按拓扑顺序执行";
    }
}
