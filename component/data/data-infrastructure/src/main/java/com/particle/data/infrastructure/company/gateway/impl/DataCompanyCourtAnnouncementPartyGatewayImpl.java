package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyCourtAnnouncementPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业法院公告当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Component
public class DataCompanyCourtAnnouncementPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyCourtAnnouncementPartyId,DataCompanyCourtAnnouncementParty> implements DataCompanyCourtAnnouncementPartyGateway {

    private IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService;

    @Override
    public DataCompanyCourtAnnouncementParty getById(DataCompanyCourtAnnouncementPartyId dataCompanyCourtAnnouncementPartyId) {
        DataCompanyCourtAnnouncementPartyDO byId = iDataCompanyCourtAnnouncementPartyService.getById(dataCompanyCourtAnnouncementPartyId.getId());
        DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty = DomainFactory.create(DataCompanyCourtAnnouncementParty.class);
        dataCompanyCourtAnnouncementParty = DataCompanyCourtAnnouncementPartyInfrastructureStructMapping.instance. dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementParty(dataCompanyCourtAnnouncementParty,byId);
        return dataCompanyCourtAnnouncementParty;
    }

    @Override
    public boolean doSave(DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty) {
        DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyDO = DataCompanyCourtAnnouncementPartyInfrastructureStructMapping.instance.dataCompanyCourtAnnouncementPartyToDataCompanyCourtAnnouncementPartyDO(dataCompanyCourtAnnouncementParty);
        if (dataCompanyCourtAnnouncementPartyDO.getId() == null) {
            dataCompanyCourtAnnouncementPartyDO.setAddControl(dataCompanyCourtAnnouncementParty.getAddControl());
            DataCompanyCourtAnnouncementPartyDO add = iDataCompanyCourtAnnouncementPartyService.add(dataCompanyCourtAnnouncementPartyDO);
            dataCompanyCourtAnnouncementParty.setId(DataCompanyCourtAnnouncementPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyCourtAnnouncementPartyDO.setUpdateControl(dataCompanyCourtAnnouncementParty.getUpdateControl());
        DataCompanyCourtAnnouncementPartyDO update = iDataCompanyCourtAnnouncementPartyService.update(dataCompanyCourtAnnouncementPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyCourtAnnouncementPartyId dataCompanyCourtAnnouncementPartyId) {
        return iDataCompanyCourtAnnouncementPartyService.deleteById(dataCompanyCourtAnnouncementPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyCourtAnnouncementPartyId dataCompanyCourtAnnouncementPartyId, IdCommand idCommand) {
        return iDataCompanyCourtAnnouncementPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyCourtAnnouncementPartyService(IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService) {
        this.iDataCompanyCourtAnnouncementPartyService = iDataCompanyCourtAnnouncementPartyService;
    }
}
