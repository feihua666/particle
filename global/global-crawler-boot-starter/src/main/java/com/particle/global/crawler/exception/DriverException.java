package com.particle.global.crawler.exception;

/**
 * Driver 异常
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public class DriverException extends CrawlerException {
    
    public DriverException(String message) {
        super(message);
    }
    
    public DriverException(String message, Throwable cause) {
        super(message, cause);
    }
}
