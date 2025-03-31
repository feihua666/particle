package com.particle.agi.domain.chat.gateway;

import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcallId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 智能体对话消息工具调用 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
public interface AgiAgentChatMessageToolcallGateway extends IBaseGateway<AgiAgentChatMessageToolcallId,AgiAgentChatMessageToolcall> {
}
