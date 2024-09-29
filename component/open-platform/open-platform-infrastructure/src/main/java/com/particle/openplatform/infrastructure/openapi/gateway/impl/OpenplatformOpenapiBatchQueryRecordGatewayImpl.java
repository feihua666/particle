package com.particle.openplatform.infrastructure.openapi.gateway.impl;

import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecord;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import com.particle.openplatform.infrastructure.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口批量查询记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Component
public class OpenplatformOpenapiBatchQueryRecordGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiBatchQueryRecordId,OpenplatformOpenapiBatchQueryRecord> implements OpenplatformOpenapiBatchQueryRecordGateway {

    private IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService;

    @Override
    public OpenplatformOpenapiBatchQueryRecord getById(OpenplatformOpenapiBatchQueryRecordId openplatformOpenapiBatchQueryRecordId) {
        OpenplatformOpenapiBatchQueryRecordDO byId = iOpenplatformOpenapiBatchQueryRecordService.getById(openplatformOpenapiBatchQueryRecordId.getId());
        OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord = DomainFactory.create(OpenplatformOpenapiBatchQueryRecord.class);
        openplatformOpenapiBatchQueryRecord = OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping.instance. openplatformOpenapiBatchQueryRecordDOToOpenplatformOpenapiBatchQueryRecord(openplatformOpenapiBatchQueryRecord,byId);
        return openplatformOpenapiBatchQueryRecord;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord) {
        OpenplatformOpenapiBatchQueryRecordDO openplatformOpenapiBatchQueryRecordDO = OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping.instance.openplatformOpenapiBatchQueryRecordToOpenplatformOpenapiBatchQueryRecordDO(openplatformOpenapiBatchQueryRecord);
        if (openplatformOpenapiBatchQueryRecordDO.getId() == null) {
            openplatformOpenapiBatchQueryRecordDO.setAddControl(openplatformOpenapiBatchQueryRecord.getAddControl());
            OpenplatformOpenapiBatchQueryRecordDO add = iOpenplatformOpenapiBatchQueryRecordService.add(openplatformOpenapiBatchQueryRecordDO);
            openplatformOpenapiBatchQueryRecord.setId(OpenplatformOpenapiBatchQueryRecordId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiBatchQueryRecordDO.setUpdateControl(openplatformOpenapiBatchQueryRecord.getUpdateControl());
        OpenplatformOpenapiBatchQueryRecordDO update = iOpenplatformOpenapiBatchQueryRecordService.update(openplatformOpenapiBatchQueryRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiBatchQueryRecordId openplatformOpenapiBatchQueryRecordId) {
        return iOpenplatformOpenapiBatchQueryRecordService.deleteById(openplatformOpenapiBatchQueryRecordId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiBatchQueryRecordId openplatformOpenapiBatchQueryRecordId, IdCommand idCommand) {
        return iOpenplatformOpenapiBatchQueryRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiBatchQueryRecordService(IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService) {
        this.iOpenplatformOpenapiBatchQueryRecordService = iOpenplatformOpenapiBatchQueryRecordService;
    }
}
