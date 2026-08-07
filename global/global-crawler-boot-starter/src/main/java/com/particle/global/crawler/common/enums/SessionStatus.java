package com.particle.global.crawler.common.enums;

/**
 * session 枚举
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public enum SessionStatus {
    /**
     * 空间
     */
    IDLE,

    /**
     * 执行中
     * 如果 sesseion 在使用中，不允许执行新的 pipeline，否则会乱
     */
    RUNNING,
}
