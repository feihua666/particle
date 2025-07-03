package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsContentMultimedia;
import com.particle.cms.domain.CmsContentMultimediaId;
import com.particle.cms.domain.gateway.CmsContentMultimediaGateway;
import com.particle.cms.infrastructure.service.ICmsContentMultimediaService;
import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;
import com.particle.cms.infrastructure.structmapping.CmsContentMultimediaInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 内容多媒体 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Component
public class CmsContentMultimediaGatewayImpl extends AbstractBaseGatewayImpl<CmsContentMultimediaId,CmsContentMultimedia> implements CmsContentMultimediaGateway {

    private ICmsContentMultimediaService iCmsContentMultimediaService;

    @Override
    public CmsContentMultimedia getById(CmsContentMultimediaId cmsContentMultimediaId) {
        CmsContentMultimediaDO byId = iCmsContentMultimediaService.getById(cmsContentMultimediaId.getId());
        CmsContentMultimedia cmsContentMultimedia = DomainFactory.create(CmsContentMultimedia.class);
        cmsContentMultimedia = CmsContentMultimediaInfrastructureStructMapping.instance. cmsContentMultimediaDOToCmsContentMultimedia(cmsContentMultimedia,byId);
        return cmsContentMultimedia;
    }

    @Override
    public boolean doSave(CmsContentMultimedia cmsContentMultimedia) {
        CmsContentMultimediaDO cmsContentMultimediaDO = CmsContentMultimediaInfrastructureStructMapping.instance.cmsContentMultimediaToCmsContentMultimediaDO(cmsContentMultimedia);
        if (cmsContentMultimediaDO.getId() == null) {
            cmsContentMultimediaDO.setAddControl(cmsContentMultimedia.getAddControl());
            CmsContentMultimediaDO add = iCmsContentMultimediaService.add(cmsContentMultimediaDO);
            cmsContentMultimedia.setId(CmsContentMultimediaId.of(add.getId()));
            return add != null;
        }
        cmsContentMultimediaDO.setUpdateControl(cmsContentMultimedia.getUpdateControl());
        CmsContentMultimediaDO update = iCmsContentMultimediaService.update(cmsContentMultimediaDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsContentMultimediaId cmsContentMultimediaId) {
        return iCmsContentMultimediaService.deleteById(cmsContentMultimediaId.getId());
    }

    @Override
    public boolean delete(CmsContentMultimediaId cmsContentMultimediaId, IdCommand idCommand) {
        return iCmsContentMultimediaService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsContentMultimediaService(ICmsContentMultimediaService iCmsContentMultimediaService) {
        this.iCmsContentMultimediaService = iCmsContentMultimediaService;
    }
}
