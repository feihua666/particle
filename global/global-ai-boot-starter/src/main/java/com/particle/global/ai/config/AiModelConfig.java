package com.particle.global.ai.config;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * AI 模型配置对象（用于运行时调用）
 *
 * @author particle
 * @since 2026-04-14
 */
@Data
public class AiModelConfig {

    /**
     * 模型ID
     */
    private Long modelId;

    /**
     * 提供商ID
     */
    private Long providerId;

    /**
     * 提供商编码
     */
    private String providerCode;

    /**
     * 模型编码
     */
    private String modelCode;

    /**
     * 模型类型（chat, embedding, image）
     */
    private String modelType;

    /**
     * 温度值
     */
    private BigDecimal temperature;

    /**
     * Top P 值
     */
    private BigDecimal topP;

    /**
     * 最大 Token 数
     */
    private Integer maxTokens;

    /**
     * 提供商配置
     */
    private AiModelProviderConfig providerConfig;

    /**
     * 扩展配置
     */
    private Map<String, Object> extraConfig;

    /**
     * 获取完整标识
     */
    public String getFullIdentifier() {
        return this.providerCode + ":" + this.modelCode;
    }

    /**
     * 验证配置是否完整
     */
    public boolean isValid() {
        return this.modelId != null
            && this.providerCode != null
            && !this.providerCode.isEmpty()
            && this.modelCode != null
            && !this.modelCode.isEmpty()
            && this.providerConfig != null
            && this.providerConfig.getEffectiveApiKey() != null;
    }
}
