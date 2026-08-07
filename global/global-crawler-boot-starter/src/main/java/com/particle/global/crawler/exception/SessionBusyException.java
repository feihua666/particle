package com.particle.global.crawler.exception;

/**
 * Session 忙异常
 * 如果Session在 使用中，则抛出此异常
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public class SessionBusyException extends SessionException {

    public SessionBusyException(String message) {
        super(message);
    }

    public SessionBusyException(String message, Throwable cause) {
        super(message, cause);
    }
}
