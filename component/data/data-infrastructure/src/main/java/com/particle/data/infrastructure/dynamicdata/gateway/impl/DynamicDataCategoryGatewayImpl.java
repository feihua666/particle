package com.particle.data.infrastructure.dynamicdata.gateway.impl;

import com.particle.data.domain.dynamicdata.DynamicDataCategory;
import com.particle.data.domain.dynamicdata.DynamicDataCategoryId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataCategoryGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataCategoryService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import com.particle.data.infrastructure.dynamicdata.structmapping.DynamicDataCategoryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据分类 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Component
public class DynamicDataCategoryGatewayImpl extends AbstractBaseGatewayImpl<DynamicDataCategoryId,DynamicDataCategory> implements DynamicDataCategoryGateway {

    private IDynamicDataCategoryService iDynamicDataCategoryService;

    @Override
    public DynamicDataCategory getById(DynamicDataCategoryId dynamicDataCategoryId) {
        DynamicDataCategoryDO byId = iDynamicDataCategoryService.getById(dynamicDataCategoryId.getId());
        DynamicDataCategory dynamicDataCategory = DomainFactory.create(DynamicDataCategory.class);
        dynamicDataCategory = DynamicDataCategoryInfrastructureStructMapping.instance. dynamicDataCategoryDOToDynamicDataCategory(dynamicDataCategory,byId);
        return dynamicDataCategory;
    }

    @Override
    public boolean doSave(DynamicDataCategory dynamicDataCategory) {
        DynamicDataCategoryDO dynamicDataCategoryDO = DynamicDataCategoryInfrastructureStructMapping.instance.dynamicDataCategoryToDynamicDataCategoryDO(dynamicDataCategory);
        if (dynamicDataCategoryDO.getId() == null) {
            dynamicDataCategoryDO.setAddControl(dynamicDataCategory.getAddControl());
            DynamicDataCategoryDO add = iDynamicDataCategoryService.add(dynamicDataCategoryDO);
            dynamicDataCategory.setId(DynamicDataCategoryId.of(add.getId()));
            return add != null;
        }
        dynamicDataCategoryDO.setUpdateControl(dynamicDataCategory.getUpdateControl());
        DynamicDataCategoryDO update = iDynamicDataCategoryService.update(dynamicDataCategoryDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicDataCategoryId dynamicDataCategoryId) {
        return iDynamicDataCategoryService.deleteById(dynamicDataCategoryId.getId());
    }

    @Override
    public boolean delete(DynamicDataCategoryId dynamicDataCategoryId, IdCommand idCommand) {
        return iDynamicDataCategoryService.deleteById(idCommand);
    }

    @Autowired
    public void setIDynamicDataCategoryService(IDynamicDataCategoryService iDynamicDataCategoryService) {
        this.iDynamicDataCategoryService = iDynamicDataCategoryService;
    }
}
