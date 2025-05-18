package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentContent;
import com.particle.data.domain.company.DataCompanyIprPatentContentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Component
public class DataCompanyIprPatentContentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentContentId,DataCompanyIprPatentContent> implements DataCompanyIprPatentContentGateway {

    private IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService;

    @Override
    public DataCompanyIprPatentContent getById(DataCompanyIprPatentContentId dataCompanyIprPatentContentId) {
        DataCompanyIprPatentContentDO byId = iDataCompanyIprPatentContentService.getById(dataCompanyIprPatentContentId.getId());
        DataCompanyIprPatentContent dataCompanyIprPatentContent = DomainFactory.create(DataCompanyIprPatentContent.class);
        dataCompanyIprPatentContent = DataCompanyIprPatentContentInfrastructureStructMapping.instance. dataCompanyIprPatentContentDOToDataCompanyIprPatentContent(dataCompanyIprPatentContent,byId);
        return dataCompanyIprPatentContent;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentContent dataCompanyIprPatentContent) {
        DataCompanyIprPatentContentDO dataCompanyIprPatentContentDO = DataCompanyIprPatentContentInfrastructureStructMapping.instance.dataCompanyIprPatentContentToDataCompanyIprPatentContentDO(dataCompanyIprPatentContent);
        if (dataCompanyIprPatentContentDO.getId() == null) {
            dataCompanyIprPatentContentDO.setAddControl(dataCompanyIprPatentContent.getAddControl());
            DataCompanyIprPatentContentDO add = iDataCompanyIprPatentContentService.add(dataCompanyIprPatentContentDO);
            dataCompanyIprPatentContent.setId(DataCompanyIprPatentContentId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentContentDO.setUpdateControl(dataCompanyIprPatentContent.getUpdateControl());
        DataCompanyIprPatentContentDO update = iDataCompanyIprPatentContentService.update(dataCompanyIprPatentContentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentContentId dataCompanyIprPatentContentId) {
        return iDataCompanyIprPatentContentService.deleteById(dataCompanyIprPatentContentId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentContentId dataCompanyIprPatentContentId, IdCommand idCommand) {
        return iDataCompanyIprPatentContentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentContentService(IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService) {
        this.iDataCompanyIprPatentContentService = iDataCompanyIprPatentContentService;
    }
}
