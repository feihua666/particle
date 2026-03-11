package com.particle.global.dag.exception;

/**
 * <p>
 * DAG执行异常
 * </p>
 *
 * @author Claude
 * @since 2026-01-10 21:55:00
 */
public class DAGExecutionException extends DAGException {

    public DAGExecutionException(String message) {
        super(message);
    }

    public DAGExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAGExecutionException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}