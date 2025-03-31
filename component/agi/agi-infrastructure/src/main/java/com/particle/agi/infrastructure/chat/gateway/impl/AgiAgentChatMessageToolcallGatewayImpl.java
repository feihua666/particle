package com.particle.agi.infrastructure.chat.gateway.impl;

import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcallId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolcallGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolcallService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.agi.infrastructure.chat.structmapping.AgiAgentChatMessageToolcallInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 智能体对话消息工具调用 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Component
public class AgiAgentChatMessageToolcallGatewayImpl extends AbstractBaseGatewayImpl<AgiAgentChatMessageToolcallId,AgiAgentChatMessageToolcall> implements AgiAgentChatMessageToolcallGateway {

    private IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService;

    @Override
    public AgiAgentChatMessageToolcall getById(AgiAgentChatMessageToolcallId agiAgentChatMessageToolcallId) {
        AgiAgentChatMessageToolcallDO byId = iAgiAgentChatMessageToolcallService.getById(agiAgentChatMessageToolcallId.getId());
        AgiAgentChatMessageToolcall agiAgentChatMessageToolcall = DomainFactory.create(AgiAgentChatMessageToolcall.class);
        agiAgentChatMessageToolcall = AgiAgentChatMessageToolcallInfrastructureStructMapping.instance. agiAgentChatMessageToolcallDOToAgiAgentChatMessageToolcall(agiAgentChatMessageToolcall,byId);
        return agiAgentChatMessageToolcall;
    }

    @Override
    public boolean doSave(AgiAgentChatMessageToolcall agiAgentChatMessageToolcall) {
        AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallDO = AgiAgentChatMessageToolcallInfrastructureStructMapping.instance.agiAgentChatMessageToolcallToAgiAgentChatMessageToolcallDO(agiAgentChatMessageToolcall);
        if (agiAgentChatMessageToolcallDO.getId() == null) {
            agiAgentChatMessageToolcallDO.setAddControl(agiAgentChatMessageToolcall.getAddControl());
            AgiAgentChatMessageToolcallDO add = iAgiAgentChatMessageToolcallService.add(agiAgentChatMessageToolcallDO);
            agiAgentChatMessageToolcall.setId(AgiAgentChatMessageToolcallId.of(add.getId()));
            return add != null;
        }
        agiAgentChatMessageToolcallDO.setUpdateControl(agiAgentChatMessageToolcall.getUpdateControl());
        AgiAgentChatMessageToolcallDO update = iAgiAgentChatMessageToolcallService.update(agiAgentChatMessageToolcallDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAgentChatMessageToolcallId agiAgentChatMessageToolcallId) {
        return iAgiAgentChatMessageToolcallService.deleteById(agiAgentChatMessageToolcallId.getId());
    }

    @Override
    public boolean delete(AgiAgentChatMessageToolcallId agiAgentChatMessageToolcallId, IdCommand idCommand) {
        return iAgiAgentChatMessageToolcallService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiAgentChatMessageToolcallService(IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService) {
        this.iAgiAgentChatMessageToolcallService = iAgiAgentChatMessageToolcallService;
    }
}
