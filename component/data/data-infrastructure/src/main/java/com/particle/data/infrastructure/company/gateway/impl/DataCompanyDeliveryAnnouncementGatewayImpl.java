package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyDeliveryAnnouncement;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyDeliveryAnnouncementInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业送达公告 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Component
public class DataCompanyDeliveryAnnouncementGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyDeliveryAnnouncementId,DataCompanyDeliveryAnnouncement> implements DataCompanyDeliveryAnnouncementGateway {

    private IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService;

    @Override
    public DataCompanyDeliveryAnnouncement getById(DataCompanyDeliveryAnnouncementId dataCompanyDeliveryAnnouncementId) {
        DataCompanyDeliveryAnnouncementDO byId = iDataCompanyDeliveryAnnouncementService.getById(dataCompanyDeliveryAnnouncementId.getId());
        DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement = DomainFactory.create(DataCompanyDeliveryAnnouncement.class);
        dataCompanyDeliveryAnnouncement = DataCompanyDeliveryAnnouncementInfrastructureStructMapping.instance. dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncement(dataCompanyDeliveryAnnouncement,byId);
        return dataCompanyDeliveryAnnouncement;
    }

    @Override
    public boolean doSave(DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement) {
        DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementDO = DataCompanyDeliveryAnnouncementInfrastructureStructMapping.instance.dataCompanyDeliveryAnnouncementToDataCompanyDeliveryAnnouncementDO(dataCompanyDeliveryAnnouncement);
        if (dataCompanyDeliveryAnnouncementDO.getId() == null) {
            dataCompanyDeliveryAnnouncementDO.setAddControl(dataCompanyDeliveryAnnouncement.getAddControl());
            DataCompanyDeliveryAnnouncementDO add = iDataCompanyDeliveryAnnouncementService.add(dataCompanyDeliveryAnnouncementDO);
            dataCompanyDeliveryAnnouncement.setId(DataCompanyDeliveryAnnouncementId.of(add.getId()));
            return add != null;
        }
        dataCompanyDeliveryAnnouncementDO.setUpdateControl(dataCompanyDeliveryAnnouncement.getUpdateControl());
        DataCompanyDeliveryAnnouncementDO update = iDataCompanyDeliveryAnnouncementService.update(dataCompanyDeliveryAnnouncementDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyDeliveryAnnouncementId dataCompanyDeliveryAnnouncementId) {
        return iDataCompanyDeliveryAnnouncementService.deleteById(dataCompanyDeliveryAnnouncementId.getId());
    }

    @Override
    public boolean delete(DataCompanyDeliveryAnnouncementId dataCompanyDeliveryAnnouncementId, IdCommand idCommand) {
        return iDataCompanyDeliveryAnnouncementService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyDeliveryAnnouncementService(IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService) {
        this.iDataCompanyDeliveryAnnouncementService = iDataCompanyDeliveryAnnouncementService;
    }
}
