package com.particle.agi.domain.agent.value;

import com.particle.global.dto.basic.VO;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 智能体对话结果内容
 * 参考{@link org.springframework.ai.chat.model.Generation}
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
public class AgiAgentChatResultContentDTO extends VO {
    /**
     * 智能体对话结果内容
     */
    private AgiAgentChatAssistantMessageDTO output;

    /**
     * 智能体对话结果元数据
     */
    private ChatGenerationMetadataDTO metadata;
    /**
     * 参考 {@link org.springframework.ai.chat.metadata.ChatGenerationMetadata}
     * 主要参考其默认实现{@link org.springframework.ai.chat.metadata.DefaultChatGenerationMetadata}
     */
    @Data
    public static class ChatGenerationMetadataDTO {


        private String finishReason;

        private Set<String> contentFilters;

        private Map<String, Object> metadata;

        public static ChatGenerationMetadataDTO create(String finishReason, Set<String> contentFilters, Map<String, Object> metadata) {
            ChatGenerationMetadataDTO chatGenerationMetadataDTO = new ChatGenerationMetadataDTO();
            chatGenerationMetadataDTO.finishReason = finishReason;
            chatGenerationMetadataDTO.contentFilters = contentFilters;
            chatGenerationMetadataDTO.metadata = metadata;

            return chatGenerationMetadataDTO;
        }
    }

    public static AgiAgentChatResultContentDTO create(AgiAgentChatAssistantMessageDTO output, ChatGenerationMetadataDTO metadata) {
        AgiAgentChatResultContentDTO agiAgentChatResultContentDTO = new AgiAgentChatResultContentDTO();
        agiAgentChatResultContentDTO.output = output;
        agiAgentChatResultContentDTO.metadata = metadata;
        return agiAgentChatResultContentDTO;
    }
}
