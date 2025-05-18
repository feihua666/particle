package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业开庭公告当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Component
public class DataCompanyOpenCourtAnnouncementPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyOpenCourtAnnouncementPartyId,DataCompanyOpenCourtAnnouncementParty> implements DataCompanyOpenCourtAnnouncementPartyGateway {

    private IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService;

    @Override
    public DataCompanyOpenCourtAnnouncementParty getById(DataCompanyOpenCourtAnnouncementPartyId dataCompanyOpenCourtAnnouncementPartyId) {
        DataCompanyOpenCourtAnnouncementPartyDO byId = iDataCompanyOpenCourtAnnouncementPartyService.getById(dataCompanyOpenCourtAnnouncementPartyId.getId());
        DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty = DomainFactory.create(DataCompanyOpenCourtAnnouncementParty.class);
        dataCompanyOpenCourtAnnouncementParty = DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping.instance. dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementParty(dataCompanyOpenCourtAnnouncementParty,byId);
        return dataCompanyOpenCourtAnnouncementParty;
    }

    @Override
    public boolean doSave(DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty) {
        DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyDO = DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyToDataCompanyOpenCourtAnnouncementPartyDO(dataCompanyOpenCourtAnnouncementParty);
        if (dataCompanyOpenCourtAnnouncementPartyDO.getId() == null) {
            dataCompanyOpenCourtAnnouncementPartyDO.setAddControl(dataCompanyOpenCourtAnnouncementParty.getAddControl());
            DataCompanyOpenCourtAnnouncementPartyDO add = iDataCompanyOpenCourtAnnouncementPartyService.add(dataCompanyOpenCourtAnnouncementPartyDO);
            dataCompanyOpenCourtAnnouncementParty.setId(DataCompanyOpenCourtAnnouncementPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyOpenCourtAnnouncementPartyDO.setUpdateControl(dataCompanyOpenCourtAnnouncementParty.getUpdateControl());
        DataCompanyOpenCourtAnnouncementPartyDO update = iDataCompanyOpenCourtAnnouncementPartyService.update(dataCompanyOpenCourtAnnouncementPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyOpenCourtAnnouncementPartyId dataCompanyOpenCourtAnnouncementPartyId) {
        return iDataCompanyOpenCourtAnnouncementPartyService.deleteById(dataCompanyOpenCourtAnnouncementPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyOpenCourtAnnouncementPartyId dataCompanyOpenCourtAnnouncementPartyId, IdCommand idCommand) {
        return iDataCompanyOpenCourtAnnouncementPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyOpenCourtAnnouncementPartyService(IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService) {
        this.iDataCompanyOpenCourtAnnouncementPartyService = iDataCompanyOpenCourtAnnouncementPartyService;
    }
}
