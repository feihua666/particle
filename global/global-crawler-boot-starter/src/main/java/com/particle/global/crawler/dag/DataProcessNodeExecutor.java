package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.util.*;

/**
 * 数据处理节点执行器
 */
public class DataProcessNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.DATA_PROCESS.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            Map<String, Object> config = node.getConfig();

            String sourceNodeId = (String) config.get("sourceNodeId");
            Object sourceData = null;

            if (sourceNodeId != null) {
                // 从执行上下文中获取源节点的结果
                sourceData = context.getVariable(sourceNodeId + "_extracted_data");
                if (sourceData == null) {
                    sourceData = context.getVariable(sourceNodeId + "_result");
                }
            } else {
                // 如果没有指定源节点，尝试从配置中获取数据
                sourceData = config.get("inputData");
            }

            if (sourceData == null) {
                return NodeExecutionResult.failure(new IllegalArgumentException("未找到要处理的数据"));
            }

            // 获取处理规则
            List<Map<String, Object>> processRules = (List<Map<String, Object>>) config.get("processRules");

            Object processedData = sourceData;

            if (processRules != null) {
                for (Map<String, Object> rule : processRules) {
                    String operation = (String) rule.get("operation");
                    String fieldName = (String) rule.get("fieldName");
                    Object value = rule.get("value");

                    processedData = applyOperation(processedData, operation, fieldName, value);
                }
            }

            // 将处理后的数据存储到执行上下文
            context.setVariable(node.getId() + "_processed_data", processedData);

            return NodeExecutionResult.success(processedData);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }

    /**
     * 应用处理操作
     */
    @SuppressWarnings("unchecked")
    private Object applyOperation(Object data, String operation, String fieldName, Object value) {
        if (!(data instanceof Map)) {
            return data; // 如果不是Map类型，直接返回原数据
        }

        Map<String, Object> mapData = (Map<String, Object>) data;
        Map<String, Object> result = new HashMap<>(mapData);

        switch (operation.toLowerCase()) {
            case "rename":
                // 重命名字段
                if (fieldName != null && value instanceof String) {
                    Object fieldValue = result.remove(fieldName);
                    if (fieldValue != null) {
                        result.put((String) value, fieldValue);
                    }
                }
                break;

            case "transform":
                // 转换字段值
                if (fieldName != null && value instanceof String) {
                    Object fieldValue = result.get(fieldName);
                    if (fieldValue != null) {
                        result.put(fieldName, applyTransform(fieldValue, (String) value));
                    }
                }
                break;

            case "filter":
                // 过滤数据（仅适用于列表类型的字段）
                if (fieldName != null && value instanceof Map) {
                    Object fieldValue = result.get(fieldName);
                    if (fieldValue instanceof List) {
                        List<Object> originalList = (List<Object>) fieldValue;
                        Map<String, Object> filterConfig = (Map<String, Object>) value;
                        String filterField = (String) filterConfig.get("field");
                        String filterOperator = (String) filterConfig.get("operator");
                        Object filterValue = filterConfig.get("value");

                        List<Object> filteredList = new java.util.ArrayList<>();
                        for (Object item : originalList) {
                            if (item instanceof Map) {
                                Map<String, Object> mapItem = (Map<String, Object>) item;
                                if (applyFilter(mapItem.get(filterField), filterOperator, filterValue)) {
                                    filteredList.add(item);
                                }
                            }
                        }

                        result.put(fieldName, filteredList);
                    }
                }
                break;

            case "join":
                // 连接字符串（仅适用于字符串列表）
                if (fieldName != null && value instanceof String) {
                    Object fieldValue = result.get(fieldName);
                    if (fieldValue instanceof List) {
                        List<?> stringList = (List<?>) fieldValue;
                        String joined = String.join((String) value, stringList.stream()
                                .map(Object::toString)
                                .toArray(String[]::new));
                        result.put(fieldName, joined);
                    }
                }
                break;

            case "calculate":
                // 计算操作（例如求和、平均值等）
                if (fieldName != null && value instanceof String) {
                    Object fieldValue = result.get(fieldName);
                    if (fieldValue instanceof List) {
                        List<?> numberList = (List<?>) fieldValue;
                        Double calculatedValue = applyCalculation(numberList, (String) value);
                        result.put(fieldName, calculatedValue);
                    }
                }
                break;

            default:
                // 未知操作，不改变数据
                break;
        }

        return result;
    }

    /**
     * 应用转换操作
     */
    private Object applyTransform(Object value, String transformType) {
        if (value == null) {
            return null;
        }

        String stringValue = value.toString();

        switch (transformType.toLowerCase()) {
            case "uppercase":
                return stringValue.toUpperCase();
            case "lowercase":
                return stringValue.toLowerCase();
            case "trim":
                return stringValue.trim();
            case "length":
                return stringValue.length();
            case "toInt":
                try {
                    return Integer.parseInt(stringValue);
                } catch (NumberFormatException e) {
                    return value; // 转换失败时返回原值
                }
            case "toDouble":
                try {
                    return Double.parseDouble(stringValue);
                } catch (NumberFormatException e) {
                    return value; // 转换失败时返回原值
                }
            case "toDate":
                // 这里可以添加日期转换逻辑
                return stringValue;
            default:
                return value;
        }
    }

    /**
     * 应用过滤条件
     */
    private boolean applyFilter(Object fieldValue, String operator, Object filterValue) {
        if (fieldValue == null || filterValue == null) {
            return false;
        }

        String strFieldValue = fieldValue.toString();
        String strFilterValue = filterValue.toString();

        switch (operator.toLowerCase()) {
            case "equals":
                return strFieldValue.equals(strFilterValue);
            case "contains":
                return strFieldValue.contains(strFilterValue);
            case "startsWith":
                return strFieldValue.startsWith(strFilterValue);
            case "endsWith":
                return strFieldValue.endsWith(strFilterValue);
            case "greaterThan":
                try {
                    double fieldNum = Double.parseDouble(strFieldValue);
                    double filterNum = Double.parseDouble(strFilterValue);
                    return fieldNum > filterNum;
                } catch (NumberFormatException e) {
                    return false;
                }
            case "lessThan":
                try {
                    double fieldNum = Double.parseDouble(strFieldValue);
                    double filterNum = Double.parseDouble(strFilterValue);
                    return fieldNum < filterNum;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return false;
        }
    }

    /**
     * 应用计算操作
     */
    private Double applyCalculation(List<?> numberList, String calculationType) {
        List<Double> numericValues = new java.util.ArrayList<>();
        for (Object item : numberList) {
            try {
                numericValues.add(Double.parseDouble(item.toString()));
            } catch (NumberFormatException e) {
                numericValues.add(0.0); // 无法转换为数字的项视为0
            }
        }

        switch (calculationType.toLowerCase()) {
            case "sum":
                double sum = 0.0;
                for (Double num : numericValues) {
                    sum += num;
                }
                return sum;
            case "average":
                if (numericValues.isEmpty()) {
                    return 0.0;
                }
                double total = 0.0;
                for (Double num : numericValues) {
                    total += num;
                }
                return total / numericValues.size();
            case "max":
                if (numericValues.isEmpty()) {
                    return 0.0;
                }
                double max = numericValues.get(0);
                for (Double num : numericValues) {
                    if (num > max) {
                        max = num;
                    }
                }
                return max;
            case "min":
                if (numericValues.isEmpty()) {
                    return 0.0;
                }
                double min = numericValues.get(0);
                for (Double num : numericValues) {
                    if (num < min) {
                        min = num;
                    }
                }
                return min;
            default:
                return 0.0;
        }
    }
}