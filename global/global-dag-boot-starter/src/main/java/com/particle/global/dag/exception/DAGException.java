package com.particle.global.dag.exception;

/**
 * <p>
 * DAG引擎检查型异常基础类
 * </p>
 *
 * @author Claude
 * @since 2026-01-10 21:55:00
 */
public class DAGException extends Exception {

    /**
     * 构造函数
     * @param message 异常消息
     */
    public DAGException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * @param message 异常消息
     * @param cause 异常原因
     */
    public DAGException(String message, Throwable cause) {
        super(message, cause);
    }
}