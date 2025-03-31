package com.particle.agi.infrastructure.chat.gateway.impl;

import com.particle.agi.domain.chat.AgiAgentChatMessageTool;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.agi.infrastructure.chat.structmapping.AgiAgentChatMessageToolInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 智能体对话消息工具 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Component
public class AgiAgentChatMessageToolGatewayImpl extends AbstractBaseGatewayImpl<AgiAgentChatMessageToolId,AgiAgentChatMessageTool> implements AgiAgentChatMessageToolGateway {

    private IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService;

    @Override
    public AgiAgentChatMessageTool getById(AgiAgentChatMessageToolId agiAgentChatMessageToolId) {
        AgiAgentChatMessageToolDO byId = iAgiAgentChatMessageToolService.getById(agiAgentChatMessageToolId.getId());
        AgiAgentChatMessageTool agiAgentChatMessageTool = DomainFactory.create(AgiAgentChatMessageTool.class);
        agiAgentChatMessageTool = AgiAgentChatMessageToolInfrastructureStructMapping.instance. agiAgentChatMessageToolDOToAgiAgentChatMessageTool(agiAgentChatMessageTool,byId);
        return agiAgentChatMessageTool;
    }

    @Override
    public boolean doSave(AgiAgentChatMessageTool agiAgentChatMessageTool) {
        AgiAgentChatMessageToolDO agiAgentChatMessageToolDO = AgiAgentChatMessageToolInfrastructureStructMapping.instance.agiAgentChatMessageToolToAgiAgentChatMessageToolDO(agiAgentChatMessageTool);
        if (agiAgentChatMessageToolDO.getId() == null) {
            agiAgentChatMessageToolDO.setAddControl(agiAgentChatMessageTool.getAddControl());
            AgiAgentChatMessageToolDO add = iAgiAgentChatMessageToolService.add(agiAgentChatMessageToolDO);
            agiAgentChatMessageTool.setId(AgiAgentChatMessageToolId.of(add.getId()));
            return add != null;
        }
        agiAgentChatMessageToolDO.setUpdateControl(agiAgentChatMessageTool.getUpdateControl());
        AgiAgentChatMessageToolDO update = iAgiAgentChatMessageToolService.update(agiAgentChatMessageToolDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAgentChatMessageToolId agiAgentChatMessageToolId) {
        return iAgiAgentChatMessageToolService.deleteById(agiAgentChatMessageToolId.getId());
    }

    @Override
    public boolean delete(AgiAgentChatMessageToolId agiAgentChatMessageToolId, IdCommand idCommand) {
        return iAgiAgentChatMessageToolService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiAgentChatMessageToolService(IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService) {
        this.iAgiAgentChatMessageToolService = iAgiAgentChatMessageToolService;
    }
}
