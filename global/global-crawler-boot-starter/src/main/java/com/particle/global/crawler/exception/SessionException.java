package com.particle.global.crawler.exception;

/**
 * Session 异常
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public class SessionException extends CrawlerException {
    
    public SessionException(String message) {
        super(message);
    }
    
    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
