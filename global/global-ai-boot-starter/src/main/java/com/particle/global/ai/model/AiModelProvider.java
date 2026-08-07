package com.particle.global.ai.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * AI 模型提供商聚合根
 *
 * @author particle
 * @since 2026-04-14
 */
@Data
public class AiModelProvider {

    /**
     * 提供商唯一标识（数据库主键）
     */
    private Long id;

    /**
     * 提供商编码（如：openai, dashscope, ollama, deepseek）
     */
    private String providerCode;

    /**
     * 提供商显示名称（如：OpenAI, 阿里灵积, Ollama本地）
     */
    private String providerName;

    /**
     * 提供商类型（如：cloud, local, self-hosted）
     */
    private String providerType;

    /**
     * API 基础地址
     */
    private String baseUrl;

    /**
     * 是否启用（0-禁用，1-启用）
     */
    private Integer status;

    /**
     * 扩展配置（JSON格式，如超时时间、重试次数等）
     */
    private String extraConfig;

    /**
     * 该提供商下的所有模型
     */
    private List<AiModel> models = new ArrayList<>();

    /**
     * 添加模型
     */
    public void addModel(AiModel model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
    }

    /**
     * 根据模型ID获取模型
     */
    public AiModel getModelById(Long modelId) {
        if (this.models == null) {
            return null;
        }
        return this.models.stream()
            .filter(m -> m.getId().equals(modelId))
            .findFirst()
            .orElse(null);
    }

    /**
     * 根据模型编码获取模型
     */
    public AiModel getModelByCode(String modelCode) {
        if (this.models == null) {
            return null;
        }
        return this.models.stream()
            .filter(m -> m.getModelCode().equals(modelCode))
            .findFirst()
            .orElse(null);
    }

    /**
     * 验证提供商配置是否完整
     */
    public boolean isValid() {
        return this.providerCode != null
            && !this.providerCode.isEmpty()
            && this.baseUrl != null
            && !this.baseUrl.isEmpty()
            && this.status != null
            && this.status == 1;
    }
}
