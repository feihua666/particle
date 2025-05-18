package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyCaseFilingParty;
import com.particle.data.domain.company.DataCompanyCaseFilingPartyId;
import com.particle.data.domain.company.gateway.DataCompanyCaseFilingPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyCaseFilingPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业立案信息当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Component
public class DataCompanyCaseFilingPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyCaseFilingPartyId,DataCompanyCaseFilingParty> implements DataCompanyCaseFilingPartyGateway {

    private IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService;

    @Override
    public DataCompanyCaseFilingParty getById(DataCompanyCaseFilingPartyId dataCompanyCaseFilingPartyId) {
        DataCompanyCaseFilingPartyDO byId = iDataCompanyCaseFilingPartyService.getById(dataCompanyCaseFilingPartyId.getId());
        DataCompanyCaseFilingParty dataCompanyCaseFilingParty = DomainFactory.create(DataCompanyCaseFilingParty.class);
        dataCompanyCaseFilingParty = DataCompanyCaseFilingPartyInfrastructureStructMapping.instance. dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingParty(dataCompanyCaseFilingParty,byId);
        return dataCompanyCaseFilingParty;
    }

    @Override
    public boolean doSave(DataCompanyCaseFilingParty dataCompanyCaseFilingParty) {
        DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyDO = DataCompanyCaseFilingPartyInfrastructureStructMapping.instance.dataCompanyCaseFilingPartyToDataCompanyCaseFilingPartyDO(dataCompanyCaseFilingParty);
        if (dataCompanyCaseFilingPartyDO.getId() == null) {
            dataCompanyCaseFilingPartyDO.setAddControl(dataCompanyCaseFilingParty.getAddControl());
            DataCompanyCaseFilingPartyDO add = iDataCompanyCaseFilingPartyService.add(dataCompanyCaseFilingPartyDO);
            dataCompanyCaseFilingParty.setId(DataCompanyCaseFilingPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyCaseFilingPartyDO.setUpdateControl(dataCompanyCaseFilingParty.getUpdateControl());
        DataCompanyCaseFilingPartyDO update = iDataCompanyCaseFilingPartyService.update(dataCompanyCaseFilingPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyCaseFilingPartyId dataCompanyCaseFilingPartyId) {
        return iDataCompanyCaseFilingPartyService.deleteById(dataCompanyCaseFilingPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyCaseFilingPartyId dataCompanyCaseFilingPartyId, IdCommand idCommand) {
        return iDataCompanyCaseFilingPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyCaseFilingPartyService(IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService) {
        this.iDataCompanyCaseFilingPartyService = iDataCompanyCaseFilingPartyService;
    }
}
