package com.particle.global.dag.exception;

/**
 * <p>
 * 循环依赖检测异常
 * </p>
 *
 * @author Claude
 * @since 2026-01-10 21:55:00
 */
public class CycleDetectedException extends DAGRuntimeException {

    /**
     * 构造函数
     * @param message 异常消息
     */
    public CycleDetectedException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * @param message 异常消息
     * @param cause 异常原因
     */
    public CycleDetectedException(String message, Throwable cause) {
        super(message, cause);
    }
}