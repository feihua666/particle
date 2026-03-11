package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 条件判断节点执行器
 */
public class ConditionNodeExecutor implements NodeExecutor {

    @Override
    public boolean supports(DagNode node) {
        return CrawlerDagConstants.NodeType.CONDITION.equals(node.getType());
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        Map<String, Object> config = node.getConfig();

        String conditionType = (String) config.get("conditionType"); // "expression", "valueCheck", "regex"
        String leftOperand = (String) config.get("leftOperand"); // 左操作数，通常是变量名
        String operator = (String) config.get("operator"); // 操作符
        Object rightOperand = config.get("rightOperand"); // 右操作数

        try {
            boolean result = evaluateCondition(conditionType, leftOperand, operator, rightOperand, context);

            // 将条件判断结果存储到执行上下文
            context.setVariable(node.getId() + "_condition_result", result);
            context.setVariable(node.getId() + "_evaluated", true);

            Map<String, Object> evaluationResult = Map.of(
                    "conditionType", conditionType,
                    "leftOperand", leftOperand,
                    "operator", operator,
                    "rightOperand", rightOperand,
                    "result", result
            );

            return NodeExecutionResult.success(evaluationResult);

        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }

    /**
     * 评估条件
     */
    private boolean evaluateCondition(String conditionType, String leftOperand, String operator, Object rightOperand, ExecutionContext context) {
        switch (conditionType != null ? conditionType.toLowerCase() : "expression") {
            case "expression":
                return evaluateExpression(leftOperand, operator, rightOperand, context);
            case "valuecheck":
                return evaluateValueCheck(leftOperand, operator, rightOperand, context);
            case "regex":
                return evaluateRegex(leftOperand, rightOperand, context);
            default:
                return false;
        }
    }

    /**
     * 评估表达式条件
     */
    private boolean evaluateExpression(String leftOperand, String operator, Object rightOperand, ExecutionContext context) {
        // 从执行上下文中获取左操作数的值
        Object leftValue = resolveVariable(leftOperand, context);

        if (leftValue == null && rightOperand == null) {
            return true; // 两个都是null认为相等
        }

        if (leftValue == null || rightOperand == null) {
            return false; // 一个null一个非null认为不等
        }

        return compareValues(leftValue, operator, rightOperand);
    }

    /**
     * 评估值检查条件
     */
    private boolean evaluateValueCheck(String variableName, String operator, Object expectedValue, ExecutionContext context) {
        Object actualValue = resolveVariable(variableName, context);

        switch (operator.toLowerCase()) {
            case "exists":
                return actualValue != null;
            case "not_exists":
                return actualValue == null;
            case "not_empty":
                if (actualValue instanceof String) {
                    return !((String) actualValue).isEmpty();
                } else if (actualValue instanceof java.util.Collection) {
                    return !((java.util.Collection<?>) actualValue).isEmpty();
                } else {
                    return actualValue != null;
                }
            case "empty":
                if (actualValue instanceof String) {
                    return ((String) actualValue).isEmpty();
                } else if (actualValue instanceof java.util.Collection) {
                    return ((java.util.Collection<?>) actualValue).isEmpty();
                } else {
                    return actualValue == null;
                }
            default:
                return compareValues(actualValue, operator, expectedValue);
        }
    }

    /**
     * 评估正则表达式条件
     */
    private boolean evaluateRegex(String variableName, Object patternObj, ExecutionContext context) {
        Object value = resolveVariable(variableName, context);
        String patternStr = patternObj != null ? patternObj.toString() : "";

        if (value == null || patternStr.isEmpty()) {
            return false;
        }

        try {
            Pattern pattern = Pattern.compile(patternStr);
            return pattern.matcher(value.toString()).find();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解析变量
     */
    private Object resolveVariable(String variableName, ExecutionContext context) {
        // 首先尝试从执行上下文中获取变量
        Object value = context.getVariable(variableName);

        // 如果找不到，尝试从共享数据中获取
        if (value == null) {
            value = context.getSharedData().get(variableName);
        }

        // 如果变量名以"node_result_"开头，尝试获取节点结果
        if (value == null && variableName.startsWith("node_result_")) {
            String nodeId = variableName.substring("node_result_".length());
            value = context.getVariable(nodeId + "_result");
        }

        // 如果变量名以"extracted_"开头，尝试获取提取数据
        if (value == null && variableName.startsWith("extracted_")) {
            String nodeId = variableName.substring("extracted_".length());
            value = context.getVariable(nodeId + "_extracted_data");
        }

        return value;
    }

    /**
     * 比较值
     */
    private boolean compareValues(Object leftValue, String operator, Object rightValue) {
        if (leftValue == null || rightValue == null) {
            return false;
        }

        switch (operator.toLowerCase()) {
            case "eq":
            case "==":
                return leftValue.toString().equals(rightValue.toString());
            case "ne":
            case "!=":
                return !leftValue.toString().equals(rightValue.toString());
            case "gt":
            case ">":
                return compareNumbers(leftValue, rightValue) > 0;
            case "ge":
            case ">=":
                return compareNumbers(leftValue, rightValue) >= 0;
            case "lt":
            case "<":
                return compareNumbers(leftValue, rightValue) < 0;
            case "le":
            case "<=":
                return compareNumbers(leftValue, rightValue) <= 0;
            case "contains":
                return leftValue.toString().contains(rightValue.toString());
            case "starts_with":
                return leftValue.toString().startsWith(rightValue.toString());
            case "ends_with":
                return leftValue.toString().endsWith(rightValue.toString());
            default:
                return false;
        }
    }

    /**
     * 比较数值
     */
    private int compareNumbers(Object leftValue, Object rightValue) {
        try {
            double leftNum = Double.parseDouble(leftValue.toString());
            double rightNum = Double.parseDouble(rightValue.toString());
            return Double.compare(leftNum, rightNum);
        } catch (NumberFormatException e) {
            // 如果不能转换为数字，则按字符串比较
            return leftValue.toString().compareTo(rightValue.toString());
        }
    }
}