package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsTemplateContent;
import com.particle.cms.domain.CmsTemplateContentId;
import com.particle.cms.domain.gateway.CmsTemplateContentGateway;
import com.particle.cms.infrastructure.service.ICmsTemplateContentService;
import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;
import com.particle.cms.infrastructure.structmapping.CmsTemplateContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 模板内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Component
public class CmsTemplateContentGatewayImpl extends AbstractBaseGatewayImpl<CmsTemplateContentId,CmsTemplateContent> implements CmsTemplateContentGateway {

    private ICmsTemplateContentService iCmsTemplateContentService;

    @Override
    public CmsTemplateContent getById(CmsTemplateContentId cmsTemplateContentId) {
        CmsTemplateContentDO byId = iCmsTemplateContentService.getById(cmsTemplateContentId.getId());
        CmsTemplateContent cmsTemplateContent = DomainFactory.create(CmsTemplateContent.class);
        cmsTemplateContent = CmsTemplateContentInfrastructureStructMapping.instance. cmsTemplateContentDOToCmsTemplateContent(cmsTemplateContent,byId);
        return cmsTemplateContent;
    }

    @Override
    public boolean doSave(CmsTemplateContent cmsTemplateContent) {
        CmsTemplateContentDO cmsTemplateContentDO = CmsTemplateContentInfrastructureStructMapping.instance.cmsTemplateContentToCmsTemplateContentDO(cmsTemplateContent);
        if (cmsTemplateContentDO.getId() == null) {
            cmsTemplateContentDO.setAddControl(cmsTemplateContent.getAddControl());
            CmsTemplateContentDO add = iCmsTemplateContentService.add(cmsTemplateContentDO);
            cmsTemplateContent.setId(CmsTemplateContentId.of(add.getId()));
            return add != null;
        }
        cmsTemplateContentDO.setUpdateControl(cmsTemplateContent.getUpdateControl());
        CmsTemplateContentDO update = iCmsTemplateContentService.update(cmsTemplateContentDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsTemplateContentId cmsTemplateContentId) {
        return iCmsTemplateContentService.deleteById(cmsTemplateContentId.getId());
    }

    @Override
    public boolean delete(CmsTemplateContentId cmsTemplateContentId, IdCommand idCommand) {
        return iCmsTemplateContentService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsTemplateContentService(ICmsTemplateContentService iCmsTemplateContentService) {
        this.iCmsTemplateContentService = iCmsTemplateContentService;
    }
}
