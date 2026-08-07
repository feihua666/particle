package com.particle.global.crawler.exception;

/**
 * 爬虫基础异常
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public class CrawlerException extends RuntimeException {
    
    public CrawlerException(String message) {
        super(message);
    }
    
    public CrawlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
