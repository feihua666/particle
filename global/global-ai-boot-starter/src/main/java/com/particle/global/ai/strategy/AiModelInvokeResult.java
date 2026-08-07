package com.particle.global.ai.strategy;

import lombok.Data;

/**
 * AI 模型调用结果
 *
 * @author particle
 * @since 2026-04-14
 */
@Data
public class AiModelInvokeResult {

    /**
     * 输出文本
     */
    private String output;

    /**
     * 是否完成（流式调用时使用）
     */
    private Boolean finished;

    /**
     * Prompt Token 数
     */
    private Integer promptTokens;

    /**
     * 完成 Token 数
     */
    private Integer completionTokens;

    /**
     * 总 Token 数
     */
    private Integer totalTokens;

    /**
     * 实际使用的模型
     */
    private String modelUsed;

    /**
     * 响应时间（毫秒）
     */
    private Long responseTimeMs;

    /**
     * 创建成功结果
     */
    public static AiModelInvokeResult success(String output, Integer totalTokens, Long responseTimeMs) {
        AiModelInvokeResult aiModelInvokeResult = new AiModelInvokeResult();
        aiModelInvokeResult.output = output;
        aiModelInvokeResult.totalTokens = totalTokens;
        aiModelInvokeResult.responseTimeMs = responseTimeMs;
        return aiModelInvokeResult;
    }

    /**
     * 创建流式结果
     */
    public static AiModelInvokeResult stream(String output, Boolean finished) {
        AiModelInvokeResult aiModelInvokeResult = new AiModelInvokeResult();
        aiModelInvokeResult.output = output;
        aiModelInvokeResult.finished = finished;
        return aiModelInvokeResult;
    }

    /**
     * 创建完整结果（含详细 token 使用）
     */
    public static AiModelInvokeResult complete(String output, Integer promptTokens,
                                                Integer completionTokens, Integer totalTokens,
                                                String modelUsed, Long responseTimeMs) {
        AiModelInvokeResult aiModelInvokeResult = new AiModelInvokeResult();
        aiModelInvokeResult.output = output;
        aiModelInvokeResult.promptTokens = promptTokens;
        aiModelInvokeResult.completionTokens = completionTokens;
        aiModelInvokeResult.totalTokens = totalTokens;
        aiModelInvokeResult.modelUsed = modelUsed;
        aiModelInvokeResult.responseTimeMs = responseTimeMs;
        return aiModelInvokeResult;
    }
}
