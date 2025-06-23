package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPlantVarietyChange;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyChangeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPlantVarietyChangeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Component
public class DataCompanyIprPlantVarietyChangeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPlantVarietyChangeId,DataCompanyIprPlantVarietyChange> implements DataCompanyIprPlantVarietyChangeGateway {

    private IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService;

    @Override
    public DataCompanyIprPlantVarietyChange getById(DataCompanyIprPlantVarietyChangeId dataCompanyIprPlantVarietyChangeId) {
        DataCompanyIprPlantVarietyChangeDO byId = iDataCompanyIprPlantVarietyChangeService.getById(dataCompanyIprPlantVarietyChangeId.getId());
        DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange = DomainFactory.create(DataCompanyIprPlantVarietyChange.class);
        dataCompanyIprPlantVarietyChange = DataCompanyIprPlantVarietyChangeInfrastructureStructMapping.instance. dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChange(dataCompanyIprPlantVarietyChange,byId);
        return dataCompanyIprPlantVarietyChange;
    }

    @Override
    public boolean doSave(DataCompanyIprPlantVarietyChange dataCompanyIprPlantVarietyChange) {
        DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeDO = DataCompanyIprPlantVarietyChangeInfrastructureStructMapping.instance.dataCompanyIprPlantVarietyChangeToDataCompanyIprPlantVarietyChangeDO(dataCompanyIprPlantVarietyChange);
        if (dataCompanyIprPlantVarietyChangeDO.getId() == null) {
            dataCompanyIprPlantVarietyChangeDO.setAddControl(dataCompanyIprPlantVarietyChange.getAddControl());
            DataCompanyIprPlantVarietyChangeDO add = iDataCompanyIprPlantVarietyChangeService.add(dataCompanyIprPlantVarietyChangeDO);
            dataCompanyIprPlantVarietyChange.setId(DataCompanyIprPlantVarietyChangeId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPlantVarietyChangeDO.setUpdateControl(dataCompanyIprPlantVarietyChange.getUpdateControl());
        DataCompanyIprPlantVarietyChangeDO update = iDataCompanyIprPlantVarietyChangeService.update(dataCompanyIprPlantVarietyChangeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPlantVarietyChangeId dataCompanyIprPlantVarietyChangeId) {
        return iDataCompanyIprPlantVarietyChangeService.deleteById(dataCompanyIprPlantVarietyChangeId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPlantVarietyChangeId dataCompanyIprPlantVarietyChangeId, IdCommand idCommand) {
        return iDataCompanyIprPlantVarietyChangeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPlantVarietyChangeService(IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService) {
        this.iDataCompanyIprPlantVarietyChangeService = iDataCompanyIprPlantVarietyChangeService;
    }
}
