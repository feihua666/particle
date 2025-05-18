package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyJudgmentDocumentContent;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContentId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyJudgmentDocumentContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业裁判文书内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Component
public class DataCompanyJudgmentDocumentContentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyJudgmentDocumentContentId,DataCompanyJudgmentDocumentContent> implements DataCompanyJudgmentDocumentContentGateway {

    private IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService;

    @Override
    public DataCompanyJudgmentDocumentContent getById(DataCompanyJudgmentDocumentContentId dataCompanyJudgmentDocumentContentId) {
        DataCompanyJudgmentDocumentContentDO byId = iDataCompanyJudgmentDocumentContentService.getById(dataCompanyJudgmentDocumentContentId.getId());
        DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent = DomainFactory.create(DataCompanyJudgmentDocumentContent.class);
        dataCompanyJudgmentDocumentContent = DataCompanyJudgmentDocumentContentInfrastructureStructMapping.instance. dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContent(dataCompanyJudgmentDocumentContent,byId);
        return dataCompanyJudgmentDocumentContent;
    }

    @Override
    public boolean doSave(DataCompanyJudgmentDocumentContent dataCompanyJudgmentDocumentContent) {
        DataCompanyJudgmentDocumentContentDO dataCompanyJudgmentDocumentContentDO = DataCompanyJudgmentDocumentContentInfrastructureStructMapping.instance.dataCompanyJudgmentDocumentContentToDataCompanyJudgmentDocumentContentDO(dataCompanyJudgmentDocumentContent);
        if (dataCompanyJudgmentDocumentContentDO.getId() == null) {
            dataCompanyJudgmentDocumentContentDO.setAddControl(dataCompanyJudgmentDocumentContent.getAddControl());
            DataCompanyJudgmentDocumentContentDO add = iDataCompanyJudgmentDocumentContentService.add(dataCompanyJudgmentDocumentContentDO);
            dataCompanyJudgmentDocumentContent.setId(DataCompanyJudgmentDocumentContentId.of(add.getId()));
            return add != null;
        }
        dataCompanyJudgmentDocumentContentDO.setUpdateControl(dataCompanyJudgmentDocumentContent.getUpdateControl());
        DataCompanyJudgmentDocumentContentDO update = iDataCompanyJudgmentDocumentContentService.update(dataCompanyJudgmentDocumentContentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyJudgmentDocumentContentId dataCompanyJudgmentDocumentContentId) {
        return iDataCompanyJudgmentDocumentContentService.deleteById(dataCompanyJudgmentDocumentContentId.getId());
    }

    @Override
    public boolean delete(DataCompanyJudgmentDocumentContentId dataCompanyJudgmentDocumentContentId, IdCommand idCommand) {
        return iDataCompanyJudgmentDocumentContentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyJudgmentDocumentContentService(IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService) {
        this.iDataCompanyJudgmentDocumentContentService = iDataCompanyJudgmentDocumentContentService;
    }
}
