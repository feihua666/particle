package com.particle.global.ai.config;

import lombok.Data;

import java.util.Map;

/**
 * AI 模型提供商配置对象（用于运行时调用）
 *
 * @author particle
 * @since 2026-04-14
 */
@Data
public class AiModelProviderConfig {

    /**
     * 提供商ID
     */
    private Long providerId;

    /**
     * 提供商编码
     */
    private String providerCode;

    /**
     * API Key（已解密）
     */
    private String apiKey;

    /**
     * API 基础地址
     */
    private String baseUrl;

    /**
     * 扩展配置
     */
    private Map<String, Object> extraConfig;

    /**
     * 获取有效的 API Key
     */
    public String getEffectiveApiKey() {
        if (this.apiKey != null && !this.apiKey.isEmpty()) {
            return this.apiKey;
        }
        if (this.extraConfig != null && this.extraConfig.containsKey("apiKey")) {
            return (String) this.extraConfig.get("apiKey");
        }
        return null;
    }

    /**
     * 获取有效的 Base URL
     */
    public String getEffectiveBaseUrl() {
        if (this.baseUrl != null && !this.baseUrl.isEmpty()) {
            return this.baseUrl;
        }
        if (this.extraConfig != null && this.extraConfig.containsKey("baseUrl")) {
            return (String) this.extraConfig.get("baseUrl");
        }
        return null;
    }
}
