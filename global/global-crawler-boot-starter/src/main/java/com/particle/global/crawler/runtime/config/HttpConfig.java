package com.particle.global.crawler.runtime.config;

import lombok.Data;

/**
 * HTTP 配置
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class HttpConfig {
    /**
     * 请求超时时间（毫秒）
     */
    private Integer timeout;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 最大重试次数
     */
    private Integer maxRetries;

    /**
     * 连接池大小
     */
    private Integer poolSize;

    /**
     * 是否跟随重定向
     */
    private Boolean isFollowRedirects;

    /**
     * 是否验证 SSL 证书
     */
    private Boolean isVerifySsl;

    public static HttpConfig defaultConfig() {
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setTimeout(10000);
        httpConfig.setUserAgent(null);
        httpConfig.setMaxRetries(3);
        httpConfig.setPoolSize(10);
        httpConfig.setIsFollowRedirects(true);
        httpConfig.setIsVerifySsl(true);
        return httpConfig;
    }

    /**
     * 合并配置（当前配置优先）
     */
    public HttpConfig merge(HttpConfig defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.timeout == null) {
            this.timeout = defaults.timeout;
        }
        if (this.userAgent == null) {
            this.userAgent = defaults.userAgent;
        }
        if (this.maxRetries == null) {
            this.maxRetries = defaults.maxRetries;
        }
        if (this.poolSize == null) {
            this.poolSize = defaults.poolSize;
        }
        if (this.isFollowRedirects == null) {
            this.isFollowRedirects = defaults.isFollowRedirects;
        }
        if (this.isVerifySsl == null) {
            this.isVerifySsl = defaults.isVerifySsl;
        }
        return this;
    }
}
