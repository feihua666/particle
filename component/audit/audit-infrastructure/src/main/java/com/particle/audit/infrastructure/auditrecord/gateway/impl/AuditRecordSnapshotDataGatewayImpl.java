package com.particle.audit.infrastructure.auditrecord.gateway.impl;

import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotDataId;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotDataGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotDataService;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;
import com.particle.audit.infrastructure.auditrecord.structmapping.AuditRecordSnapshotDataInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 审核记录数据快照 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Component
public class AuditRecordSnapshotDataGatewayImpl extends AbstractBaseGatewayImpl<AuditRecordSnapshotDataId,AuditRecordSnapshotData> implements AuditRecordSnapshotDataGateway {

    private IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService;

    @Override
    public AuditRecordSnapshotData getById(AuditRecordSnapshotDataId auditRecordSnapshotDataId) {
        AuditRecordSnapshotDataDO byId = iAuditRecordSnapshotDataService.getById(auditRecordSnapshotDataId.getId());
        AuditRecordSnapshotData auditRecordSnapshotData = DomainFactory.create(AuditRecordSnapshotData.class);
        auditRecordSnapshotData = AuditRecordSnapshotDataInfrastructureStructMapping.instance. auditRecordSnapshotDataDOToAuditRecordSnapshotData(auditRecordSnapshotData,byId);
        return auditRecordSnapshotData;
    }

    @Override
    public boolean doSave(AuditRecordSnapshotData auditRecordSnapshotData) {
        AuditRecordSnapshotDataDO auditRecordSnapshotDataDO = AuditRecordSnapshotDataInfrastructureStructMapping.instance.auditRecordSnapshotDataToAuditRecordSnapshotDataDO(auditRecordSnapshotData);
        if (auditRecordSnapshotDataDO.getId() == null) {
            auditRecordSnapshotDataDO.setAddControl(auditRecordSnapshotData.getAddControl());
            AuditRecordSnapshotDataDO add = iAuditRecordSnapshotDataService.add(auditRecordSnapshotDataDO);
            auditRecordSnapshotData.setId(AuditRecordSnapshotDataId.of(add.getId()));
            return add != null;
        }
        auditRecordSnapshotDataDO.setUpdateControl(auditRecordSnapshotData.getUpdateControl());
        AuditRecordSnapshotDataDO update = iAuditRecordSnapshotDataService.update(auditRecordSnapshotDataDO);
        return update != null;
    }

    @Override
    public boolean delete(AuditRecordSnapshotDataId auditRecordSnapshotDataId) {
        return iAuditRecordSnapshotDataService.deleteById(auditRecordSnapshotDataId.getId());
    }

    @Override
    public boolean delete(AuditRecordSnapshotDataId auditRecordSnapshotDataId, IdCommand idCommand) {
        return iAuditRecordSnapshotDataService.deleteById(idCommand);
    }

    @Autowired
    public void setIAuditRecordSnapshotDataService(IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService) {
        this.iAuditRecordSnapshotDataService = iAuditRecordSnapshotDataService;
    }
}
