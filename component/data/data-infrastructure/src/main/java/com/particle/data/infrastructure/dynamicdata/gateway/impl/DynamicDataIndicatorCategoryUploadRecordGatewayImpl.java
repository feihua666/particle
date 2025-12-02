package com.particle.data.infrastructure.dynamicdata.gateway.impl;

import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecordId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryUploadRecordGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryUploadRecordService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;
import com.particle.data.infrastructure.dynamicdata.structmapping.DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据指标分类上传记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Component
public class DynamicDataIndicatorCategoryUploadRecordGatewayImpl extends AbstractBaseGatewayImpl<DynamicDataIndicatorCategoryUploadRecordId,DynamicDataIndicatorCategoryUploadRecord> implements DynamicDataIndicatorCategoryUploadRecordGateway {

    private IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService;

    @Override
    public DynamicDataIndicatorCategoryUploadRecord getById(DynamicDataIndicatorCategoryUploadRecordId dynamicDataIndicatorCategoryUploadRecordId) {
        DynamicDataIndicatorCategoryUploadRecordDO byId = iDynamicDataIndicatorCategoryUploadRecordService.getById(dynamicDataIndicatorCategoryUploadRecordId.getId());
        DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord = DomainFactory.create(DynamicDataIndicatorCategoryUploadRecord.class);
        dynamicDataIndicatorCategoryUploadRecord = DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping.instance. dynamicDataIndicatorCategoryUploadRecordDOToDynamicDataIndicatorCategoryUploadRecord(dynamicDataIndicatorCategoryUploadRecord,byId);
        return dynamicDataIndicatorCategoryUploadRecord;
    }

    @Override
    public boolean doSave(DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord) {
        DynamicDataIndicatorCategoryUploadRecordDO dynamicDataIndicatorCategoryUploadRecordDO = DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping.instance.dynamicDataIndicatorCategoryUploadRecordToDynamicDataIndicatorCategoryUploadRecordDO(dynamicDataIndicatorCategoryUploadRecord);
        if (dynamicDataIndicatorCategoryUploadRecordDO.getId() == null) {
            dynamicDataIndicatorCategoryUploadRecordDO.setAddControl(dynamicDataIndicatorCategoryUploadRecord.getAddControl());
            DynamicDataIndicatorCategoryUploadRecordDO add = iDynamicDataIndicatorCategoryUploadRecordService.add(dynamicDataIndicatorCategoryUploadRecordDO);
            dynamicDataIndicatorCategoryUploadRecord.setId(DynamicDataIndicatorCategoryUploadRecordId.of(add.getId()));
            return add != null;
        }
        dynamicDataIndicatorCategoryUploadRecordDO.setUpdateControl(dynamicDataIndicatorCategoryUploadRecord.getUpdateControl());
        DynamicDataIndicatorCategoryUploadRecordDO update = iDynamicDataIndicatorCategoryUploadRecordService.update(dynamicDataIndicatorCategoryUploadRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicDataIndicatorCategoryUploadRecordId dynamicDataIndicatorCategoryUploadRecordId) {
        return iDynamicDataIndicatorCategoryUploadRecordService.deleteById(dynamicDataIndicatorCategoryUploadRecordId.getId());
    }

    @Override
    public boolean delete(DynamicDataIndicatorCategoryUploadRecordId dynamicDataIndicatorCategoryUploadRecordId, IdCommand idCommand) {
        return iDynamicDataIndicatorCategoryUploadRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setIDynamicDataIndicatorCategoryUploadRecordService(IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService) {
        this.iDynamicDataIndicatorCategoryUploadRecordService = iDynamicDataIndicatorCategoryUploadRecordService;
    }
}
