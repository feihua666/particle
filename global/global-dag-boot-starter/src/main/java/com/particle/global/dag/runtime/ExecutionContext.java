package com.particle.global.dag.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行上下文
 * <p>
 * 用于在 DAG 执行过程中传递和共享数据，提供变量存储和共享数据存储功能，
 * 支持跨节点的数据传递和状态管理。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public class ExecutionContext {

    /**
     * 存储执行过程中的变量（用于节点间的数据传递和中间结果存储）
     */
    private final Map<String, Object> variables = new HashMap<>();

    /**
     * 存储执行过程中的共享数据（用于跨节点的全局数据共享）
     */
    private final Map<String, Object> sharedData = new HashMap<>();

    /**
     * 存储节点执行状态（用于跟踪各节点的执行情况）
     */
    private final Map<String, NodeExecution> nodeExecutions = new HashMap<>();

    /**
     * 通用属性存储
     * 用于编排层存储业务上下文（如 callbackId、definitionId 等），不与 variables 混用
     */
    private final Map<String, Object> attributes = new HashMap<>();

    /**
     * 默认构造函数
     */
    public ExecutionContext() {}

    /**
     * 设置变量
     */
    public void setVariable(String key, Object value) {
        variables.put(key, value);
    }

    /**
     * 获取变量
     */
    public Object getVariable(String key) {
        return variables.get(key);
    }

    /**
     * 获取变量，带默认值
     */
    public Object getVariable(String key, Object defaultValue) {
        return variables.getOrDefault(key, defaultValue);
    }

    /**
     * 设置共享数据
     */
    public void setSharedData(String key, Object value) {
        sharedData.put(key, value);
    }

    /**
     * 获取共享数据
     */
    public Object getSharedData(String key) {
        return sharedData.get(key);
    }

    /**
     * 获取所有变量
     */
    public Map<String, Object> getVariables() {
        return new HashMap<>(variables);
    }

    /**
     * 获取所有共享数据
     */
    public Map<String, Object> getSharedData() {
        return new HashMap<>(sharedData);
    }

    /**
     * 设置通用属性
     */
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * 获取通用属性
     */
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * 获取所有属性
     */
    public Map<String, Object> getAttributes() {
        return new HashMap<>(attributes);
    }

    /**
     * 合并另一个执行上下文
     */
    public void merge(ExecutionContext other) {
        if (other != null) {
            this.variables.putAll(other.variables);
            this.sharedData.putAll(other.sharedData);
            this.nodeExecutions.putAll(other.nodeExecutions);
            this.attributes.putAll(other.attributes);
        }
    }

    /**
     * 获取节点执行状态
     */
    public NodeExecution getNodeExecution(String nodeId) {
        return nodeExecutions.get(nodeId);
    }

    /**
     * 设置节点执行状态
     */
    public void setNodeExecution(String nodeId, NodeExecution nodeExecution) {
        nodeExecutions.put(nodeId, nodeExecution);
    }

    /**
     * 获取所有节点执行状态
     */
    public Map<String, NodeExecution> getNodeExecutions() {
        return new HashMap<>(nodeExecutions);
    }
}
