package com.particle.agi.domain.agent.value;

import com.particle.global.dto.basic.VO;
import lombok.Data;

import java.time.Duration;

/**
 * <p>
 * 智能体对话结果元数据
 * 参考：{@link org.springframework.ai.chat.metadata.ChatResponseMetadata}
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
public class AgiAgentChatResultMetadataDTO extends VO {

    /**
     * 一个id，todo 暂没有弄明白
     */
    private String id; // Set to blank to preserve backward compat with previous

    /**
     * 模型名称
     */
    private String model;

    /**
     * 限流信息
     */
    private RateLimitDTO rateLimit;

    /**
     * 消耗信息
     */
    private UsageDTO usage;
    // todo 下面注释的没有太明白，暂不添加
    // private PromptMetadata promptMetadata = PromptMetadata.empty();

    public static AgiAgentChatResultMetadataDTO create(String id, String model, RateLimitDTO rateLimitDTO, UsageDTO usageDTO) {
        AgiAgentChatResultMetadataDTO AgiAgentChatResultMetadataDTO = new AgiAgentChatResultMetadataDTO();
        AgiAgentChatResultMetadataDTO.id = id;
        AgiAgentChatResultMetadataDTO.model = model;
        AgiAgentChatResultMetadataDTO.rateLimit = rateLimitDTO;
        AgiAgentChatResultMetadataDTO.usage = usageDTO;
        return AgiAgentChatResultMetadataDTO;
    }

    /**
     * 参考{@link org.springframework.ai.chat.metadata.RateLimit}
     */
    @Data
    public static class RateLimitDTO {

        /**
         * 最大请求限制：允许在耗尽速率限制之前的最大请求数量。
         */
        private Long requestsLimit;

        /**
         * 剩余请求数量：在耗尽速率限制之前允许的剩余请求数量。
         */
        private Long requestsRemaining;

        /**
         * 请求重置时间：速率限制（基于请求）重置到初始状态的时间。
         */
        private Duration requestsReset;

        /**
         * 最大令牌限制：允许在耗尽速率限制之前的最大小令牌数量。
         */
        private Long tokensLimit;

        /**
         * 剩余令牌数量：在耗尽速率限制之前允许的剩余令牌数量。
         */
        private Long tokensRemaining;

        /**
         * 令牌重置时间：速率限制（基于令牌）重置到初始状态的时间。
         */
        private Duration tokensReset;

        public static RateLimitDTO create(Long requestsLimit,
                                          Long requestsRemaining,
                                          Duration requestsReset,
                                          Long tokensLimit,
                                          Long tokensRemaining,
                                          Duration tokensReset) {
            RateLimitDTO rateLimitDTO = new RateLimitDTO();
            rateLimitDTO.requestsLimit = requestsLimit;
            rateLimitDTO.requestsRemaining = requestsRemaining;
            rateLimitDTO.requestsReset = requestsReset;
            rateLimitDTO.tokensLimit = tokensLimit;
            rateLimitDTO.tokensRemaining = tokensRemaining;
            rateLimitDTO.tokensReset = tokensReset;

            return rateLimitDTO;
        }
    }

    /**
     * 参考{@link org.springframework.ai.chat.metadata.Usage}
     */
    @Data
    public static class UsageDTO {
        /**
         * 在 AI 请求的 {@literal prompt} 中使用的令牌数量。
         */
        private Integer promptTokens;

        /**
         * 在 AI 响应的 {@literal generation (aka completion)} 中返回的令牌数量。
         */
        private Integer completionTokens;
        /**
         * {@literal prompt} 和 {@literal generation} 累计使用的令牌数量。
         */
        private Integer totalTokens;

        /**
         * 从底层模型 API 响应中获取的原始使用数据。
         */
        private Object nativeUsage;

        public static UsageDTO create(Integer promptTokens,
                                      Integer completionTokens,
                                      Integer totalTokens,
                                      Object nativeUsage) {
            UsageDTO usageDTO = new UsageDTO();
            usageDTO.promptTokens = promptTokens;
            usageDTO.completionTokens = completionTokens;
            usageDTO.totalTokens = totalTokens;
            usageDTO.nativeUsage = nativeUsage;
            return usageDTO;
        }
    }
}
