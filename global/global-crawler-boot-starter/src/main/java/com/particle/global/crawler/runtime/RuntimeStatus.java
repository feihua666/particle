package com.particle.global.crawler.runtime;

/**
 * 执行状态枚举
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public enum RuntimeStatus {

    /**
     * 等待执行
     */
    PENDING,

    /**
     * 正在执行
     */
    RUNNING,

    /**
     * 执行成功
     */
    SUCCESS,

    /**
     * 执行失败
     */
    FAILED,

    /**
     * 已取消
     */
    CANCELLED
}
