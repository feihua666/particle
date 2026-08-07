package com.particle.global.ai.strategy;

import com.particle.global.ai.config.AiModelConfig;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * AI 模型调用策略接口
 * 不同提供商使用不同的调用策略
 * 
 * @author particle
 * @since 2026-04-14
 */
public interface AiModelInvocationStrategy {
    
    /**
     * 是否支持该提供商
     * 
     * @param providerCode 提供商编码
     * @return true-支持，false-不支持
     */
    boolean supports(String providerCode);
    
    /**
     * 同步调用
     * 
     * @param prompt 提示词
     * @param modelConfig 模型配置
     * @return AI 调用结果
     */
    AiModelInvokeResult invoke(String prompt, AiModelConfig modelConfig);
    
    /**
     * 带上下文的同步调用
     * 
     * @param messages 消息列表（key: role, content）
     * @param modelConfig 模型配置
     * @return AI 调用结果
     */
    AiModelInvokeResult invokeWithContext(Map<String, Object>[] messages, AiModelConfig modelConfig);
    
    /**
     * 流式调用
     * 
     * @param prompt 提示词
     * @param modelConfig 模型配置
     * @return 流式响应
     */
    Flux<ChatResponse> invokeStream(String prompt, AiModelConfig modelConfig);
    
    /**
     * 获取策略支持的提供商编码
     * 
     * @return 提供商编码
     */
    String getSupportedProviderCode();
    
    /**
     * 获取策略优先级（数字越小优先级越高）
     * 
     * @return 优先级
     */
    default int getPriority() {
        return 100;
    }
}
