package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyOpenCourtAnnouncementContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业开庭公告内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Component
public class DataCompanyOpenCourtAnnouncementContentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyOpenCourtAnnouncementContentId,DataCompanyOpenCourtAnnouncementContent> implements DataCompanyOpenCourtAnnouncementContentGateway {

    private IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService;

    @Override
    public DataCompanyOpenCourtAnnouncementContent getById(DataCompanyOpenCourtAnnouncementContentId dataCompanyOpenCourtAnnouncementContentId) {
        DataCompanyOpenCourtAnnouncementContentDO byId = iDataCompanyOpenCourtAnnouncementContentService.getById(dataCompanyOpenCourtAnnouncementContentId.getId());
        DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent = DomainFactory.create(DataCompanyOpenCourtAnnouncementContent.class);
        dataCompanyOpenCourtAnnouncementContent = DataCompanyOpenCourtAnnouncementContentInfrastructureStructMapping.instance. dataCompanyOpenCourtAnnouncementContentDOToDataCompanyOpenCourtAnnouncementContent(dataCompanyOpenCourtAnnouncementContent,byId);
        return dataCompanyOpenCourtAnnouncementContent;
    }

    @Override
    public boolean doSave(DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent) {
        DataCompanyOpenCourtAnnouncementContentDO dataCompanyOpenCourtAnnouncementContentDO = DataCompanyOpenCourtAnnouncementContentInfrastructureStructMapping.instance.dataCompanyOpenCourtAnnouncementContentToDataCompanyOpenCourtAnnouncementContentDO(dataCompanyOpenCourtAnnouncementContent);
        if (dataCompanyOpenCourtAnnouncementContentDO.getId() == null) {
            dataCompanyOpenCourtAnnouncementContentDO.setAddControl(dataCompanyOpenCourtAnnouncementContent.getAddControl());
            DataCompanyOpenCourtAnnouncementContentDO add = iDataCompanyOpenCourtAnnouncementContentService.add(dataCompanyOpenCourtAnnouncementContentDO);
            dataCompanyOpenCourtAnnouncementContent.setId(DataCompanyOpenCourtAnnouncementContentId.of(add.getId()));
            return add != null;
        }
        dataCompanyOpenCourtAnnouncementContentDO.setUpdateControl(dataCompanyOpenCourtAnnouncementContent.getUpdateControl());
        DataCompanyOpenCourtAnnouncementContentDO update = iDataCompanyOpenCourtAnnouncementContentService.update(dataCompanyOpenCourtAnnouncementContentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyOpenCourtAnnouncementContentId dataCompanyOpenCourtAnnouncementContentId) {
        return iDataCompanyOpenCourtAnnouncementContentService.deleteById(dataCompanyOpenCourtAnnouncementContentId.getId());
    }

    @Override
    public boolean delete(DataCompanyOpenCourtAnnouncementContentId dataCompanyOpenCourtAnnouncementContentId, IdCommand idCommand) {
        return iDataCompanyOpenCourtAnnouncementContentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyOpenCourtAnnouncementContentService(IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService) {
        this.iDataCompanyOpenCourtAnnouncementContentService = iDataCompanyOpenCourtAnnouncementContentService;
    }
}
