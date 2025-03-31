package com.particle.agi.domain.agent.gateway;

import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
import com.particle.agi.domain.agent.value.AgiAgentChatParam;
import com.particle.agi.domain.agent.value.AgiAgentChatResultDTO;
import com.particle.common.domain.gateway.IBaseGateway;
import reactor.core.publisher.Flux;

/**
 * <p>
 * 智能体 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
public interface AgiAgentGateway extends IBaseGateway<AgiAgentId,AgiAgent> {

    /**
     * 智能体聊天对话
     * @param agiAgentChatParam
     * @return
     */
    public Flux<AgiAgentChatResultDTO> chatStream(AgiAgentChatParam agiAgentChatParam);
}
