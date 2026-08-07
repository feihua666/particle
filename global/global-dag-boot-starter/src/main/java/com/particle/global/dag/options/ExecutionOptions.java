package com.particle.global.dag.options;

import com.google.common.collect.Lists;
import com.particle.global.dag.constants.NodeRoleConstants;
import com.particle.global.dag.constants.NodeTypeConstants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DAG 执行选项
 * <p>
 * 控制执行范围（全量/局部/重试）和容错行为。
 * 并行执行由 dag 引擎根据 DAG 拓扑自动决定，不需要配置。
 * </p>
 *
 * @author particle
 * @since 2026-01-09 10:22:40
 */
public class ExecutionOptions {

    /**
     * 起始节点集合（局部执行 / 重试时使用）
     * 空集合表示全量执行，非空集合表示从指定节点开始执行
     */
    private final Set<String> startNodeIds;

    /**
     * 是否跳过上游节点
     * true：上游节点标记为 SKIPPED
     * false：要求依赖必须完成
     */
    private final boolean skipUpstream;

    /**
     * 是否只重试失败的节点
     */
    private final boolean retryFailedOnly;

    /**
     * 是否跳过已成功的节点
     */
    private final boolean skipSuccessful;

    /**
     * 是否启用容错执行（节点失败后继续执行后续节点）
     */
    private final boolean faultTolerant;

    /**
     * 停止节点 ID （执行到该节点完成后停止）
     * null 表示不限制，执行到 DAG 尾部
     */
    private final String stopAfterNodeId;
    /**
     * 跳过指定角色的节点
     */
    private final List<String> skippedNodeRoles = Lists.newArrayList(NodeRoleConstants.CONSTANT_OUTPUT);

    private ExecutionOptions(Set<String> startNodeIds,
                             boolean skipUpstream,
                             boolean retryFailedOnly,
                             boolean skipSuccessful,
                             boolean faultTolerant,
                             String stopAfterNodeId) {
        this.startNodeIds = startNodeIds != null ? new HashSet<>(startNodeIds) : new HashSet<>();
        this.skipUpstream = skipUpstream;
        this.retryFailedOnly = retryFailedOnly;
        this.skipSuccessful = skipSuccessful;
        this.faultTolerant = faultTolerant;
        this.stopAfterNodeId = stopAfterNodeId;
    }

    // ==================== 工厂方法 ====================

    /**
     * 全量执行（从 DAG root 开始）
     */
    public static ExecutionOptions full() {
        return new ExecutionOptions(Collections.emptySet(), false, false, false, false, null);
    }

    /**
     * 从指定节点开始执行
     */
    public static ExecutionOptions partial(Set<String> startNodeIds) {
        return new ExecutionOptions(startNodeIds, true, false, false, false, null);
    }

    /**
     * 从单个节点开始（语法糖）
     */
    public static ExecutionOptions partial(String startNodeId) {
        return partial(Set.of(startNodeId));
    }

    /**
     * 重试失败节点
     */
    public static ExecutionOptions retryFailed() {
        return new ExecutionOptions(Collections.emptySet(), false, true, false, false, null);
    }

    /**
     * 跳过成功节点执行
     */
    public static ExecutionOptions skipSuccessful() {
        return new ExecutionOptions(Collections.emptySet(), false, false, true, false, null);
    }

    /**
     * 执行到此节点为止（包含该节点，执行完成后停止）
     */
    public static ExecutionOptions upTo(String stopAfterNodeId) {
        return new ExecutionOptions(Collections.emptySet(), false, false, false, false, stopAfterNodeId);
    }

    /**
     * 从指定节点开始，执行到指定节点为止（包含两端）
     * <p>
     * 等价于 partial(startNodeId) + upTo(endNodeId) 的组合。
     * </p>
     *
     * @param startNodeId 起始节点ID（从此节点开始执行）
     * @param endNodeId   结束节点ID（执行到该节点后停止）
     */
    public static ExecutionOptions fromTo(String startNodeId, String endNodeId) {
        return new ExecutionOptions(Set.of(startNodeId), true, false, false, false, endNodeId);
    }

    /**
     * 自定义执行选项
     */
    public static ExecutionOptions custom(Set<String> startNodeIds,
                                         boolean skipUpstream,
                                         boolean retryFailedOnly,
                                         boolean skipSuccessful,
                                         boolean faultTolerant,
                                         String stopAfterNodeId) {
        return new ExecutionOptions(startNodeIds, skipUpstream, retryFailedOnly, skipSuccessful, faultTolerant, stopAfterNodeId);
    }

    // ==================== Getter ====================

    public Set<String> getStartNodeIds() {
        return Collections.unmodifiableSet(startNodeIds);
    }

    public boolean isSkipUpstream() {
        return skipUpstream;
    }

    public boolean isRetryFailedOnly() {
        return retryFailedOnly;
    }

    public boolean isSkipSuccessful() {
        return skipSuccessful;
    }

    public boolean isFaultTolerant() {
        return faultTolerant;
    }

    public String getStopAfterNodeId() {
        return stopAfterNodeId;
    }

    public List<String> getSkippedNodeRoles() {
        return skippedNodeRoles;
    }
}
