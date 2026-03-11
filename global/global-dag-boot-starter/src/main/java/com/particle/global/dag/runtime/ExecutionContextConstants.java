package com.particle.global.dag.runtime;

/**
 * <p>
 * 执行上下文变量常量
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 13:23:00
 */
public class ExecutionContextConstants {

    /**
     * 表示节点已执行的变量后缀
     */
    public static final String EXECUTED_SUFFIX = "_executed";

    /**
     * 表示节点执行结果的变量后缀
     */
    public static final String RESULT_SUFFIX = "_result";

    /**
     * 表示节点已跳过的变量后缀
     */
    public static final String SKIPPED_SUFFIX = "_skipped";

    /**
     * 获取节点执行标识变量名
     * @param nodeId 节点ID
     * @return 节点执行标识变量名
     */
    public static String getNodeExecutedVariableName(String nodeId) {
        return nodeId + EXECUTED_SUFFIX;
    }

    /**
     * 获取节点输出变量名（直接使用节点ID作为键）
     * @param nodeId 节点ID
     * @return 节点输出变量名
     */
    public static String getNodeOutputVariableName(String nodeId) {
        return nodeId; // Direct node ID is used as the variable name
    }

    /**
     * 获取节点结果变量名（后缀形式）
     * @param nodeId 节点ID
     * @return 节点结果变量名
     */
    public static String getNodeResultVariableName(String nodeId) {
        return nodeId + RESULT_SUFFIX;
    }

    /**
     * 获取节点跳过标识变量名
     * @param nodeId 节点ID
     * @return 节点跳过标识变量名
     */
    public static String getNodeSkippedVariableName(String nodeId) {
        return nodeId + SKIPPED_SUFFIX;
    }
}