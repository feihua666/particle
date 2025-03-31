package com.particle.agi.infrastructure.rag.gateway.impl;

import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegmentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentSegmentGateway;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.infrastructure.rag.structmapping.AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 知识存储原始文档片段 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Component
public class AgiVectorStoreRawDocumentSegmentGatewayImpl extends AbstractBaseGatewayImpl<AgiVectorStoreRawDocumentSegmentId,AgiVectorStoreRawDocumentSegment> implements AgiVectorStoreRawDocumentSegmentGateway {

    private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;

    @Override
    public AgiVectorStoreRawDocumentSegment getById(AgiVectorStoreRawDocumentSegmentId agiVectorStoreRawDocumentSegmentId) {
        AgiVectorStoreRawDocumentSegmentDO byId = iAgiVectorStoreRawDocumentSegmentService.getById(agiVectorStoreRawDocumentSegmentId.getId());
        AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment = DomainFactory.create(AgiVectorStoreRawDocumentSegment.class);
        agiVectorStoreRawDocumentSegment = AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping.instance. agiVectorStoreRawDocumentSegmentDOToAgiVectorStoreRawDocumentSegment(agiVectorStoreRawDocumentSegment,byId);
        return agiVectorStoreRawDocumentSegment;
    }

    @Override
    public boolean doSave(AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment) {
        AgiVectorStoreRawDocumentSegmentDO agiVectorStoreRawDocumentSegmentDO = AgiVectorStoreRawDocumentSegmentInfrastructureStructMapping.instance.agiVectorStoreRawDocumentSegmentToAgiVectorStoreRawDocumentSegmentDO(agiVectorStoreRawDocumentSegment);
        if (agiVectorStoreRawDocumentSegmentDO.getId() == null) {
            agiVectorStoreRawDocumentSegmentDO.setAddControl(agiVectorStoreRawDocumentSegment.getAddControl());
            AgiVectorStoreRawDocumentSegmentDO add = iAgiVectorStoreRawDocumentSegmentService.add(agiVectorStoreRawDocumentSegmentDO);
            agiVectorStoreRawDocumentSegment.setId(AgiVectorStoreRawDocumentSegmentId.of(add.getId()));
            return add != null;
        }
        agiVectorStoreRawDocumentSegmentDO.setUpdateControl(agiVectorStoreRawDocumentSegment.getUpdateControl());
        AgiVectorStoreRawDocumentSegmentDO update = iAgiVectorStoreRawDocumentSegmentService.update(agiVectorStoreRawDocumentSegmentDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiVectorStoreRawDocumentSegmentId agiVectorStoreRawDocumentSegmentId) {
        return iAgiVectorStoreRawDocumentSegmentService.deleteById(agiVectorStoreRawDocumentSegmentId.getId());
    }

    @Override
    public boolean delete(AgiVectorStoreRawDocumentSegmentId agiVectorStoreRawDocumentSegmentId, IdCommand idCommand) {
        return iAgiVectorStoreRawDocumentSegmentService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiVectorStoreRawDocumentSegmentService(IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService) {
        this.iAgiVectorStoreRawDocumentSegmentService = iAgiVectorStoreRawDocumentSegmentService;
    }
}
