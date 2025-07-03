package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsChannel;
import com.particle.cms.domain.CmsChannelId;
import com.particle.cms.domain.gateway.CmsChannelGateway;
import com.particle.cms.infrastructure.service.ICmsChannelService;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.infrastructure.structmapping.CmsChannelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 栏目 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Component
public class CmsChannelGatewayImpl extends AbstractBaseGatewayImpl<CmsChannelId,CmsChannel> implements CmsChannelGateway {

    private ICmsChannelService iCmsChannelService;

    @Override
    public CmsChannel getById(CmsChannelId cmsChannelId) {
        CmsChannelDO byId = iCmsChannelService.getById(cmsChannelId.getId());
        CmsChannel cmsChannel = DomainFactory.create(CmsChannel.class);
        cmsChannel = CmsChannelInfrastructureStructMapping.instance. cmsChannelDOToCmsChannel(cmsChannel,byId);
        return cmsChannel;
    }

    @Override
    public boolean doSave(CmsChannel cmsChannel) {
        CmsChannelDO cmsChannelDO = CmsChannelInfrastructureStructMapping.instance.cmsChannelToCmsChannelDO(cmsChannel);
        if (cmsChannelDO.getId() == null) {
            cmsChannelDO.setAddControl(cmsChannel.getAddControl());
            CmsChannelDO add = iCmsChannelService.add(cmsChannelDO);
            cmsChannel.setId(CmsChannelId.of(add.getId()));
            return add != null;
        }
        cmsChannelDO.setUpdateControl(cmsChannel.getUpdateControl());
        CmsChannelDO update = iCmsChannelService.update(cmsChannelDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsChannelId cmsChannelId) {
        return iCmsChannelService.deleteById(cmsChannelId.getId());
    }

    @Override
    public boolean delete(CmsChannelId cmsChannelId, IdCommand idCommand) {
        return iCmsChannelService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsChannelService(ICmsChannelService iCmsChannelService) {
        this.iCmsChannelService = iCmsChannelService;
    }
}
