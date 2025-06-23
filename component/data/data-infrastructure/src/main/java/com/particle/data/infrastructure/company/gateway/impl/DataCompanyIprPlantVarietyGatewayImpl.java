package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPlantVariety;
import com.particle.data.domain.company.DataCompanyIprPlantVarietyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPlantVarietyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPlantVarietyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权植物新品种 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Component
public class DataCompanyIprPlantVarietyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPlantVarietyId,DataCompanyIprPlantVariety> implements DataCompanyIprPlantVarietyGateway {

    private IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService;

    @Override
    public DataCompanyIprPlantVariety getById(DataCompanyIprPlantVarietyId dataCompanyIprPlantVarietyId) {
        DataCompanyIprPlantVarietyDO byId = iDataCompanyIprPlantVarietyService.getById(dataCompanyIprPlantVarietyId.getId());
        DataCompanyIprPlantVariety dataCompanyIprPlantVariety = DomainFactory.create(DataCompanyIprPlantVariety.class);
        dataCompanyIprPlantVariety = DataCompanyIprPlantVarietyInfrastructureStructMapping.instance. dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVariety(dataCompanyIprPlantVariety,byId);
        return dataCompanyIprPlantVariety;
    }

    @Override
    public boolean doSave(DataCompanyIprPlantVariety dataCompanyIprPlantVariety) {
        DataCompanyIprPlantVarietyDO dataCompanyIprPlantVarietyDO = DataCompanyIprPlantVarietyInfrastructureStructMapping.instance.dataCompanyIprPlantVarietyToDataCompanyIprPlantVarietyDO(dataCompanyIprPlantVariety);
        if (dataCompanyIprPlantVarietyDO.getId() == null) {
            dataCompanyIprPlantVarietyDO.setAddControl(dataCompanyIprPlantVariety.getAddControl());
            DataCompanyIprPlantVarietyDO add = iDataCompanyIprPlantVarietyService.add(dataCompanyIprPlantVarietyDO);
            dataCompanyIprPlantVariety.setId(DataCompanyIprPlantVarietyId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPlantVarietyDO.setUpdateControl(dataCompanyIprPlantVariety.getUpdateControl());
        DataCompanyIprPlantVarietyDO update = iDataCompanyIprPlantVarietyService.update(dataCompanyIprPlantVarietyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPlantVarietyId dataCompanyIprPlantVarietyId) {
        return iDataCompanyIprPlantVarietyService.deleteById(dataCompanyIprPlantVarietyId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPlantVarietyId dataCompanyIprPlantVarietyId, IdCommand idCommand) {
        return iDataCompanyIprPlantVarietyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPlantVarietyService(IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService) {
        this.iDataCompanyIprPlantVarietyService = iDataCompanyIprPlantVarietyService;
    }
}
