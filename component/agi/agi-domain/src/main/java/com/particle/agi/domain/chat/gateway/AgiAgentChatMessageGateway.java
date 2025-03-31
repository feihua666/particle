package com.particle.agi.domain.chat.gateway;

import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.AgiAgentChatMessageId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 智能体对话消息 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
public interface AgiAgentChatMessageGateway extends IBaseGateway<AgiAgentChatMessageId,AgiAgentChatMessage> {
}
