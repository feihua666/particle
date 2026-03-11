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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 激进规划策略 - 分析DAG并生成并行执行步骤，最大化并行度
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class AggressivePlanningStrategy implements PlanningStrategy {

    @Override
    public DagExecutionPlan plan(DagDefinition dagDefinition, ExecutionContext executionContext, ExecutionOptions options) {
        // 获取并行执行层（可以并行执行的节点分组）
        List<List<DagNode>> parallelLayers = analyzeParallelLayers(dagDefinition);

        // 根据ExecutionOptions过滤节点
        List<List<DagNode>> filteredLayers = filterLayersByOptions(parallelLayers, executionContext, options);

        // 为每个并行层创建一个执行步骤
        List<ExecutionStep> steps = new ArrayList<>();
        for (int i = 0; i < filteredLayers.size(); i++) {
            List<DagNode> layer = filteredLayers.get(i);

            // 只有当层中有节点时才创建步骤
            if (!layer.isEmpty()) {
                // 确定前置依赖步骤（前一个层的所有步骤）
                List<String> dependencies = new ArrayList<>();
                if (i > 0) {
                    dependencies.add("layer-" + (i - 1)); // 前一层
                }

                ExecutionStep step = new ExecutionStep(
                    "layer-" + i,
                    layer,
                    ExecutionMode.PARALLEL,
                    layer.size() // 并行度为该层节点数量
                );

                steps.add(step);
            }
        }

        return new DagExecutionPlan("aggressive-plan-" + System.currentTimeMillis(), steps);
    }

    /**
     * 分析DAG并生成并行执行层
     */
    private List<List<DagNode>> analyzeParallelLayers(DagDefinition dagDefinition) {
        List<List<DagNode>> layers = new ArrayList<>();
        Set<String> processedNodes = new HashSet<>();
        Map<String, Integer> nodeInDegree = new HashMap<>();

        // 计算每个节点的入度
        for (DagNode node : dagDefinition.getNodes()) {
            nodeInDegree.put(node.getId(), dagDefinition.getPredecessorEdgeCount(node.getId()));
        }

        // 重复直到所有节点都被处理
        while (processedNodes.size() < dagDefinition.getNodes().size()) {
            List<DagNode> currentLayer = new ArrayList<>();

            // 找到所有入度为0且未处理的节点
            for (DagNode node : dagDefinition.getNodes()) {
                if (!processedNodes.contains(node.getId()) && nodeInDegree.get(node.getId()) == 0) {
                    currentLayer.add(node);
                }
            }

            if (currentLayer.isEmpty()) {
                // 如果没有入度为0的节点，说明有环
                throw new RuntimeException("DAG contains cycle");
            }

            // 添加当前层到结果
            layers.add(currentLayer);

            // 标记当前层节点为已处理，并减少其后继节点的入度
            for (DagNode node : currentLayer) {
                processedNodes.add(node.getId());

                // 减少后继节点的入度
                for (String successorId : dagDefinition.getSuccessorNodeIds(node.getId())) {
                    nodeInDegree.put(successorId, nodeInDegree.get(successorId) - 1);
                }
            }
        }

        return layers;
    }

    /**
     * 根据ExecutionOptions过滤层
     */
    private List<List<DagNode>> filterLayersByOptions(List<List<DagNode>> layers, ExecutionContext context, ExecutionOptions options) {
        List<List<DagNode>> filteredLayers = new ArrayList<>();

        for (List<DagNode> layer : layers) {
            List<DagNode> filteredLayer = new ArrayList<>();

            for (DagNode node : layer) {
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
                    filteredLayer.add(node);
                }
            }

            // 只有当层中有节点时才添加到结果
            if (!filteredLayer.isEmpty()) {
                filteredLayers.add(filteredLayer);
            }
        }

        return filteredLayers;
    }

    @Override
    public String getName() {
        return "aggressive-planning-strategy";
    }

    @Override
    public String getDescription() {
        return "激进规划策略 - 分析DAG并生成并行执行步骤，最大化并行度";
    }
}
