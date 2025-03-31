package com.particle.agi.client.agent.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 智能体对话结果内容
 * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatResultContentDTO} 的结果前端响应
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Schema
@Data
public class AgiAgentChatResponseContentVO extends VO {

    @Schema(description = "智能体对话结果内容")
    private AgiAgentChatAssistantMessageVO output;

    @Schema(description = "智能体对话结果元数据")
    private ChatGenerationMetadataVO metadata;
    /**
     * 承接 {@link com.particle.agi.domain.agent.value.AgiAgentChatResultContentDTO.ChatGenerationMetadataDTO} 的结果前端响应
     */
    @Schema
    @Data
    public static class ChatGenerationMetadataVO {


        @Schema(description = "结束原因")
        private String finishReason;

        @Schema(description = "内容过滤器")
        private Set<String> contentFilters;

        @Schema(description = "元数据")
        private Map<String, Object> metadata;

        public static ChatGenerationMetadataVO create(String finishReason, Set<String> contentFilters, Map<String, Object> metadata) {
            ChatGenerationMetadataVO chatGenerationMetadataVO = new ChatGenerationMetadataVO();
            chatGenerationMetadataVO.finishReason = finishReason;
            chatGenerationMetadataVO.contentFilters = contentFilters;
            chatGenerationMetadataVO.metadata = metadata;

            return chatGenerationMetadataVO;
        }
    }

    public static AgiAgentChatResponseContentVO create(AgiAgentChatAssistantMessageVO output, ChatGenerationMetadataVO metadata) {
        AgiAgentChatResponseContentVO agiAgentChatResultContentDTO = new AgiAgentChatResponseContentVO();
        agiAgentChatResultContentDTO.output = output;
        agiAgentChatResultContentDTO.metadata = metadata;
        return agiAgentChatResultContentDTO;
    }
}
