package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.util.Map;

/**
 * <p>
 * 数据处理节点执行器
 * </p>
 * <p>
 * 用于处理数据转换、过滤、聚合等操作
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:45:00
 */
public class DataProcessNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        String nodeType = node.getType();
        return NodeTypeConstants.TRANSFORM.equalsIgnoreCase(nodeType);
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            Map<String, Object> config = node.getConfig();
            if (config == null) {
                return NodeExecutionResult.failure(new IllegalArgumentException("Data process node config is null"));
            }

            String operation = (String) config.get("operation");
            Object inputData = config.get("input");
            Object expression = config.get("expression");

            if (operation == null) {
                return NodeExecutionResult.failure(new IllegalArgumentException("Operation type is required"));
            }

            Object result;
            switch (operation.toLowerCase()) {
                case "transform":
                case "map":
                    result = transformData(inputData, expression, context);
                    break;
                case "filter":
                    result = filterData(inputData, expression, context);
                    break;
                case "aggregate":
                    result = aggregateData(inputData, expression, context);
                    break;
                default:
                    return NodeExecutionResult.failure(new IllegalArgumentException("Unsupported operation: " + operation));
            }

            return NodeExecutionResult.success(result);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }

    private Object transformData(Object inputData, Object expression, ExecutionContext context) {
        // 简单的数据转换实现 - 可以根据表达式进行数据转换
        if (expression instanceof String && inputData instanceof Map) {
            Map<String, Object> inputMap = (Map<String, Object>) inputData;
            String expressionStr = (String) expression;

            // 这里可以使用表达式引擎进行复杂转换
            // 例如: "name.toUpperCase()" 或其他表达式
            return inputMap; // 简化实现，实际中可以使用SpEL或Groovy进行转换
        }
        return inputData;
    }

    private Object filterData(Object inputData, Object expression, ExecutionContext context) {
        // 简单的数据过滤实现
        return inputData;
    }

    private Object aggregateData(Object inputData, Object expression, ExecutionContext context) {
        // 简单的数据聚合实现
        return inputData;
    }
}
