package com.particle.agi.infrastructure.rag.gateway.impl;

import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentGateway;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentService;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentDO;
import com.particle.agi.infrastructure.rag.structmapping.AgiVectorStoreRawDocumentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 知识存储原始文档 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Component
public class AgiVectorStoreRawDocumentGatewayImpl extends AbstractBaseGatewayImpl<AgiVectorStoreRawDocumentId,AgiVectorStoreRawDocument> implements AgiVectorStoreRawDocumentGateway {

    private IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService;

    @Override
    public AgiVectorStoreRawDocument getById(AgiVectorStoreRawDocumentId agiVectorStoreRawDocumentId) {
        AgiVectorStoreRawDocumentDO byId = iAgiVectorStoreRawDocumentService.getById(agiVectorStoreRawDocumentId.getId());
        AgiVectorStoreRawDocument agiVectorStoreRawDocument = DomainFactory.create(AgiVectorStoreRawDocument.class);
        agiVectorStoreRawDocument = AgiVectorStoreRawDocumentInfrastructureStructMapping.instance. agiVectorStoreRawDocumentDOToAgiVectorStoreRawDocument(agiVectorStoreRawDocument,byId);
        return agiVectorStoreRawDocument;
    }

    @Override
    public boolean doSave(AgiVectorStoreRawDocument agiVectorStoreRawDocument) {
        AgiVectorStoreRawDocumentDO agiVectorStoreRawDocumentDO = AgiVectorStoreRawDocumentInfrastructureStructMapping.instance.agiVectorStoreRawDocumentToAgiVectorStoreRawDocumentDO(agiVectorStoreRawDocument);
        if (agiVectorStoreRawDocumentDO.getId() == null) {
            agiVectorStoreRawDocumentDO.setAddControl(agiVectorStoreRawDocument.getAddControl());
            AgiVectorStoreRawDocumentDO add = iAgiVectorStoreRawDocumentService.add(agiVectorStoreRawDocumentDO);
            agiVectorStoreRawDocument.setId(AgiVectorStoreRawDocumentId.of(add.getId()));
            return add != null;
        }
        agiVectorStoreRawDocumentDO.setUpdateControl(agiVectorStoreRawDocument.getUpdateControl());
        AgiVectorStoreRawDocumentDO update = iAgiVectorStoreRawDocumentService.update(agiVectorStoreRawDocumentDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiVectorStoreRawDocumentId agiVectorStoreRawDocumentId) {
        return iAgiVectorStoreRawDocumentService.deleteById(agiVectorStoreRawDocumentId.getId());
    }

    @Override
    public boolean delete(AgiVectorStoreRawDocumentId agiVectorStoreRawDocumentId, IdCommand idCommand) {
        return iAgiVectorStoreRawDocumentService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiVectorStoreRawDocumentService(IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService) {
        this.iAgiVectorStoreRawDocumentService = iAgiVectorStoreRawDocumentService;
    }
}
