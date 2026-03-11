package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotAttachmentGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotAttachmentService;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 审核记录附件快照 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Component
@Validated
public class AuditRecordSnapshotAttachmentCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotAttachmentGateway auditRecordSnapshotAttachmentGateway;
	private IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService;
	/**
	 * 注入使用set方法
	 * @param auditRecordSnapshotAttachmentGateway
	 */
	@Autowired
	public void setAuditRecordSnapshotAttachmentGateway(AuditRecordSnapshotAttachmentGateway auditRecordSnapshotAttachmentGateway) {
		this.auditRecordSnapshotAttachmentGateway = auditRecordSnapshotAttachmentGateway;
	}
	@Autowired
	public void setIAuditRecordSnapshotAttachmentService(IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService) {
		this.iAuditRecordSnapshotAttachmentService = iAuditRecordSnapshotAttachmentService;
	}
}
