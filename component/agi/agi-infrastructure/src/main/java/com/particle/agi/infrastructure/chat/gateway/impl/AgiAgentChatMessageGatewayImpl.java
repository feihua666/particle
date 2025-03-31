package com.particle.agi.infrastructure.chat.gateway.impl;

import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.AgiAgentChatMessageId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.infrastructure.chat.structmapping.AgiAgentChatMessageInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 智能体对话消息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Component
public class AgiAgentChatMessageGatewayImpl extends AbstractBaseGatewayImpl<AgiAgentChatMessageId,AgiAgentChatMessage> implements AgiAgentChatMessageGateway {

    private IAgiAgentChatMessageService iAgiAgentChatMessageService;

    @Override
    public AgiAgentChatMessage getById(AgiAgentChatMessageId agiAgentChatMessageId) {
        AgiAgentChatMessageDO byId = iAgiAgentChatMessageService.getById(agiAgentChatMessageId.getId());
        AgiAgentChatMessage agiAgentChatMessage = DomainFactory.create(AgiAgentChatMessage.class);
        agiAgentChatMessage = AgiAgentChatMessageInfrastructureStructMapping.instance. agiAgentChatMessageDOToAgiAgentChatMessage(agiAgentChatMessage,byId);
        return agiAgentChatMessage;
    }

    @Override
    public boolean doSave(AgiAgentChatMessage agiAgentChatMessage) {
        AgiAgentChatMessageDO agiAgentChatMessageDO = AgiAgentChatMessageInfrastructureStructMapping.instance.agiAgentChatMessageToAgiAgentChatMessageDO(agiAgentChatMessage);
        if (agiAgentChatMessageDO.getId() == null) {
            agiAgentChatMessageDO.setAddControl(agiAgentChatMessage.getAddControl());
            AgiAgentChatMessageDO add = iAgiAgentChatMessageService.add(agiAgentChatMessageDO);
            agiAgentChatMessage.setId(AgiAgentChatMessageId.of(add.getId()));
            return add != null;
        }
        agiAgentChatMessageDO.setUpdateControl(agiAgentChatMessage.getUpdateControl());
        AgiAgentChatMessageDO update = iAgiAgentChatMessageService.update(agiAgentChatMessageDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAgentChatMessageId agiAgentChatMessageId) {
        return iAgiAgentChatMessageService.deleteById(agiAgentChatMessageId.getId());
    }

    @Override
    public boolean delete(AgiAgentChatMessageId agiAgentChatMessageId, IdCommand idCommand) {
        return iAgiAgentChatMessageService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiAgentChatMessageService(IAgiAgentChatMessageService iAgiAgentChatMessageService) {
        this.iAgiAgentChatMessageService = iAgiAgentChatMessageService;
    }
}
