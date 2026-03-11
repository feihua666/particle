package com.particle.global.dag.runtime;

/**
 * <p>
 * 节点执行结果
 * </p>
 * <p>
 * 用于表示节点执行的成功或失败状态，以及相应的输出或错误信息。
 * 执行器可以通过此类返回详细的执行结果，引擎根据结果决定后续处理逻辑。
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class NodeExecutionResult {

    /**
     * 执行是否成功
     */
    private final boolean success;

    /**
     * 执行输出结果，执行失败时为null
     */
    private final Object output;

    /**
     * 执行失败时的异常信息，执行成功时为null
     */
    private final Throwable error;

    /**
     * 私有构造函数，使用静态工厂方法创建实例
     *
     * @param success 执行是否成功
     * @param output 执行输出结果
     * @param error 执行失败时的异常信息
     */
    private NodeExecutionResult(boolean success, Object output, Throwable error) {
        this.success = success;
        this.output = output;
        this.error = error;
    }

    /**
     * 创建成功的执行结果
     *
     * @param output 执行输出结果，可以为null
     * @return 成功的执行结果
     */
    public static NodeExecutionResult success(Object output) {
        return new NodeExecutionResult(true, output, null);
    }

    /**
     * 创建失败的执行结果
     *
     * @param error 执行失败的异常信息，不能为null
     * @return 失败的执行结果
     */
    public static NodeExecutionResult failure(Throwable error) {
        return new NodeExecutionResult(false, null, error);
    }

    /**
     * 判断执行是否成功
     *
     * @return true表示成功，false表示失败
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 获取执行输出结果
     *
     * @return 执行输出结果，如果执行失败则返回null
     */
    public Object getOutput() {
        return output;
    }

    /**
     * 获取执行失败时的异常信息
     *
     * @return 执行失败的异常信息，如果执行成功则返回null
     */
    public Throwable getError() {
        return error;
    }
}