package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
import com.particle.cms.domain.gateway.CmsContentGateway;
import com.particle.cms.infrastructure.service.ICmsContentService;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.infrastructure.structmapping.CmsContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Component
public class CmsContentGatewayImpl extends AbstractBaseGatewayImpl<CmsContentId,CmsContent> implements CmsContentGateway {

    private ICmsContentService iCmsContentService;

    @Override
    public CmsContent getById(CmsContentId cmsContentId) {
        CmsContentDO byId = iCmsContentService.getById(cmsContentId.getId());
        CmsContent cmsContent = DomainFactory.create(CmsContent.class);
        cmsContent = CmsContentInfrastructureStructMapping.instance. cmsContentDOToCmsContent(cmsContent,byId);
        return cmsContent;
    }

    @Override
    public boolean doSave(CmsContent cmsContent) {
        CmsContentDO cmsContentDO = CmsContentInfrastructureStructMapping.instance.cmsContentToCmsContentDO(cmsContent);
        if (cmsContentDO.getId() == null) {
            cmsContentDO.setAddControl(cmsContent.getAddControl());
            CmsContentDO add = iCmsContentService.add(cmsContentDO);
            cmsContent.setId(CmsContentId.of(add.getId()));
            return add != null;
        }
        cmsContentDO.setUpdateControl(cmsContent.getUpdateControl());
        CmsContentDO update = iCmsContentService.update(cmsContentDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsContentId cmsContentId) {
        return iCmsContentService.deleteById(cmsContentId.getId());
    }

    @Override
    public boolean delete(CmsContentId cmsContentId, IdCommand idCommand) {
        return iCmsContentService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsContentService(ICmsContentService iCmsContentService) {
        this.iCmsContentService = iCmsContentService;
    }
}
