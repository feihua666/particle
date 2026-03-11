package com.particle.global.dag.options;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * DAG 执行选项
 * </p>
 *
 * @author Claude
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
     * 是否启用容错执行
     */
    private final boolean faultTolerant;

    /**
     * 是否启用并行执行
     */
    private final boolean parallel;

    /**
     * 是否启用条件执行
     */
    private final boolean conditional;

    private ExecutionOptions(Set<String> startNodeIds,
                             boolean skipUpstream,
                             boolean retryFailedOnly,
                             boolean skipSuccessful,
                             boolean faultTolerant,
                             boolean parallel,
                             boolean conditional) {
        this.startNodeIds = startNodeIds != null ? new HashSet<>(startNodeIds) : new HashSet<>();
        this.skipUpstream = skipUpstream;
        this.retryFailedOnly = retryFailedOnly;
        this.skipSuccessful = skipSuccessful;
        this.faultTolerant = faultTolerant;
        this.parallel = parallel;
        this.conditional = conditional;
    }

    private ExecutionOptions(Set<String> startNodeIds,
                             boolean skipUpstream) {
        this(startNodeIds, skipUpstream, false, false, false, false, false);
    }

    /* -------------------- 工厂方法 -------------------- */

    /**
     * 全量执行（从 DAG root 开始）
     */
    public static ExecutionOptions full() {
        return new ExecutionOptions(
                Collections.emptySet(),
                false,
                false,
                false,
                false,
                false,
                false
        );
    }

    /**
     * 从指定节点开始执行
     */
    public static ExecutionOptions partial(Set<String> startNodeIds) {
        return new ExecutionOptions(
                startNodeIds,
                true,
                false,
                false,
                false,
                false,
                false
        );
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
        return new ExecutionOptions(
                Collections.emptySet(),
                false,
                true,
                false,
                false,
                false,
                false
        );
    }

    /**
     * 跳过成功节点执行
     */
    public static ExecutionOptions skipSuccessful() {
        return new ExecutionOptions(
                Collections.emptySet(),
                false,
                false,
                true,
                false,
                false,
                false
        );
    }

    /**
     * 自定义执行选项
     */
    public static ExecutionOptions custom(Set<String> startNodeIds,
                                         boolean skipUpstream,
                                         boolean retryFailedOnly,
                                         boolean skipSuccessful) {
        return custom(startNodeIds, skipUpstream, retryFailedOnly, skipSuccessful, false, false, false);
    }

    /**
     * 自定义执行选项（完整参数）
     */
    public static ExecutionOptions custom(Set<String> startNodeIds,
                                         boolean skipUpstream,
                                         boolean retryFailedOnly,
                                         boolean skipSuccessful,
                                         boolean faultTolerant,
                                         boolean parallel,
                                         boolean conditional) {
        return new ExecutionOptions(
                startNodeIds != null ? new HashSet<>(startNodeIds) : Collections.emptySet(),
                skipUpstream,
                retryFailedOnly,
                skipSuccessful,
                faultTolerant,
                parallel,
                conditional
        );
    }

    /* -------------------- Getter -------------------- */

    /**
     * 获取起始节点ID集合
     * @return 起始节点ID集合
     */
    public Set<String> getStartNodeIds() {
        return Collections.unmodifiableSet(startNodeIds);
    }

    /**
     * 是否只重试失败的节点
     * @return 是否只重试失败的节点
     */
    public boolean isRetryFailedOnly() {
        return retryFailedOnly;
    }

    /**
     * 是否跳过已成功的节点
     * @return 是否跳过已成功的节点
     */
    public boolean isSkipSuccessful() {
        return skipSuccessful;
    }

    /**
     * 是否启用容错执行
     * @return 是否启用容错执行
     */
    public boolean isFaultTolerant() {
        return faultTolerant;
    }

    /**
     * 是否启用并行执行
     * @return 是否启用并行执行
     */
    public boolean isParallel() {
        return parallel;
    }

}
