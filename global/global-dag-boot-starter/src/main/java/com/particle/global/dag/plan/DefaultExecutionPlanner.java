package com.particle.global.dag.plan;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dag.model.DagDefinition;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.options.ExecutionOptions;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecution;
import com.particle.global.dag.runtime.NodeExecutionStatus;

import java.util.*;

/**
 * 默认执行计划器
 * <p>
 * 使用拓扑分层算法（Kahn 算法）将 DAG 拆分为多个执行层。
 * 同层节点之间无依赖关系，可并行执行；层间串行。
 * 引擎根据每层的节点数量自动决定串行或并行执行。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public class DefaultExecutionPlanner implements ExecutionPlanner {

    @Override
    public DagExecutionPlan plan(DagDefinition dagDefinition, ExecutionContext context, ExecutionOptions options) {
        // 1. 拓扑分层
        List<List<DagNode>> layers = analyzeLayers(dagDefinition);

        // 2. 根据 ExecutionOptions 过滤节点
        List<List<DagNode>> filteredLayers = filterByOptions(layers, context, options);

        // 3. 构建执行步骤
        List<ExecutionStep> steps = new ArrayList<>();
        for (int i = 0; i < filteredLayers.size(); i++) {
            List<DagNode> layer = filteredLayers.get(i);
            if (!layer.isEmpty()) {
                steps.add(new ExecutionStep("layer-" + i, layer));
            }
        }

        return new DagExecutionPlan("plan-" + System.currentTimeMillis(), steps);
    }

    /**
     * 拓扑分层（Kahn 算法）
     * <p>
     * 将 DAG 按入度分层：入度为 0 的节点为第一层，
     * 处理完第一层后更新后继节点的入度，新的入度为 0 的节点为第二层，依此类推。
     * 同层节点之间没有依赖关系，可以并行执行。
     * </p>
     */
    private List<List<DagNode>> analyzeLayers(DagDefinition dagDefinition) {
        List<List<DagNode>> layers = new ArrayList<>();
        Set<String> processedNodes = new HashSet<>();
        Map<String, Integer> nodeInDegree = new HashMap<>();

        // 计算每个节点的入度
        for (DagNode node : dagDefinition.getNodes()) {
            nodeInDegree.put(node.getId(), dagDefinition.getPredecessorEdgeCount(node.getId()));
        }

        while (processedNodes.size() < dagDefinition.getNodes().size()) {
            List<DagNode> currentLayer = new ArrayList<>();

            // 找到所有入度为 0 且未处理的节点
            for (DagNode node : dagDefinition.getNodes()) {
                if (!processedNodes.contains(node.getId()) && nodeInDegree.get(node.getId()) == 0) {
                    currentLayer.add(node);
                }
            }

            if (currentLayer.isEmpty()) {
                // 没有入度为 0 的节点说明有环（正常流程不会到这，validate 已检测过）
                throw new RuntimeException("DAG contains cycle (should have been caught by validation)");
            }

            layers.add(currentLayer);

            // 标记当前层节点为已处理，并减少后继节点的入度
            for (DagNode node : currentLayer) {
                processedNodes.add(node.getId());
                for (String successorId : dagDefinition.getSuccessorNodeIds(node.getId())) {
                    nodeInDegree.put(successorId, nodeInDegree.get(successorId) - 1);
                }
            }
        }

        return layers;
    }

    /**
     * 根据 ExecutionOptions 过滤节点
     */
    private List<List<DagNode>> filterByOptions(List<List<DagNode>> layers,
                                                 ExecutionContext context,
                                                 ExecutionOptions options) {
        List<List<DagNode>> filteredLayers = new ArrayList<>();

        for (List<DagNode> layer : layers) {
            List<DagNode> filteredLayer = new ArrayList<>();

            for (DagNode node : layer) {
                boolean shouldInclude = true;

                if (options.isSkipSuccessful()) {
                    NodeExecution nodeExecution = context.getNodeExecution(node.getId());
                    if (nodeExecution != null && nodeExecution.getStatus() == NodeExecutionStatus.SUCCESS) {
                        shouldInclude = false;
                    }
                }

                if (options.isRetryFailedOnly()) {
                    NodeExecution nodeExecution = context.getNodeExecution(node.getId());
                    if (nodeExecution != null && nodeExecution.getStatus() != NodeExecutionStatus.FAILED) {
                        shouldInclude = false;
                    }
                }

                if (CollectionUtil.isNotEmpty(options.getSkippedNodeRoles())) {
                    shouldInclude = !options.getSkippedNodeRoles().contains(node.getRole());
                }

                if (shouldInclude) {
                    filteredLayer.add(node);
                }
            }

            if (!filteredLayer.isEmpty()) {
                filteredLayers.add(filteredLayer);
            }
        }

        return filteredLayers;
    }
}
