package com.particle.audit.infrastructure.auditrecord.gateway.impl;

import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachment;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachmentId;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotAttachmentGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotAttachmentService;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;
import com.particle.audit.infrastructure.auditrecord.structmapping.AuditRecordSnapshotAttachmentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 审核记录附件快照 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Component
public class AuditRecordSnapshotAttachmentGatewayImpl extends AbstractBaseGatewayImpl<AuditRecordSnapshotAttachmentId,AuditRecordSnapshotAttachment> implements AuditRecordSnapshotAttachmentGateway {

    private IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService;

    @Override
    public AuditRecordSnapshotAttachment getById(AuditRecordSnapshotAttachmentId auditRecordSnapshotAttachmentId) {
        AuditRecordSnapshotAttachmentDO byId = iAuditRecordSnapshotAttachmentService.getById(auditRecordSnapshotAttachmentId.getId());
        AuditRecordSnapshotAttachment auditRecordSnapshotAttachment = DomainFactory.create(AuditRecordSnapshotAttachment.class);
        auditRecordSnapshotAttachment = AuditRecordSnapshotAttachmentInfrastructureStructMapping.instance. auditRecordSnapshotAttachmentDOToAuditRecordSnapshotAttachment(auditRecordSnapshotAttachment,byId);
        return auditRecordSnapshotAttachment;
    }

    @Override
    public boolean doSave(AuditRecordSnapshotAttachment auditRecordSnapshotAttachment) {
        AuditRecordSnapshotAttachmentDO auditRecordSnapshotAttachmentDO = AuditRecordSnapshotAttachmentInfrastructureStructMapping.instance.auditRecordSnapshotAttachmentToAuditRecordSnapshotAttachmentDO(auditRecordSnapshotAttachment);
        if (auditRecordSnapshotAttachmentDO.getId() == null) {
            auditRecordSnapshotAttachmentDO.setAddControl(auditRecordSnapshotAttachment.getAddControl());
            AuditRecordSnapshotAttachmentDO add = iAuditRecordSnapshotAttachmentService.add(auditRecordSnapshotAttachmentDO);
            auditRecordSnapshotAttachment.setId(AuditRecordSnapshotAttachmentId.of(add.getId()));
            return add != null;
        }
        auditRecordSnapshotAttachmentDO.setUpdateControl(auditRecordSnapshotAttachment.getUpdateControl());
        AuditRecordSnapshotAttachmentDO update = iAuditRecordSnapshotAttachmentService.update(auditRecordSnapshotAttachmentDO);
        return update != null;
    }

    @Override
    public boolean delete(AuditRecordSnapshotAttachmentId auditRecordSnapshotAttachmentId) {
        return iAuditRecordSnapshotAttachmentService.deleteById(auditRecordSnapshotAttachmentId.getId());
    }

    @Override
    public boolean delete(AuditRecordSnapshotAttachmentId auditRecordSnapshotAttachmentId, IdCommand idCommand) {
        return iAuditRecordSnapshotAttachmentService.deleteById(idCommand);
    }

    @Autowired
    public void setIAuditRecordSnapshotAttachmentService(IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService) {
        this.iAuditRecordSnapshotAttachmentService = iAuditRecordSnapshotAttachmentService;
    }
}
