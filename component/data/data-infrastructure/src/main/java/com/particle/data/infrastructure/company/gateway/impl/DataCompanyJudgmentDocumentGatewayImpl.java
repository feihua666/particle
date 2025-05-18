package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyJudgmentDocument;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyJudgmentDocumentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业裁判文书 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Component
public class DataCompanyJudgmentDocumentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyJudgmentDocumentId,DataCompanyJudgmentDocument> implements DataCompanyJudgmentDocumentGateway {

    private IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService;

    @Override
    public DataCompanyJudgmentDocument getById(DataCompanyJudgmentDocumentId dataCompanyJudgmentDocumentId) {
        DataCompanyJudgmentDocumentDO byId = iDataCompanyJudgmentDocumentService.getById(dataCompanyJudgmentDocumentId.getId());
        DataCompanyJudgmentDocument dataCompanyJudgmentDocument = DomainFactory.create(DataCompanyJudgmentDocument.class);
        dataCompanyJudgmentDocument = DataCompanyJudgmentDocumentInfrastructureStructMapping.instance. dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocument(dataCompanyJudgmentDocument,byId);
        return dataCompanyJudgmentDocument;
    }

    @Override
    public boolean doSave(DataCompanyJudgmentDocument dataCompanyJudgmentDocument) {
        DataCompanyJudgmentDocumentDO dataCompanyJudgmentDocumentDO = DataCompanyJudgmentDocumentInfrastructureStructMapping.instance.dataCompanyJudgmentDocumentToDataCompanyJudgmentDocumentDO(dataCompanyJudgmentDocument);
        if (dataCompanyJudgmentDocumentDO.getId() == null) {
            dataCompanyJudgmentDocumentDO.setAddControl(dataCompanyJudgmentDocument.getAddControl());
            DataCompanyJudgmentDocumentDO add = iDataCompanyJudgmentDocumentService.add(dataCompanyJudgmentDocumentDO);
            dataCompanyJudgmentDocument.setId(DataCompanyJudgmentDocumentId.of(add.getId()));
            return add != null;
        }
        dataCompanyJudgmentDocumentDO.setUpdateControl(dataCompanyJudgmentDocument.getUpdateControl());
        DataCompanyJudgmentDocumentDO update = iDataCompanyJudgmentDocumentService.update(dataCompanyJudgmentDocumentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyJudgmentDocumentId dataCompanyJudgmentDocumentId) {
        return iDataCompanyJudgmentDocumentService.deleteById(dataCompanyJudgmentDocumentId.getId());
    }

    @Override
    public boolean delete(DataCompanyJudgmentDocumentId dataCompanyJudgmentDocumentId, IdCommand idCommand) {
        return iDataCompanyJudgmentDocumentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyJudgmentDocumentService(IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService) {
        this.iDataCompanyJudgmentDocumentService = iDataCompanyJudgmentDocumentService;
    }
}
