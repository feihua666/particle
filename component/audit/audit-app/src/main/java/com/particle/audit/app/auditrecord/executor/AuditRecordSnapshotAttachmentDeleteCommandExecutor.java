package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotAttachmentAppStructMapping;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachment;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachmentId;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotAttachmentGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotAttachmentService;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 审核记录附件快照 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Component
@Validated
public class AuditRecordSnapshotAttachmentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotAttachmentGateway auditRecordSnapshotAttachmentGateway;
	private IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService;

	/**
	 * 执行 审核记录附件快照 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotAttachmentVO> execute(@Valid CommonIdCommand deleteCommand) {
		AuditRecordSnapshotAttachmentId auditRecordSnapshotAttachmentId = AuditRecordSnapshotAttachmentId.of(deleteCommand.getId());
		AuditRecordSnapshotAttachment byId = auditRecordSnapshotAttachmentGateway.getById(auditRecordSnapshotAttachmentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = auditRecordSnapshotAttachmentGateway.delete(auditRecordSnapshotAttachmentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AuditRecordSnapshotAttachmentAppStructMapping.instance.toAuditRecordSnapshotAttachmentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
