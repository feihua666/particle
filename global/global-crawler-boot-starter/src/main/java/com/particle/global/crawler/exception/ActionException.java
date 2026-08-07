package com.particle.global.crawler.exception;

/**
 * Action 执行异常
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public class ActionException extends CrawlerException {
    
    public ActionException(String message) {
        super(message);
    }
    
    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
