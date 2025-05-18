package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyCourtAnnouncementInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业法院公告 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Component
public class DataCompanyCourtAnnouncementGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyCourtAnnouncementId,DataCompanyCourtAnnouncement> implements DataCompanyCourtAnnouncementGateway {

    private IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService;

    @Override
    public DataCompanyCourtAnnouncement getById(DataCompanyCourtAnnouncementId dataCompanyCourtAnnouncementId) {
        DataCompanyCourtAnnouncementDO byId = iDataCompanyCourtAnnouncementService.getById(dataCompanyCourtAnnouncementId.getId());
        DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement = DomainFactory.create(DataCompanyCourtAnnouncement.class);
        dataCompanyCourtAnnouncement = DataCompanyCourtAnnouncementInfrastructureStructMapping.instance. dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncement(dataCompanyCourtAnnouncement,byId);
        return dataCompanyCourtAnnouncement;
    }

    @Override
    public boolean doSave(DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement) {
        DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementDO = DataCompanyCourtAnnouncementInfrastructureStructMapping.instance.dataCompanyCourtAnnouncementToDataCompanyCourtAnnouncementDO(dataCompanyCourtAnnouncement);
        if (dataCompanyCourtAnnouncementDO.getId() == null) {
            dataCompanyCourtAnnouncementDO.setAddControl(dataCompanyCourtAnnouncement.getAddControl());
            DataCompanyCourtAnnouncementDO add = iDataCompanyCourtAnnouncementService.add(dataCompanyCourtAnnouncementDO);
            dataCompanyCourtAnnouncement.setId(DataCompanyCourtAnnouncementId.of(add.getId()));
            return add != null;
        }
        dataCompanyCourtAnnouncementDO.setUpdateControl(dataCompanyCourtAnnouncement.getUpdateControl());
        DataCompanyCourtAnnouncementDO update = iDataCompanyCourtAnnouncementService.update(dataCompanyCourtAnnouncementDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyCourtAnnouncementId dataCompanyCourtAnnouncementId) {
        return iDataCompanyCourtAnnouncementService.deleteById(dataCompanyCourtAnnouncementId.getId());
    }

    @Override
    public boolean delete(DataCompanyCourtAnnouncementId dataCompanyCourtAnnouncementId, IdCommand idCommand) {
        return iDataCompanyCourtAnnouncementService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyCourtAnnouncementService(IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService) {
        this.iDataCompanyCourtAnnouncementService = iDataCompanyCourtAnnouncementService;
    }
}
