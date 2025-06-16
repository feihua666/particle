package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentParty;
import com.particle.data.domain.company.DataCompanyIprPatentPartyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Component
public class DataCompanyIprPatentPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentPartyId,DataCompanyIprPatentParty> implements DataCompanyIprPatentPartyGateway {

    private IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService;

    @Override
    public DataCompanyIprPatentParty getById(DataCompanyIprPatentPartyId dataCompanyIprPatentPartyId) {
        DataCompanyIprPatentPartyDO byId = iDataCompanyIprPatentPartyService.getById(dataCompanyIprPatentPartyId.getId());
        DataCompanyIprPatentParty dataCompanyIprPatentParty = DomainFactory.create(DataCompanyIprPatentParty.class);
        dataCompanyIprPatentParty = DataCompanyIprPatentPartyInfrastructureStructMapping.instance. dataCompanyIprPatentPartyDOToDataCompanyIprPatentParty(dataCompanyIprPatentParty,byId);
        return dataCompanyIprPatentParty;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentParty dataCompanyIprPatentParty) {
        DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyDO = DataCompanyIprPatentPartyInfrastructureStructMapping.instance.dataCompanyIprPatentPartyToDataCompanyIprPatentPartyDO(dataCompanyIprPatentParty);
        if (dataCompanyIprPatentPartyDO.getId() == null) {
            dataCompanyIprPatentPartyDO.setAddControl(dataCompanyIprPatentParty.getAddControl());
            DataCompanyIprPatentPartyDO add = iDataCompanyIprPatentPartyService.add(dataCompanyIprPatentPartyDO);
            dataCompanyIprPatentParty.setId(DataCompanyIprPatentPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentPartyDO.setUpdateControl(dataCompanyIprPatentParty.getUpdateControl());
        DataCompanyIprPatentPartyDO update = iDataCompanyIprPatentPartyService.update(dataCompanyIprPatentPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentPartyId dataCompanyIprPatentPartyId) {
        return iDataCompanyIprPatentPartyService.deleteById(dataCompanyIprPatentPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentPartyId dataCompanyIprPatentPartyId, IdCommand idCommand) {
        return iDataCompanyIprPatentPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentPartyService(IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService) {
        this.iDataCompanyIprPatentPartyService = iDataCompanyIprPatentPartyService;
    }
}
