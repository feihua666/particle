package com.particle.agi.client.agent.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 智能体对话助手消息响应数据对象
 * 承接{@link com.particle.agi.domain.agent.value.AgiAgentChatAssistantMessageDTO} 的前端响应对象
 * </p>
 *
 * @author yangwei
 * @since 2025-02-24 14:15:14
 */
@Schema
@Data
public class AgiAgentChatAssistantMessageVO extends AgiAgentChatBaseMessageVO {

    @Schema(description = "工具调用信息")
    private List<AgiAgentChatToolCallVO> toolCalls;

    @Schema(description = "媒体信息")
    private List<AgiAgentChatMediaVO> medias;

    public static AgiAgentChatAssistantMessageVO create(
            String messageType,
            String text,
            Map<String, Object> metadata,
            List<AgiAgentChatToolCallVO> toolCalls,
            List<AgiAgentChatMediaVO> medias) {
        AgiAgentChatAssistantMessageVO agiAgentChatAssistantMessageDTO = new AgiAgentChatAssistantMessageVO();

        agiAgentChatAssistantMessageDTO.setMessageType(messageType);
        agiAgentChatAssistantMessageDTO.setText(text);
        agiAgentChatAssistantMessageDTO.setMetadata(metadata);

        agiAgentChatAssistantMessageDTO.toolCalls = toolCalls;
        agiAgentChatAssistantMessageDTO.medias = medias;
        return agiAgentChatAssistantMessageDTO;
    }
}
