package com.particle.data.infrastructure.dynamictable.gateway.impl;

import com.particle.data.domain.dynamictable.DynamicTable;
import com.particle.data.domain.dynamictable.DynamicTableId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.structmapping.DynamicTableInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据表格 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Component
public class DynamicTableGatewayImpl extends AbstractBaseGatewayImpl<DynamicTableId,DynamicTable> implements DynamicTableGateway {

    private IDynamicTableService iDynamicTableService;

    @Override
    public DynamicTable getById(DynamicTableId dynamicTableId) {
        DynamicTableDO byId = iDynamicTableService.getById(dynamicTableId.getId());
        DynamicTable dynamicTable = DomainFactory.create(DynamicTable.class);
        dynamicTable = DynamicTableInfrastructureStructMapping.instance. dynamicTableDOToDynamicTable(dynamicTable,byId);
        return dynamicTable;
    }

    @Override
    public boolean doSave(DynamicTable dynamicTable) {
        DynamicTableDO dynamicTableDO = DynamicTableInfrastructureStructMapping.instance.dynamicTableToDynamicTableDO(dynamicTable);
        if (dynamicTableDO.getId() == null) {
            dynamicTableDO.setAddControl(dynamicTable.getAddControl());
            DynamicTableDO add = iDynamicTableService.add(dynamicTableDO);
            dynamicTable.setId(DynamicTableId.of(add.getId()));
            return add != null;
        }
        dynamicTableDO.setUpdateControl(dynamicTable.getUpdateControl());
        DynamicTableDO update = iDynamicTableService.update(dynamicTableDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicTableId dynamicTableId) {
        return iDynamicTableService.deleteById(dynamicTableId.getId());
    }

    @Override
    public boolean delete(DynamicTableId dynamicTableId, IdCommand idCommand) {
        return iDynamicTableService.deleteById(idCommand);
    }

    @Autowired
    public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
        this.iDynamicTableService = iDynamicTableService;
    }
}
