package com.particle.global.dag.exception;

/**
 * <p>
 * DAG引擎运行时异常基础类
 * </p>
 *
 * @author Claude
 * @since 2026-01-10 21:55:00
 */
public class DAGRuntimeException extends RuntimeException {

    /**
     * 构造函数
     * @param message 异常消息
     */
    public DAGRuntimeException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * @param message 异常消息
     * @param cause 异常原因
     */
    public DAGRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}