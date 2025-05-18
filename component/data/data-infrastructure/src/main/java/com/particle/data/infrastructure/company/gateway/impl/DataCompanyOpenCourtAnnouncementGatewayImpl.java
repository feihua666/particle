package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyOpenCourtAnnouncementInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业开庭公告 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Component
public class DataCompanyOpenCourtAnnouncementGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyOpenCourtAnnouncementId,DataCompanyOpenCourtAnnouncement> implements DataCompanyOpenCourtAnnouncementGateway {

    private IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService;

    @Override
    public DataCompanyOpenCourtAnnouncement getById(DataCompanyOpenCourtAnnouncementId dataCompanyOpenCourtAnnouncementId) {
        DataCompanyOpenCourtAnnouncementDO byId = iDataCompanyOpenCourtAnnouncementService.getById(dataCompanyOpenCourtAnnouncementId.getId());
        DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement = DomainFactory.create(DataCompanyOpenCourtAnnouncement.class);
        dataCompanyOpenCourtAnnouncement = DataCompanyOpenCourtAnnouncementInfrastructureStructMapping.instance. dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncement(dataCompanyOpenCourtAnnouncement,byId);
        return dataCompanyOpenCourtAnnouncement;
    }

    @Override
    public boolean doSave(DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement) {
        DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementDO = DataCompanyOpenCourtAnnouncementInfrastructureStructMapping.instance.dataCompanyOpenCourtAnnouncementToDataCompanyOpenCourtAnnouncementDO(dataCompanyOpenCourtAnnouncement);
        if (dataCompanyOpenCourtAnnouncementDO.getId() == null) {
            dataCompanyOpenCourtAnnouncementDO.setAddControl(dataCompanyOpenCourtAnnouncement.getAddControl());
            DataCompanyOpenCourtAnnouncementDO add = iDataCompanyOpenCourtAnnouncementService.add(dataCompanyOpenCourtAnnouncementDO);
            dataCompanyOpenCourtAnnouncement.setId(DataCompanyOpenCourtAnnouncementId.of(add.getId()));
            return add != null;
        }
        dataCompanyOpenCourtAnnouncementDO.setUpdateControl(dataCompanyOpenCourtAnnouncement.getUpdateControl());
        DataCompanyOpenCourtAnnouncementDO update = iDataCompanyOpenCourtAnnouncementService.update(dataCompanyOpenCourtAnnouncementDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyOpenCourtAnnouncementId dataCompanyOpenCourtAnnouncementId) {
        return iDataCompanyOpenCourtAnnouncementService.deleteById(dataCompanyOpenCourtAnnouncementId.getId());
    }

    @Override
    public boolean delete(DataCompanyOpenCourtAnnouncementId dataCompanyOpenCourtAnnouncementId, IdCommand idCommand) {
        return iDataCompanyOpenCourtAnnouncementService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyOpenCourtAnnouncementService(IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService) {
        this.iDataCompanyOpenCourtAnnouncementService = iDataCompanyOpenCourtAnnouncementService;
    }
}
