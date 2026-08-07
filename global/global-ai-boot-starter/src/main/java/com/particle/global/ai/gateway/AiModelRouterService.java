package com.particle.global.ai.gateway;

import com.particle.global.ai.config.AiModelConfig;
import com.particle.global.ai.strategy.AiModelInvocationStrategy;
import com.particle.global.ai.strategy.AiModelInvokeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.client.ChatClient;
import reactor.core.publisher.Flux;
import org.springframework.ai.chat.model.ChatResponse;

import java.math.BigDecimal;
import java.util.Map;

/**
 * AI 模型路由服务
 * 根据模型配置动态选择策略和模型进行调用
 * 
 * @author particle
 * @since 2026-04-14
 */
public class AiModelRouterService {
    
    private static final Logger log = LoggerFactory.getLogger(AiModelRouterService.class);
    
    private final AiModelRegistry registry;
    
    public AiModelRouterService(AiModelRegistry registry) {
        this.registry = registry;
    }
    
    /**
     * 根据模型配置同步调用
     * 
     * @param prompt 提示词
     * @param modelConfig 模型配置
     * @return 调用结果
     */
    public AiModelInvokeResult invoke(String prompt, AiModelConfig modelConfig) {
        log.debug("Invoking AI model: provider={}, model={}", 
            modelConfig.getProviderCode(), modelConfig.getModelCode());
        
        // 1. 获取调用策略
        AiModelInvocationStrategy strategy = registry.getStrategy(modelConfig.getProviderCode());
        
        if (strategy != null) {
            // 2. 使用策略调用
            long startTime = System.currentTimeMillis();
            try {
                AiModelInvokeResult result = strategy.invoke(prompt, modelConfig);
                result.setResponseTimeMs(System.currentTimeMillis() - startTime);
                return result;
            } catch (Exception e) {
                log.error("Strategy invocation failed for provider: {}", 
                    modelConfig.getProviderCode(), e);
                // 降级：使用 Spring AI 原生调用
                return invokeWithSpringAi(prompt, modelConfig, startTime);
            }
        } else {
            // 3. 使用 Spring AI 原生调用
            return invokeWithSpringAi(prompt, modelConfig, System.currentTimeMillis());
        }
    }
    
    /**
     * 根据模型 ID 同步调用
     * 
     * @param prompt 提示词
     * @param modelId 模型ID
     * @param modelConfigGateway 模型配置网关
     * @return 调用结果
     */
    public AiModelInvokeResult invokeByModelId(String prompt, Long modelId, 
                                                 ModelConfigGateway modelConfigGateway) {
        AiModelConfig modelConfig = modelConfigGateway.getModelConfig(modelId);
        if (modelConfig == null) {
            throw new IllegalArgumentException("Model not found: " + modelId);
        }
        return invoke(prompt, modelConfig);
    }
    
    /**
     * 带上下文的同步调用
     * 
     * @param messages 消息列表
     * @param modelConfig 模型配置
     * @return 调用结果
     */
    @SuppressWarnings("unchecked")
    public AiModelInvokeResult invokeWithContext(Map<String, Object>[] messages, AiModelConfig modelConfig) {
        log.debug("Invoking AI model with context: provider={}, model={}", 
            modelConfig.getProviderCode(), modelConfig.getModelCode());
        
        // 1. 获取调用策略
        AiModelInvocationStrategy strategy = registry.getStrategy(modelConfig.getProviderCode());
        
        if (strategy != null) {
            // 2. 使用策略调用
            long startTime = System.currentTimeMillis();
            try {
                AiModelInvokeResult result = strategy.invokeWithContext(messages, modelConfig);
                result.setResponseTimeMs(System.currentTimeMillis() - startTime);
                return result;
            } catch (Exception e) {
                log.error("Strategy invocation with context failed for provider: {}", 
                    modelConfig.getProviderCode(), e);
                // 降级：使用第一条消息作为 prompt
                String prompt = (String) messages[messages.length - 1].get("content");
                return invokeWithSpringAi(prompt, modelConfig, startTime);
            }
        } else {
            // 3. 使用 Spring AI 原生调用
            String prompt = (String) messages[messages.length - 1].get("content");
            return invokeWithSpringAi(prompt, modelConfig, System.currentTimeMillis());
        }
    }
    
    /**
     * 流式调用
     * 
     * @param prompt 提示词
     * @param modelConfig 模型配置
     * @return 流式响应
     */
    public Flux<ChatResponse> invokeStream(String prompt, AiModelConfig modelConfig) {
        log.debug("Invoking AI model stream: provider={}, model={}", 
            modelConfig.getProviderCode(), modelConfig.getModelCode());
        
        // 1. 获取调用策略
        AiModelInvocationStrategy strategy = registry.getStrategy(modelConfig.getProviderCode());
        
        if (strategy != null) {
            // 2. 使用策略流式调用
            return strategy.invokeStream(prompt, modelConfig);
        } else {
            // 3. 使用 Spring AI 原生流式调用
            return invokeStreamWithSpringAi(prompt, modelConfig);
        }
    }
    
    /**
     * 根据模型 ID 流式调用
     * 
     * @param prompt 提示词
     * @param modelId 模型ID
     * @param modelConfigGateway 模型配置网关
     * @return 流式响应
     */
    public Flux<ChatResponse> invokeStreamByModelId(String prompt, Long modelId, 
                                                      ModelConfigGateway modelConfigGateway) {
        AiModelConfig modelConfig = modelConfigGateway.getModelConfig(modelId);
        if (modelConfig == null) {
            throw new IllegalArgumentException("Model not found: " + modelId);
        }
        return invokeStream(prompt, modelConfig);
    }
    
    /**
     * 使用 Spring AI 原生调用（降级方案）
     */
    private AiModelInvokeResult invokeWithSpringAi(String prompt, AiModelConfig modelConfig, 
                                                     long startTime) {
        log.info("Fallback to Spring AI native invocation for provider: {}", 
            modelConfig.getProviderCode());
        
        ChatModel chatModel = registry.getChatModel(
            modelConfig.getProviderCode(), 
            modelConfig.getModelCode()
        );
        
        if (chatModel == null) {
            throw new IllegalStateException("No ChatModel available for: " + 
                modelConfig.getFullIdentifier());
        }
        
        ChatClient chatClient = ChatClient.create(chatModel);
        
        String result = chatClient.prompt()
            .user(prompt)
            .call()
            .content();
        
        long responseTime = System.currentTimeMillis() - startTime;
        
        return AiModelInvokeResult.success(result, null, responseTime);
    }
    
    /**
     * 使用 Spring AI 原生流式调用（降级方案）
     */
    private Flux<ChatResponse> invokeStreamWithSpringAi(String prompt, AiModelConfig modelConfig) {
        log.info("Fallback to Spring AI native stream invocation for provider: {}", 
            modelConfig.getProviderCode());
        
        ChatModel chatModel = registry.getChatModel(
            modelConfig.getProviderCode(), 
            modelConfig.getModelCode()
        );
        
        if (chatModel == null) {
            return Flux.error(new IllegalStateException("No ChatModel available for: " + 
                modelConfig.getFullIdentifier()));
        }
        
        return chatModel.stream(
            new org.springframework.ai.chat.prompt.Prompt(
                new org.springframework.ai.chat.messages.UserMessage(prompt)
            )
        );
    }
    
    /**
     * 获取注册表
     */
    public AiModelRegistry getRegistry() {
        return registry;
    }
}
