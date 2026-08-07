package com.particle.global.crawler.runtime.config;

import com.particle.global.crawler.runtime.session.auth.AuthConfig;
import lombok.Data;

import java.util.List;

/**
 * session 配置
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class SessionConfig {
    /**
     * 请求的 sessionId
     */
    private String sessionId;
    /**
     * 是否使用 session
     */
    private Boolean isUseSession;

    /**
     * 认证配置
     */
    private List<AuthConfig> authConfigs;
    /**
     * 合并配置（当前配置优先）
     */
    public SessionConfig merge(SessionConfig defaults) {
        if (defaults == null) {
            return this;
        }
        if (this.sessionId == null) {
            this.sessionId = defaults.sessionId;
        }
        if (this.isUseSession == null) {
            this.isUseSession = defaults.isUseSession;
        }
        if (this.authConfigs == null) {
            this.authConfigs = defaults.authConfigs;
        }
        return this;
    }

    public static SessionConfig defaultConfig() {
        SessionConfig config = new SessionConfig();
        config.setSessionId(null);
        config.setIsUseSession(false);
        config.setAuthConfigs(AuthConfig.parse(null));
        return config;
    }
}
