package com.particle.agi.infrastructure.chat.gateway.impl;

import com.particle.agi.domain.chat.AgiAgentChat;
import com.particle.agi.domain.chat.AgiAgentChatId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.agi.infrastructure.chat.structmapping.AgiAgentChatInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 智能体对话 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Component
public class AgiAgentChatGatewayImpl extends AbstractBaseGatewayImpl<AgiAgentChatId,AgiAgentChat> implements AgiAgentChatGateway {

    private IAgiAgentChatService iAgiAgentChatService;

    @Override
    public AgiAgentChat getById(AgiAgentChatId agiAgentChatId) {
        AgiAgentChatDO byId = iAgiAgentChatService.getById(agiAgentChatId.getId());
        AgiAgentChat agiAgentChat = DomainFactory.create(AgiAgentChat.class);
        agiAgentChat = AgiAgentChatInfrastructureStructMapping.instance. agiAgentChatDOToAgiAgentChat(agiAgentChat,byId);
        return agiAgentChat;
    }

    @Override
    public boolean doSave(AgiAgentChat agiAgentChat) {
        AgiAgentChatDO agiAgentChatDO = AgiAgentChatInfrastructureStructMapping.instance.agiAgentChatToAgiAgentChatDO(agiAgentChat);
        if (agiAgentChatDO.getId() == null) {
            agiAgentChatDO.setAddControl(agiAgentChat.getAddControl());
            AgiAgentChatDO add = iAgiAgentChatService.add(agiAgentChatDO);
            agiAgentChat.setId(AgiAgentChatId.of(add.getId()));
            return add != null;
        }
        agiAgentChatDO.setUpdateControl(agiAgentChat.getUpdateControl());
        AgiAgentChatDO update = iAgiAgentChatService.update(agiAgentChatDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAgentChatId agiAgentChatId) {
        return iAgiAgentChatService.deleteById(agiAgentChatId.getId());
    }

    @Override
    public boolean delete(AgiAgentChatId agiAgentChatId, IdCommand idCommand) {
        return iAgiAgentChatService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiAgentChatService(IAgiAgentChatService iAgiAgentChatService) {
        this.iAgiAgentChatService = iAgiAgentChatService;
    }
}
