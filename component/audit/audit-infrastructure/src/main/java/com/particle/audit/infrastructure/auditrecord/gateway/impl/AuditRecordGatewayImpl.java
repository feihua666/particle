package com.particle.audit.infrastructure.auditrecord.gateway.impl;

import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.AuditRecordId;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordService;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;
import com.particle.audit.infrastructure.auditrecord.structmapping.AuditRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 审核记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Component
public class AuditRecordGatewayImpl extends AbstractBaseGatewayImpl<AuditRecordId,AuditRecord> implements AuditRecordGateway {

    private IAuditRecordService iAuditRecordService;

    @Override
    public AuditRecord getById(AuditRecordId auditRecordId) {
        AuditRecordDO byId = iAuditRecordService.getById(auditRecordId.getId());
        AuditRecord auditRecord = DomainFactory.create(AuditRecord.class);
        auditRecord = AuditRecordInfrastructureStructMapping.instance. auditRecordDOToAuditRecord(auditRecord,byId);
        return auditRecord;
    }

    @Override
    public boolean doSave(AuditRecord auditRecord) {
        AuditRecordDO auditRecordDO = AuditRecordInfrastructureStructMapping.instance.auditRecordToAuditRecordDO(auditRecord);
        if (auditRecordDO.getId() == null) {
            auditRecordDO.setAddControl(auditRecord.getAddControl());
            AuditRecordDO add = iAuditRecordService.add(auditRecordDO);
            auditRecord.setId(AuditRecordId.of(add.getId()));
            return add != null;
        }
        auditRecordDO.setUpdateControl(auditRecord.getUpdateControl());
        AuditRecordDO update = iAuditRecordService.update(auditRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(AuditRecordId auditRecordId) {
        return iAuditRecordService.deleteById(auditRecordId.getId());
    }

    @Override
    public boolean delete(AuditRecordId auditRecordId, IdCommand idCommand) {
        return iAuditRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setIAuditRecordService(IAuditRecordService iAuditRecordService) {
        this.iAuditRecordService = iAuditRecordService;
    }
}
