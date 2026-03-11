package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsTemplate;
import com.particle.cms.domain.CmsTemplateId;
import com.particle.cms.domain.gateway.CmsTemplateGateway;
import com.particle.cms.infrastructure.service.ICmsTemplateService;
import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.cms.infrastructure.structmapping.CmsTemplateInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 模板 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Component
public class CmsTemplateGatewayImpl extends AbstractBaseGatewayImpl<CmsTemplateId,CmsTemplate> implements CmsTemplateGateway {

    private ICmsTemplateService iCmsTemplateService;

    @Override
    public CmsTemplate getById(CmsTemplateId cmsTemplateId) {
        CmsTemplateDO byId = iCmsTemplateService.getById(cmsTemplateId.getId());
        CmsTemplate cmsTemplate = DomainFactory.create(CmsTemplate.class);
        cmsTemplate = CmsTemplateInfrastructureStructMapping.instance. cmsTemplateDOToCmsTemplate(cmsTemplate,byId);
        return cmsTemplate;
    }

    @Override
    public boolean doSave(CmsTemplate cmsTemplate) {
        CmsTemplateDO cmsTemplateDO = CmsTemplateInfrastructureStructMapping.instance.cmsTemplateToCmsTemplateDO(cmsTemplate);
        if (cmsTemplateDO.getId() == null) {
            cmsTemplateDO.setAddControl(cmsTemplate.getAddControl());
            CmsTemplateDO add = iCmsTemplateService.add(cmsTemplateDO);
            cmsTemplate.setId(CmsTemplateId.of(add.getId()));
            return add != null;
        }
        cmsTemplateDO.setUpdateControl(cmsTemplate.getUpdateControl());
        CmsTemplateDO update = iCmsTemplateService.update(cmsTemplateDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsTemplateId cmsTemplateId) {
        return iCmsTemplateService.deleteById(cmsTemplateId.getId());
    }

    @Override
    public boolean delete(CmsTemplateId cmsTemplateId, IdCommand idCommand) {
        return iCmsTemplateService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsTemplateService(ICmsTemplateService iCmsTemplateService) {
        this.iCmsTemplateService = iCmsTemplateService;
    }
}
