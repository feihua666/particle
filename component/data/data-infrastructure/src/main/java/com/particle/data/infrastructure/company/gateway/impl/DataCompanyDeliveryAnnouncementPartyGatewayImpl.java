package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementParty;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业送达公告当事人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Component
public class DataCompanyDeliveryAnnouncementPartyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyDeliveryAnnouncementPartyId,DataCompanyDeliveryAnnouncementParty> implements DataCompanyDeliveryAnnouncementPartyGateway {

    private IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService;

    @Override
    public DataCompanyDeliveryAnnouncementParty getById(DataCompanyDeliveryAnnouncementPartyId dataCompanyDeliveryAnnouncementPartyId) {
        DataCompanyDeliveryAnnouncementPartyDO byId = iDataCompanyDeliveryAnnouncementPartyService.getById(dataCompanyDeliveryAnnouncementPartyId.getId());
        DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty = DomainFactory.create(DataCompanyDeliveryAnnouncementParty.class);
        dataCompanyDeliveryAnnouncementParty = DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping.instance. dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementParty(dataCompanyDeliveryAnnouncementParty,byId);
        return dataCompanyDeliveryAnnouncementParty;
    }

    @Override
    public boolean doSave(DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty) {
        DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyDO = DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping.instance.dataCompanyDeliveryAnnouncementPartyToDataCompanyDeliveryAnnouncementPartyDO(dataCompanyDeliveryAnnouncementParty);
        if (dataCompanyDeliveryAnnouncementPartyDO.getId() == null) {
            dataCompanyDeliveryAnnouncementPartyDO.setAddControl(dataCompanyDeliveryAnnouncementParty.getAddControl());
            DataCompanyDeliveryAnnouncementPartyDO add = iDataCompanyDeliveryAnnouncementPartyService.add(dataCompanyDeliveryAnnouncementPartyDO);
            dataCompanyDeliveryAnnouncementParty.setId(DataCompanyDeliveryAnnouncementPartyId.of(add.getId()));
            return add != null;
        }
        dataCompanyDeliveryAnnouncementPartyDO.setUpdateControl(dataCompanyDeliveryAnnouncementParty.getUpdateControl());
        DataCompanyDeliveryAnnouncementPartyDO update = iDataCompanyDeliveryAnnouncementPartyService.update(dataCompanyDeliveryAnnouncementPartyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyDeliveryAnnouncementPartyId dataCompanyDeliveryAnnouncementPartyId) {
        return iDataCompanyDeliveryAnnouncementPartyService.deleteById(dataCompanyDeliveryAnnouncementPartyId.getId());
    }

    @Override
    public boolean delete(DataCompanyDeliveryAnnouncementPartyId dataCompanyDeliveryAnnouncementPartyId, IdCommand idCommand) {
        return iDataCompanyDeliveryAnnouncementPartyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyDeliveryAnnouncementPartyService(IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService) {
        this.iDataCompanyDeliveryAnnouncementPartyService = iDataCompanyDeliveryAnnouncementPartyService;
    }
}
