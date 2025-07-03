package com.particle.cms.infrastructure.gateway.impl;

import com.particle.cms.domain.CmsContentCategory;
import com.particle.cms.domain.CmsContentCategoryId;
import com.particle.cms.domain.gateway.CmsContentCategoryGateway;
import com.particle.cms.infrastructure.service.ICmsContentCategoryService;
import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;
import com.particle.cms.infrastructure.structmapping.CmsContentCategoryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 内容分类 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Component
public class CmsContentCategoryGatewayImpl extends AbstractBaseGatewayImpl<CmsContentCategoryId,CmsContentCategory> implements CmsContentCategoryGateway {

    private ICmsContentCategoryService iCmsContentCategoryService;

    @Override
    public CmsContentCategory getById(CmsContentCategoryId cmsContentCategoryId) {
        CmsContentCategoryDO byId = iCmsContentCategoryService.getById(cmsContentCategoryId.getId());
        CmsContentCategory cmsContentCategory = DomainFactory.create(CmsContentCategory.class);
        cmsContentCategory = CmsContentCategoryInfrastructureStructMapping.instance. cmsContentCategoryDOToCmsContentCategory(cmsContentCategory,byId);
        return cmsContentCategory;
    }

    @Override
    public boolean doSave(CmsContentCategory cmsContentCategory) {
        CmsContentCategoryDO cmsContentCategoryDO = CmsContentCategoryInfrastructureStructMapping.instance.cmsContentCategoryToCmsContentCategoryDO(cmsContentCategory);
        if (cmsContentCategoryDO.getId() == null) {
            cmsContentCategoryDO.setAddControl(cmsContentCategory.getAddControl());
            CmsContentCategoryDO add = iCmsContentCategoryService.add(cmsContentCategoryDO);
            cmsContentCategory.setId(CmsContentCategoryId.of(add.getId()));
            return add != null;
        }
        cmsContentCategoryDO.setUpdateControl(cmsContentCategory.getUpdateControl());
        CmsContentCategoryDO update = iCmsContentCategoryService.update(cmsContentCategoryDO);
        return update != null;
    }

    @Override
    public boolean delete(CmsContentCategoryId cmsContentCategoryId) {
        return iCmsContentCategoryService.deleteById(cmsContentCategoryId.getId());
    }

    @Override
    public boolean delete(CmsContentCategoryId cmsContentCategoryId, IdCommand idCommand) {
        return iCmsContentCategoryService.deleteById(idCommand);
    }

    @Autowired
    public void setICmsContentCategoryService(ICmsContentCategoryService iCmsContentCategoryService) {
        this.iCmsContentCategoryService = iCmsContentCategoryService;
    }
}
