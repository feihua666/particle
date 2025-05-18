package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyRestrictHighConsumeParty;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumePartyId;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumePartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyRestrictHighConsumePartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业限制高消费当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
public class DataCompanyRestrictHighConsumePartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyRestrictHighConsumePartyId,DataCompanyRestrictHighConsumeParty> implements DataCompanyRestrictHighConsumePartyGateway {

    private IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService;

    @Override
    public DataCompanyRestrictHighConsumeParty getById(DataCompanyRestrictHighConsumePartyId dataCompanyRestrictHighConsumePartyId) {
        DataCompanyRestrictHighConsumePartyDO byId = iDataCompanyRestrictHighConsumePartyService.getById(dataCompanyRestrictHighConsumePartyId.getId());
        DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty = DomainFactory.create(DataCompanyRestrictHighConsumeParty.class);
        dataCompanyRestrictHighConsumeParty = DataCompanyRestrictHighConsumePartyInfrastructureStructMapping.instance. dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumeParty(dataCompanyRestrictHighConsumeParty,byId);
        return dataCompanyRestrictHighConsumeParty;
    }

    @Override
    public boolean doSave(DataCompanyRestrictHighConsumeParty dataCompanyRestrictHighConsumeParty) {
        DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyDO = DataCompanyRestrictHighConsumePartyInfrastructureStructMapping.instance.dataCompanyRestrictHighConsumePartyToDataCompanyRestrictHighConsumePartyDO(dataCompanyRestrictHighConsumeParty);
        if (dataCompanyRestrictHighConsumePartyDO.getId() == null) {
            dataCompanyRestrictHighConsumePartyDO.setAddControl(dataCompanyRestrictHighConsumeParty.getAddControl());
            DataCompanyRestrictHighConsumePartyDO add = iDataCompanyRestrictHighConsumePartyService.add(dataCompanyRestrictHighConsumePartyDO);
            dataCompanyRestrictHighConsumeParty.setId(DataCompanyRestrictHighConsumePartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyRestrictHighConsumePartyDO.setUpdateControl(dataCompanyRestrictHighConsumeParty.getUpdateControl());
        DataCompanyRestrictHighConsumePartyDO update = iDataCompanyRestrictHighConsumePartyService.update(dataCompanyRestrictHighConsumePartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyRestrictHighConsumePartyId dataCompanyRestrictHighConsumePartyId) {
        return iDataCompanyRestrictHighConsumePartyService.deleteById(dataCompanyRestrictHighConsumePartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyRestrictHighConsumePartyId dataCompanyRestrictHighConsumePartyId, IdCommand idCommand) {
        return iDataCompanyRestrictHighConsumePartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyRestrictHighConsumePartyService(IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService) {
        this.iDataCompanyRestrictHighConsumePartyService = iDataCompanyRestrictHighConsumePartyService;
    }
}
