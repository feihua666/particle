package com.particle.global.dag.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 执行上下文
 *
 * 用于在DAG执行过程中传递和共享数据，提供变量存储和共享数据存储功能，
 * 支持跨节点的数据传递和状态管理。
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class ExecutionContext {

    /**
     * 存储执行过程中的变量
     * 用于节点间的数据传递和中间结果存储
     */
    private final Map<String, Object> variables = new HashMap<>();

    /**
     * 存储执行过程中的共享数据
     * 用于跨节点的全局数据共享
     */
    private final Map<String, Object> sharedData = new HashMap<>();

    /**
     * 存储节点执行状态
     * 用于跟踪各节点的执行情况
     */
    private final Map<String, NodeExecution> nodeExecutions = new HashMap<>();

    /**
     * 当前执行的ID
     * 用于标识当前DAG执行实例
     */
    private String currentExecutionId;

    /**
     * 默认构造函数
     * 创建空的执行上下文
     */
    public ExecutionContext() {}

    /**
     * 构造函数
     * @param executionId 执行ID，用于标识当前DAG执行实例
     */
    public ExecutionContext(String executionId) {
        this.currentExecutionId = executionId;
    }

    /**
     * 设置变量
     * 变量主要用于节点间的数据传递
     * @param key 变量键，不能为空或空白字符
     * @param value 变量值，可以为null
     */
    public void setVariable(String key, Object value) {
        variables.put(key, value);
    }

    /**
     * 获取变量
     * @param key 变量键
     * @return 变量值，如果不存在则返回null
     */
    public Object getVariable(String key) {
        return variables.get(key);
    }

    /**
     * 获取变量，带默认值
     * 当指定键的变量不存在时，返回提供的默认值
     * @param key 变量键
     * @param defaultValue 默认值
     * @return 变量值或默认值
     */
    public Object getVariable(String key, Object defaultValue) {
        return variables.getOrDefault(key, defaultValue);
    }

    /**
     * 设置共享数据
     * 共享数据可用于所有节点访问的全局数据
     * @param key 数据键，不能为空或空白字符
     * @param value 数据值，可以为null
     */
    public void setSharedData(String key, Object value) {
        sharedData.put(key, value);
    }

    /**
     * 获取共享数据
     * @param key 数据键
     * @return 数据值，如果不存在则返回null
     */
    public Object getSharedData(String key) {
        return sharedData.get(key);
    }

    /**
     * 获取所有变量
     * 返回当前上下文中所有变量的副本
     * @return 所有变量的不可变副本
     */
    public Map<String, Object> getVariables() {
        return new HashMap<>(variables);
    }

    /**
     * 获取所有共享数据
     * 返回当前上下文中所有共享数据的副本
     * @return 所有共享数据的不可变副本
     */
    public Map<String, Object> getSharedData() {
        return new HashMap<>(sharedData);
    }

    /**
     * 获取当前执行ID
     * @return 当前执行ID，如果未设置则返回null
     */
    public String getCurrentExecutionId() {
        return currentExecutionId;
    }

    /**
     * 设置当前执行ID
     * @param currentExecutionId 当前执行ID
     */
    public void setCurrentExecutionId(String currentExecutionId) {
        this.currentExecutionId = currentExecutionId;
    }

    /**
     * 合并另一个执行上下文
     * 将另一个上下文中的变量和共享数据合并到当前上下文中
     * 当前上下文的执行ID如果为空，则会被另一个上下文的执行ID替换
     * @param other 另一个执行上下文，如果为null则不做任何操作
     */
    public void merge(ExecutionContext other) {
        if (other != null) {
            this.variables.putAll(other.variables);
            this.sharedData.putAll(other.sharedData);
            this.nodeExecutions.putAll(other.nodeExecutions);
            if (this.currentExecutionId == null) {
                this.currentExecutionId = other.currentExecutionId;
            }
        }
    }

    /**
     * 获取节点执行状态
     * @param nodeId 节点ID
     * @return 节点执行状态
     */
    public NodeExecution getNodeExecution(String nodeId) {
        return nodeExecutions.get(nodeId);
    }

    /**
     * 设置节点执行状态
     * @param nodeId 节点ID
     * @param nodeExecution 节点执行状态
     */
    public void setNodeExecution(String nodeId, NodeExecution nodeExecution) {
        nodeExecutions.put(nodeId, nodeExecution);
    }

    /**
     * 获取所有节点执行状态
     * @return 所有节点执行状态的不可变副本
     */
    public Map<String, NodeExecution> getNodeExecutions() {
        return new HashMap<>(nodeExecutions);
    }
}