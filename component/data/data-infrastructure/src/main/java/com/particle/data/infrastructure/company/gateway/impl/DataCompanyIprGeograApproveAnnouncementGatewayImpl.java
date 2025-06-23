package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncement;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncementId;
import com.particle.data.domain.company.gateway.DataCompanyIprGeograApproveAnnouncementGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权地理标识核准公告 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Component
public class DataCompanyIprGeograApproveAnnouncementGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprGeograApproveAnnouncementId,DataCompanyIprGeograApproveAnnouncement> implements DataCompanyIprGeograApproveAnnouncementGateway {

    private IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService;

    @Override
    public DataCompanyIprGeograApproveAnnouncement getById(DataCompanyIprGeograApproveAnnouncementId dataCompanyIprGeograApproveAnnouncementId) {
        DataCompanyIprGeograApproveAnnouncementDO byId = iDataCompanyIprGeograApproveAnnouncementService.getById(dataCompanyIprGeograApproveAnnouncementId.getId());
        DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement = DomainFactory.create(DataCompanyIprGeograApproveAnnouncement.class);
        dataCompanyIprGeograApproveAnnouncement = DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping.instance. dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncement(dataCompanyIprGeograApproveAnnouncement,byId);
        return dataCompanyIprGeograApproveAnnouncement;
    }

    @Override
    public boolean doSave(DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement) {
        DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementDO = DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping.instance.dataCompanyIprGeograApproveAnnouncementToDataCompanyIprGeograApproveAnnouncementDO(dataCompanyIprGeograApproveAnnouncement);
        if (dataCompanyIprGeograApproveAnnouncementDO.getId() == null) {
            dataCompanyIprGeograApproveAnnouncementDO.setAddControl(dataCompanyIprGeograApproveAnnouncement.getAddControl());
            DataCompanyIprGeograApproveAnnouncementDO add = iDataCompanyIprGeograApproveAnnouncementService.add(dataCompanyIprGeograApproveAnnouncementDO);
            dataCompanyIprGeograApproveAnnouncement.setId(DataCompanyIprGeograApproveAnnouncementId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprGeograApproveAnnouncementDO.setUpdateControl(dataCompanyIprGeograApproveAnnouncement.getUpdateControl());
        DataCompanyIprGeograApproveAnnouncementDO update = iDataCompanyIprGeograApproveAnnouncementService.update(dataCompanyIprGeograApproveAnnouncementDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprGeograApproveAnnouncementId dataCompanyIprGeograApproveAnnouncementId) {
        return iDataCompanyIprGeograApproveAnnouncementService.deleteById(dataCompanyIprGeograApproveAnnouncementId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprGeograApproveAnnouncementId dataCompanyIprGeograApproveAnnouncementId, IdCommand idCommand) {
        return iDataCompanyIprGeograApproveAnnouncementService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprGeograApproveAnnouncementService(IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService) {
        this.iDataCompanyIprGeograApproveAnnouncementService = iDataCompanyIprGeograApproveAnnouncementService;
    }
}
