package com.particle.agi.client.agent.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Duration;

/**
 * <p>
 * 智能体对话结果元数据
 * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatResultMetadataDTO} 的结果前端响应
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
@Schema
public class AgiAgentChatResponseMetadataVO extends VO {

    @Schema(description = "一个id，todo 暂没有弄明白")
    private String id; // Set to blank to preserve backward compat with previous

    @Schema(description = "模型名称")
    private String model;

    @Schema(description = "限流信息")
    private RateLimitVO rateLimit;

    @Schema(description = "消耗信息")
    private UsageVO usage;

    public static AgiAgentChatResponseMetadataVO create(String id, String model, RateLimitVO rateLimitVO, UsageVO usageVO) {
        AgiAgentChatResponseMetadataVO AgiAgentChatResultMetadataDTO = new AgiAgentChatResponseMetadataVO();
        AgiAgentChatResultMetadataDTO.id = id;
        AgiAgentChatResultMetadataDTO.model = model;
        AgiAgentChatResultMetadataDTO.rateLimit = rateLimitVO;
        AgiAgentChatResultMetadataDTO.usage = usageVO;
        return AgiAgentChatResultMetadataDTO;
    }

    /**
     * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatResultMetadataDTO.RateLimitDTO} 的结果前端响应
     */
    @Data
    @Schema
    public static class RateLimitVO {

        @Schema(description = "最大请求限制：允许在耗尽速率限制之前的最大请求数量")
        private Long requestsLimit;

        @Schema(description = "剩余请求数量：在耗尽速率限制之前允许的剩余请求数量。")
        private Long requestsRemaining;

        @Schema(description = "请求重置时间：速率限制（基于请求）重置到初始状态的时间。")
        private Duration requestsReset;

        @Schema(description = "最大令牌限制：允许在耗尽速率限制之前的最大小令牌数量。")
        private Long tokensLimit;

        @Schema(description = "剩余令牌数量：在耗尽速率限制之前允许的剩余令牌数量。")
        private Long tokensRemaining;

        @Schema(description = "令牌重置时间：速率限制（基于令牌）重置到初始状态的时间。")
        private Duration tokensReset;

        public static RateLimitVO create(Long requestsLimit,
                                         Long requestsRemaining,
                                         Duration requestsReset,
                                         Long tokensLimit,
                                         Long tokensRemaining,
                                         Duration tokensReset) {
            RateLimitVO rateLimitVO = new RateLimitVO();
            rateLimitVO.requestsLimit = requestsLimit;
            rateLimitVO.requestsRemaining = requestsRemaining;
            rateLimitVO.requestsReset = requestsReset;
            rateLimitVO.tokensLimit = tokensLimit;
            rateLimitVO.tokensRemaining = tokensRemaining;
            rateLimitVO.tokensReset = tokensReset;

            return rateLimitVO;
        }
    }

    /**
     * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatResultMetadataDTO.UsageDTO} 的结果前端响应
     */
    @Data
    @Schema
    public static class UsageVO {

        @Schema(description = "在 AI 请求的 prompt 中使用的令牌数量。")
        private Integer promptTokens;

        @Schema(description = "在 AI 响应的 generation (aka completion) 中返回的令牌数量。")
        private Integer completionTokens;

        @Schema(description = "prompt 和 generation 累计使用的令牌数量。")
        private Integer totalTokens;

        @Schema(description = "从底层模型 API 响应中获取的原始使用数据。")
        private Object nativeUsage;

        public static UsageVO create(Integer promptTokens,
                                     Integer completionTokens,
                                     Integer totalTokens,
                                     Object nativeUsage) {
            UsageVO usageVO = new UsageVO();
            usageVO.promptTokens = promptTokens;
            usageVO.completionTokens = completionTokens;
            usageVO.totalTokens = totalTokens;
            usageVO.nativeUsage = nativeUsage;
            return usageVO;
        }
    }
}
