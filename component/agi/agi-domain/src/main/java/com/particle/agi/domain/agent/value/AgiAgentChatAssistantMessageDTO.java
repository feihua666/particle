package com.particle.agi.domain.agent.value;

import com.particle.agi.domain.enums.AiMessageType;
import com.particle.agi.domain.values.AgiMedia;
import com.particle.global.dto.basic.VO;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 智能体对话助手消息，一般是响应的output或者是请求prompt的预置对话消息
 * </p>
 *
 * @author yangwei
 * @since 2025/2/18 13:26
 */
@Data
public class AgiAgentChatAssistantMessageDTO extends AgiAgentChatBaseMessageDTO {


    private List<AgiAgentChatToolCallDTO> toolCalls;

    private List<AgiMedia> medias;

    public static AgiAgentChatAssistantMessageDTO create(
            String messageType,
            String text,
            Map<String, Object> metadata,
            List<AgiAgentChatToolCallDTO> toolCalls,
            List<AgiMedia> medias) {
        AgiAgentChatAssistantMessageDTO agiAgentChatAssistantMessageDTO = new AgiAgentChatAssistantMessageDTO();

        agiAgentChatAssistantMessageDTO.setMessageType(messageType);
        agiAgentChatAssistantMessageDTO.setText(text);
        agiAgentChatAssistantMessageDTO.setMetadata(metadata);

        agiAgentChatAssistantMessageDTO.toolCalls = toolCalls;
        agiAgentChatAssistantMessageDTO.medias = medias;
        return agiAgentChatAssistantMessageDTO;
    }
}
