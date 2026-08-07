package com.particle.global.ai.gateway;

import com.particle.global.ai.config.AiModelConfig;
import com.particle.global.ai.config.AiModelProviderConfig;

import java.util.List;

/**
 * 模型配置网关接口
 * 由业务模块（如 agi）实现，提供从数据库读取配置的能力
 * 
 * @author particle
 * @since 2026-04-14
 */
public interface ModelConfigGateway {
    
    /**
     * 根据模型ID获取配置
     * 
     * @param modelId 模型ID
     * @return 模型配置
     */
    AiModelConfig getModelConfig(Long modelId);
    
    /**
     * 根据提供商编码和模型编码获取配置
     * 
     * @param providerCode 提供商编码
     * @param modelCode 模型编码
     * @return 模型配置
     */
    AiModelConfig getModelConfig(String providerCode, String modelCode);
    
    /**
     * 获取所有已启用的提供商配置
     * 
     * @return 提供商配置列表
     */
    List<AiModelProviderConfig> getAllEnabledProviders();
    
    /**
     * 获取指定提供商下的所有模型配置
     * 
     * @param providerId 提供商ID
     * @return 模型配置列表
     */
    List<AiModelConfig> getModelsByProviderId(Long providerId);
    
    /**
     * 刷新缓存（当配置变更时调用）
     */
    void refreshCache();
    
    /**
     * 获取默认模型配置
     * 
     * @return 默认模型配置
     */
    default AiModelConfig getDefaultModelConfig() {
        return null;
    }
}
