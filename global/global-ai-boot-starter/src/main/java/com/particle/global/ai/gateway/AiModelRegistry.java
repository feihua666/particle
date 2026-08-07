package com.particle.global.ai.gateway;

import com.particle.global.ai.strategy.AiModelInvocationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AI 模型注册表
 * 管理所有 ChatModel Bean 和调用策略
 * 
 * @author particle
 * @since 2026-04-14
 */
public class AiModelRegistry {
    
    private static final Logger log = LoggerFactory.getLogger(AiModelRegistry.class);
    
    /**
     * ChatModel 注册表（key: 提供商编码:模型编码）
     */
    private final Map<String, ChatModel> chatModelRegistry = new ConcurrentHashMap<>();
    
    /**
     * 调用策略注册表（key: 提供商编码）
     */
    private final Map<String, AiModelInvocationStrategy> strategyRegistry = new ConcurrentHashMap<>();
    
    /**
     * 默认 ChatModel
     */
    private ChatModel defaultChatModel;
    
    /**
     * 注册 ChatModel
     * 
     * @param key 唯一标识（格式: providerCode:modelCode）
     * @param chatModel ChatModel 实例
     */
    public void registerChatModel(String key, ChatModel chatModel) {
        chatModelRegistry.put(key, chatModel);
        log.info("Registered ChatModel: {}", key);
    }
    
    /**
     * 注销 ChatModel
     * 
     * @param key 唯一标识
     */
    public void unregisterChatModel(String key) {
        chatModelRegistry.remove(key);
        log.info("Unregistered ChatModel: {}", key);
    }
    
    /**
     * 根据 key 获取 ChatModel
     * 
     * @param key 唯一标识
     * @return ChatModel 实例
     */
    public ChatModel getChatModel(String key) {
        ChatModel model = chatModelRegistry.get(key);
        if (model == null) {
            log.warn("ChatModel not found for key: {}, using default", key);
            return defaultChatModel;
        }
        return model;
    }
    
    /**
     * 根据提供商和模型获取 ChatModel
     * 
     * @param providerCode 提供商编码
     * @param modelCode 模型编码
     * @return ChatModel 实例
     */
    public ChatModel getChatModel(String providerCode, String modelCode) {
        String key = buildKey(providerCode, modelCode);
        return getChatModel(key);
    }
    
    /**
     * 注册调用策略
     * 
     * @param strategy 策略实例
     */
    public void registerStrategy(AiModelInvocationStrategy strategy) {
        String providerCode = strategy.getSupportedProviderCode();
        strategyRegistry.put(providerCode, strategy);
        log.info("Registered strategy for provider: {}", providerCode);
    }
    
    /**
     * 根据提供商编码获取策略
     * 
     * @param providerCode 提供商编码
     * @return 策略实例
     */
    public AiModelInvocationStrategy getStrategy(String providerCode) {
        AiModelInvocationStrategy strategy = strategyRegistry.get(providerCode);
        if (strategy == null) {
            log.warn("No strategy found for provider: {}", providerCode);
        }
        return strategy;
    }
    
    /**
     * 获取所有已注册的策略
     * 
     * @return 策略列表
     */
    public Map<String, AiModelInvocationStrategy> getAllStrategies() {
        return new ConcurrentHashMap<>(strategyRegistry);
    }
    
    /**
     * 设置默认 ChatModel
     * 
     * @param defaultChatModel ChatModel 实例
     */
    public void setDefaultChatModel(ChatModel defaultChatModel) {
        this.defaultChatModel = defaultChatModel;
    }
    
    /**
     * 获取默认 ChatModel
     * 
     * @return ChatModel 实例
     */
    public ChatModel getDefaultChatModel() {
        return defaultChatModel;
    }
    
    /**
     * 获取所有已注册的 ChatModel key
     * 
     * @return key 集合
     */
    public Map<String, ChatModel> getAllChatModels() {
        return new ConcurrentHashMap<>(chatModelRegistry);
    }
    
    /**
     * 构建唯一 key
     * 
     * @param providerCode 提供商编码
     * @param modelCode 模型编码
     * @return 唯一 key
     */
    public static String buildKey(String providerCode, String modelCode) {
        return providerCode + ":" + modelCode;
    }
    
    /**
     * 清空注册表
     */
    public void clear() {
        chatModelRegistry.clear();
        strategyRegistry.clear();
        log.info("Cleared all registries");
    }
    
    /**
     * 刷新注册表（从数据库重新加载）
     */
    public void refresh() {
        clear();
        log.info("Registry refreshed");
    }
}
