package com.particle.global.ai.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * AI 模型实体
 *
 * @author particle
 * @since 2026-04-14
 */
@Data
public class AiModel {

    /**
     * 模型唯一标识（数据库主键）
     */
    private Long id;

    /**
     * 所属提供商ID
     */
    private Long providerId;

    /**
     * 模型编码（如：gpt-4, qwen-plus, qwen2:7b）
     */
    private String modelCode;

    /**
     * 模型显示名称
     */
    private String modelName;

    /**
     * 模型类型（chat, embedding, image, rerank）
     */
    private String modelType;

    /**
     * 最大 Token 数
     */
    private Integer maxTokens;

    /**
     * 默认温度值（0.0-2.0）
     */
    private BigDecimal defaultTemperature;

    /**
     * 默认 Top P 值
     */
    private BigDecimal defaultTopP;

    /**
     * 是否启用（0-禁用，1-启用）
     */
    private Integer status;

    /**
     * 排序顺序
     */
    private Integer sortOrder;

    /**
     * 扩展配置（JSON格式）
     */
    private Map<String, Object> extraConfig;

    /**
     * 模型描述
     */
    private String description;

    /**
     * 是否为默认模型
     */
    private Boolean isDefault;

    /**
     * 验证模型配置是否完整
     */
    public boolean isValid() {
        return this.modelCode != null
            && !this.modelCode.isEmpty()
            && this.modelType != null
            && !this.modelType.isEmpty()
            && this.status != null
            && this.status == 1;
    }

    /**
     * 获取实际使用的温度值（优先使用传入值，否则使用默认值）
     */
    public BigDecimal getEffectiveTemperature(BigDecimal overrideTemperature) {
        if (overrideTemperature != null) {
            return overrideTemperature;
        }
        return this.defaultTemperature != null ? this.defaultTemperature : new BigDecimal("0.7");
    }

    /**
     * 获取实际使用的 Top P 值（优先使用传入值，否则使用默认值）
     */
    public BigDecimal getEffectiveTopP(BigDecimal overrideTopP) {
        if (overrideTopP != null) {
            return overrideTopP;
        }
        return this.defaultTopP;
    }
}
