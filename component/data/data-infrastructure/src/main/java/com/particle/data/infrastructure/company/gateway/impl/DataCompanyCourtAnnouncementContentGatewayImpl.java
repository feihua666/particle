package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyCourtAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyCourtAnnouncementContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业法院公告内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Component
public class DataCompanyCourtAnnouncementContentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyCourtAnnouncementContentId,DataCompanyCourtAnnouncementContent> implements DataCompanyCourtAnnouncementContentGateway {

    private IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService;

    @Override
    public DataCompanyCourtAnnouncementContent getById(DataCompanyCourtAnnouncementContentId dataCompanyCourtAnnouncementContentId) {
        DataCompanyCourtAnnouncementContentDO byId = iDataCompanyCourtAnnouncementContentService.getById(dataCompanyCourtAnnouncementContentId.getId());
        DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent = DomainFactory.create(DataCompanyCourtAnnouncementContent.class);
        dataCompanyCourtAnnouncementContent = DataCompanyCourtAnnouncementContentInfrastructureStructMapping.instance. dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContent(dataCompanyCourtAnnouncementContent,byId);
        return dataCompanyCourtAnnouncementContent;
    }

    @Override
    public boolean doSave(DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent) {
        DataCompanyCourtAnnouncementContentDO dataCompanyCourtAnnouncementContentDO = DataCompanyCourtAnnouncementContentInfrastructureStructMapping.instance.dataCompanyCourtAnnouncementContentToDataCompanyCourtAnnouncementContentDO(dataCompanyCourtAnnouncementContent);
        if (dataCompanyCourtAnnouncementContentDO.getId() == null) {
            dataCompanyCourtAnnouncementContentDO.setAddControl(dataCompanyCourtAnnouncementContent.getAddControl());
            DataCompanyCourtAnnouncementContentDO add = iDataCompanyCourtAnnouncementContentService.add(dataCompanyCourtAnnouncementContentDO);
            dataCompanyCourtAnnouncementContent.setId(DataCompanyCourtAnnouncementContentId.of(add.getId()));
            return add != null;
        }
        dataCompanyCourtAnnouncementContentDO.setUpdateControl(dataCompanyCourtAnnouncementContent.getUpdateControl());
        DataCompanyCourtAnnouncementContentDO update = iDataCompanyCourtAnnouncementContentService.update(dataCompanyCourtAnnouncementContentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyCourtAnnouncementContentId dataCompanyCourtAnnouncementContentId) {
        return iDataCompanyCourtAnnouncementContentService.deleteById(dataCompanyCourtAnnouncementContentId.getId());
    }

    @Override
    public boolean delete(DataCompanyCourtAnnouncementContentId dataCompanyCourtAnnouncementContentId, IdCommand idCommand) {
        return iDataCompanyCourtAnnouncementContentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyCourtAnnouncementContentService(IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService) {
        this.iDataCompanyCourtAnnouncementContentService = iDataCompanyCourtAnnouncementContentService;
    }
}
