package com.particle.data.infrastructure.dynamicdata.gateway.impl;

import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.data.infrastructure.dynamicdata.structmapping.DynamicDataIndicatorCategoryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据指标分类 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Component
public class DynamicDataIndicatorCategoryGatewayImpl extends AbstractBaseGatewayImpl<DynamicDataIndicatorCategoryId,DynamicDataIndicatorCategory> implements DynamicDataIndicatorCategoryGateway {

    private IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService;
    private TableServivce tableServivce;

    @Override
    public DynamicDataIndicatorCategory getById(DynamicDataIndicatorCategoryId dynamicDataIndicatorCategoryId) {
        DynamicDataIndicatorCategoryDO byId = iDynamicDataIndicatorCategoryService.getById(dynamicDataIndicatorCategoryId.getId());
        DynamicDataIndicatorCategory dynamicDataIndicatorCategory = DomainFactory.create(DynamicDataIndicatorCategory.class);
        dynamicDataIndicatorCategory = DynamicDataIndicatorCategoryInfrastructureStructMapping.instance. dynamicDataIndicatorCategoryDOToDynamicDataIndicatorCategory(dynamicDataIndicatorCategory,byId);
        return dynamicDataIndicatorCategory;
    }

    @Override
    public boolean doSave(DynamicDataIndicatorCategory dynamicDataIndicatorCategory) {
        DynamicDataIndicatorCategoryDO dynamicDataIndicatorCategoryDO = DynamicDataIndicatorCategoryInfrastructureStructMapping.instance.dynamicDataIndicatorCategoryToDynamicDataIndicatorCategoryDO(dynamicDataIndicatorCategory);
        if (dynamicDataIndicatorCategoryDO.getId() == null) {
            dynamicDataIndicatorCategoryDO.setAddControl(dynamicDataIndicatorCategory.getAddControl());
            DynamicDataIndicatorCategoryDO add = iDynamicDataIndicatorCategoryService.add(dynamicDataIndicatorCategoryDO);
            dynamicDataIndicatorCategory.setId(DynamicDataIndicatorCategoryId.of(add.getId()));
            return add != null;
        }
        dynamicDataIndicatorCategoryDO.setUpdateControl(dynamicDataIndicatorCategory.getUpdateControl());
        DynamicDataIndicatorCategoryDO update = iDynamicDataIndicatorCategoryService.update(dynamicDataIndicatorCategoryDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicDataIndicatorCategoryId dynamicDataIndicatorCategoryId) {
        return iDynamicDataIndicatorCategoryService.deleteById(dynamicDataIndicatorCategoryId.getId());
    }

    @Override
    public boolean delete(DynamicDataIndicatorCategoryId dynamicDataIndicatorCategoryId, IdCommand idCommand) {
        return iDynamicDataIndicatorCategoryService.deleteById(idCommand);
    }

    @Override
    public void createTable(String tableName, String comment) {
        // mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (!tableServivce.isExistTable(tableName)) {
            tableServivce.createTable(tableName,comment);
        }
    }

    @Override
    public void dropTable(String tableName) {
        // 原因是 mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (tableServivce.isExistTable(tableName)) {
            tableServivce.dropTable(tableName);
        }
    }

    @Autowired
    public void setIDynamicDataIndicatorCategoryService(IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService) {
        this.iDynamicDataIndicatorCategoryService = iDynamicDataIndicatorCategoryService;
    }

    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
}

