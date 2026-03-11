package com.particle.global.dag.runtime;

/**
 * <p>
 * 节点执行状态（运行态对象）
 * </p>
 * <p>
 * 用于记录节点的执行状态、输出结果和错误信息，提供状态变更方法供引擎更新节点执行状态。
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class NodeExecution {

    /**
     * 节点ID
     */
    private final String nodeId;

    /**
     * 节点执行状态
     */
    private NodeExecutionStatus status;

    /**
     * 节点执行输出结果
     */
    private Object output;

    /**
     * 节点执行失败时的异常信息
     */
    private Throwable error;

    public NodeExecution(String nodeId) {
        this.nodeId = nodeId;
        this.status = NodeExecutionStatus.PENDING;
    }

    /**
     * 标记节点为运行中状态
     */
    public void markRunning() {
        this.status = NodeExecutionStatus.RUNNING;
    }

    /**
     * 标记节点为成功状态
     *
     * @param output 节点执行输出结果
     */
    public void markSuccess(Object output) {
        this.status = NodeExecutionStatus.SUCCESS;
        this.output = output;
    }

    /**
     * 标记节点为失败状态
     *
     * @param error 节点执行失败的异常信息
     */
    public void markFailed(Throwable error) {
        this.status = NodeExecutionStatus.FAILED;
        this.error = error;
    }

    /**
     * 标记节点为跳过状态
     */
    public void markSkipped() {
        this.status = NodeExecutionStatus.SKIPPED;
    }

    /**
     * 获取节点ID
     *
     * @return 节点ID
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 获取节点执行状态
     *
     * @return 节点执行状态
     */
    public NodeExecutionStatus getStatus() {
        return status;
    }

    /**
     * 获取节点执行输出结果
     *
     * @return 节点执行输出结果，如果未执行成功则可能为null
     */
    public Object getOutput() {
        return output;
    }

    /**
     * 获取节点执行失败时的异常信息
     *
     * @return 节点执行失败的异常信息，如果未失败则返回null
     */
    public Throwable getError() {
        return error;
    }
}
