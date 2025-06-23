package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademarkParty;
import com.particle.data.domain.company.DataCompanyIprTrademarkPartyId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Component
public class DataCompanyIprTrademarkPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkPartyId,DataCompanyIprTrademarkParty> implements DataCompanyIprTrademarkPartyGateway {

    private IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService;

    @Override
    public DataCompanyIprTrademarkParty getById(DataCompanyIprTrademarkPartyId dataCompanyIprTrademarkPartyId) {
        DataCompanyIprTrademarkPartyDO byId = iDataCompanyIprTrademarkPartyService.getById(dataCompanyIprTrademarkPartyId.getId());
        DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty = DomainFactory.create(DataCompanyIprTrademarkParty.class);
        dataCompanyIprTrademarkParty = DataCompanyIprTrademarkPartyInfrastructureStructMapping.instance. dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkParty(dataCompanyIprTrademarkParty,byId);
        return dataCompanyIprTrademarkParty;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademarkParty dataCompanyIprTrademarkParty) {
        DataCompanyIprTrademarkPartyDO dataCompanyIprTrademarkPartyDO = DataCompanyIprTrademarkPartyInfrastructureStructMapping.instance.dataCompanyIprTrademarkPartyToDataCompanyIprTrademarkPartyDO(dataCompanyIprTrademarkParty);
        if (dataCompanyIprTrademarkPartyDO.getId() == null) {
            dataCompanyIprTrademarkPartyDO.setAddControl(dataCompanyIprTrademarkParty.getAddControl());
            DataCompanyIprTrademarkPartyDO add = iDataCompanyIprTrademarkPartyService.add(dataCompanyIprTrademarkPartyDO);
            dataCompanyIprTrademarkParty.setId(DataCompanyIprTrademarkPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkPartyDO.setUpdateControl(dataCompanyIprTrademarkParty.getUpdateControl());
        DataCompanyIprTrademarkPartyDO update = iDataCompanyIprTrademarkPartyService.update(dataCompanyIprTrademarkPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkPartyId dataCompanyIprTrademarkPartyId) {
        return iDataCompanyIprTrademarkPartyService.deleteById(dataCompanyIprTrademarkPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkPartyId dataCompanyIprTrademarkPartyId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkPartyService(IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService) {
        this.iDataCompanyIprTrademarkPartyService = iDataCompanyIprTrademarkPartyService;
    }
}
