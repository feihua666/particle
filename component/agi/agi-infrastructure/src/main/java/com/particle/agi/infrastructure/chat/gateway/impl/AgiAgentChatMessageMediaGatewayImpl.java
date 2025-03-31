package com.particle.agi.infrastructure.chat.gateway.impl;

import com.particle.agi.domain.chat.AgiAgentChatMessageMedia;
import com.particle.agi.domain.chat.AgiAgentChatMessageMediaId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageMediaGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageMediaService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.agi.infrastructure.chat.structmapping.AgiAgentChatMessageMediaInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 智能体对话消息媒体 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Component
public class AgiAgentChatMessageMediaGatewayImpl extends AbstractBaseGatewayImpl<AgiAgentChatMessageMediaId,AgiAgentChatMessageMedia> implements AgiAgentChatMessageMediaGateway {

    private IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService;

    @Override
    public AgiAgentChatMessageMedia getById(AgiAgentChatMessageMediaId agiAgentChatMessageMediaId) {
        AgiAgentChatMessageMediaDO byId = iAgiAgentChatMessageMediaService.getById(agiAgentChatMessageMediaId.getId());
        AgiAgentChatMessageMedia agiAgentChatMessageMedia = DomainFactory.create(AgiAgentChatMessageMedia.class);
        agiAgentChatMessageMedia = AgiAgentChatMessageMediaInfrastructureStructMapping.instance. agiAgentChatMessageMediaDOToAgiAgentChatMessageMedia(agiAgentChatMessageMedia,byId);
        return agiAgentChatMessageMedia;
    }

    @Override
    public boolean doSave(AgiAgentChatMessageMedia agiAgentChatMessageMedia) {
        AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO = AgiAgentChatMessageMediaInfrastructureStructMapping.instance.agiAgentChatMessageMediaToAgiAgentChatMessageMediaDO(agiAgentChatMessageMedia);
        if (agiAgentChatMessageMediaDO.getId() == null) {
            agiAgentChatMessageMediaDO.setAddControl(agiAgentChatMessageMedia.getAddControl());
            AgiAgentChatMessageMediaDO add = iAgiAgentChatMessageMediaService.add(agiAgentChatMessageMediaDO);
            agiAgentChatMessageMedia.setId(AgiAgentChatMessageMediaId.of(add.getId()));
            return add != null;
        }
        agiAgentChatMessageMediaDO.setUpdateControl(agiAgentChatMessageMedia.getUpdateControl());
        AgiAgentChatMessageMediaDO update = iAgiAgentChatMessageMediaService.update(agiAgentChatMessageMediaDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAgentChatMessageMediaId agiAgentChatMessageMediaId) {
        return iAgiAgentChatMessageMediaService.deleteById(agiAgentChatMessageMediaId.getId());
    }

    @Override
    public boolean delete(AgiAgentChatMessageMediaId agiAgentChatMessageMediaId, IdCommand idCommand) {
        return iAgiAgentChatMessageMediaService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiAgentChatMessageMediaService(IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService) {
        this.iAgiAgentChatMessageMediaService = iAgiAgentChatMessageMediaService;
    }
}
