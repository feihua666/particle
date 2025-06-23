package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContent;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContentId;
import com.particle.data.domain.company.gateway.DataCompanyDeliveryAnnouncementContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业送达公告内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Component
public class DataCompanyDeliveryAnnouncementContentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyDeliveryAnnouncementContentId,DataCompanyDeliveryAnnouncementContent> implements DataCompanyDeliveryAnnouncementContentGateway {

    private IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService;

    @Override
    public DataCompanyDeliveryAnnouncementContent getById(DataCompanyDeliveryAnnouncementContentId dataCompanyDeliveryAnnouncementContentId) {
        DataCompanyDeliveryAnnouncementContentDO byId = iDataCompanyDeliveryAnnouncementContentService.getById(dataCompanyDeliveryAnnouncementContentId.getId());
        DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent = DomainFactory.create(DataCompanyDeliveryAnnouncementContent.class);
        dataCompanyDeliveryAnnouncementContent = DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping.instance. dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContent(dataCompanyDeliveryAnnouncementContent,byId);
        return dataCompanyDeliveryAnnouncementContent;
    }

    @Override
    public boolean doSave(DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent) {
        DataCompanyDeliveryAnnouncementContentDO dataCompanyDeliveryAnnouncementContentDO = DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping.instance.dataCompanyDeliveryAnnouncementContentToDataCompanyDeliveryAnnouncementContentDO(dataCompanyDeliveryAnnouncementContent);
        if (dataCompanyDeliveryAnnouncementContentDO.getId() == null) {
            dataCompanyDeliveryAnnouncementContentDO.setAddControl(dataCompanyDeliveryAnnouncementContent.getAddControl());
            DataCompanyDeliveryAnnouncementContentDO add = iDataCompanyDeliveryAnnouncementContentService.add(dataCompanyDeliveryAnnouncementContentDO);
            dataCompanyDeliveryAnnouncementContent.setId(DataCompanyDeliveryAnnouncementContentId.of(add.getId()));
            return add != null;
        }
        dataCompanyDeliveryAnnouncementContentDO.setUpdateControl(dataCompanyDeliveryAnnouncementContent.getUpdateControl());
        DataCompanyDeliveryAnnouncementContentDO update = iDataCompanyDeliveryAnnouncementContentService.update(dataCompanyDeliveryAnnouncementContentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyDeliveryAnnouncementContentId dataCompanyDeliveryAnnouncementContentId) {
        return iDataCompanyDeliveryAnnouncementContentService.deleteById(dataCompanyDeliveryAnnouncementContentId.getId());
    }

    @Override
    public boolean delete(DataCompanyDeliveryAnnouncementContentId dataCompanyDeliveryAnnouncementContentId, IdCommand idCommand) {
        return iDataCompanyDeliveryAnnouncementContentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyDeliveryAnnouncementContentService(IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService) {
        this.iDataCompanyDeliveryAnnouncementContentService = iDataCompanyDeliveryAnnouncementContentService;
    }
}
