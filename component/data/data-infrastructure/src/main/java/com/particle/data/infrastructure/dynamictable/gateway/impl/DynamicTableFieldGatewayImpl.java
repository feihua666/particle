package com.particle.data.infrastructure.dynamictable.gateway.impl;

import com.particle.data.domain.dynamictable.DynamicTableField;
import com.particle.data.domain.dynamictable.DynamicTableFieldId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableFieldGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.structmapping.DynamicTableFieldInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据表格字段 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Component
public class DynamicTableFieldGatewayImpl extends AbstractBaseGatewayImpl<DynamicTableFieldId,DynamicTableField> implements DynamicTableFieldGateway {

    private IDynamicTableFieldService iDynamicTableFieldService;

    @Override
    public DynamicTableField getById(DynamicTableFieldId dynamicTableFieldId) {
        DynamicTableFieldDO byId = iDynamicTableFieldService.getById(dynamicTableFieldId.getId());
        DynamicTableField dynamicTableField = DomainFactory.create(DynamicTableField.class);
        dynamicTableField = DynamicTableFieldInfrastructureStructMapping.instance. dynamicTableFieldDOToDynamicTableField(dynamicTableField,byId);
        return dynamicTableField;
    }

    @Override
    public boolean doSave(DynamicTableField dynamicTableField) {
        DynamicTableFieldDO dynamicTableFieldDO = DynamicTableFieldInfrastructureStructMapping.instance.dynamicTableFieldToDynamicTableFieldDO(dynamicTableField);
        if (dynamicTableFieldDO.getId() == null) {
            dynamicTableFieldDO.setAddControl(dynamicTableField.getAddControl());
            DynamicTableFieldDO add = iDynamicTableFieldService.add(dynamicTableFieldDO);
            dynamicTableField.setId(DynamicTableFieldId.of(add.getId()));
            return add != null;
        }
        dynamicTableFieldDO.setUpdateControl(dynamicTableField.getUpdateControl());
        DynamicTableFieldDO update = iDynamicTableFieldService.update(dynamicTableFieldDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicTableFieldId dynamicTableFieldId) {
        return iDynamicTableFieldService.deleteById(dynamicTableFieldId.getId());
    }

    @Override
    public boolean delete(DynamicTableFieldId dynamicTableFieldId, IdCommand idCommand) {
        return iDynamicTableFieldService.deleteById(idCommand);
    }

    @Autowired
    public void setIDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
        this.iDynamicTableFieldService = iDynamicTableFieldService;
    }
}
