package com.particle.data.infrastructure.dynamictable.gateway.impl;

import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecordId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableUploadRecordGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableUploadRecordService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import com.particle.data.infrastructure.dynamictable.structmapping.DynamicTableUploadRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据表格上传记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Component
public class DynamicTableUploadRecordGatewayImpl extends AbstractBaseGatewayImpl<DynamicTableUploadRecordId,DynamicTableUploadRecord> implements DynamicTableUploadRecordGateway {

    private IDynamicTableUploadRecordService iDynamicTableUploadRecordService;

    @Override
    public DynamicTableUploadRecord getById(DynamicTableUploadRecordId dynamicTableUploadRecordId) {
        DynamicTableUploadRecordDO byId = iDynamicTableUploadRecordService.getById(dynamicTableUploadRecordId.getId());
        DynamicTableUploadRecord dynamicTableUploadRecord = DomainFactory.create(DynamicTableUploadRecord.class);
        dynamicTableUploadRecord = DynamicTableUploadRecordInfrastructureStructMapping.instance. dynamicTableUploadRecordDOToDynamicTableUploadRecord(dynamicTableUploadRecord,byId);
        return dynamicTableUploadRecord;
    }

    @Override
    public boolean doSave(DynamicTableUploadRecord dynamicTableUploadRecord) {
        DynamicTableUploadRecordDO dynamicTableUploadRecordDO = DynamicTableUploadRecordInfrastructureStructMapping.instance.dynamicTableUploadRecordToDynamicTableUploadRecordDO(dynamicTableUploadRecord);
        if (dynamicTableUploadRecordDO.getId() == null) {
            dynamicTableUploadRecordDO.setAddControl(dynamicTableUploadRecord.getAddControl());
            DynamicTableUploadRecordDO add = iDynamicTableUploadRecordService.add(dynamicTableUploadRecordDO);
            dynamicTableUploadRecord.setId(DynamicTableUploadRecordId.of(add.getId()));
            return add != null;
        }
        dynamicTableUploadRecordDO.setUpdateControl(dynamicTableUploadRecord.getUpdateControl());
        DynamicTableUploadRecordDO update = iDynamicTableUploadRecordService.update(dynamicTableUploadRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicTableUploadRecordId dynamicTableUploadRecordId) {
        return iDynamicTableUploadRecordService.deleteById(dynamicTableUploadRecordId.getId());
    }

    @Override
    public boolean delete(DynamicTableUploadRecordId dynamicTableUploadRecordId, IdCommand idCommand) {
        return iDynamicTableUploadRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setIDynamicTableUploadRecordService(IDynamicTableUploadRecordService iDynamicTableUploadRecordService) {
        this.iDynamicTableUploadRecordService = iDynamicTableUploadRecordService;
    }
}
