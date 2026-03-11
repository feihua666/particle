package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotAttachmentAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachment;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotAttachmentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

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
public class AuditRecordSnapshotAttachmentCreateCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotAttachmentGateway auditRecordSnapshotAttachmentGateway;

	/**
	 * 执行审核记录附件快照添加指令
	 * @param auditRecordSnapshotAttachmentCreateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotAttachmentVO> execute(@Valid AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand) {
		AuditRecordSnapshotAttachment auditRecordSnapshotAttachment = createByAuditRecordSnapshotAttachmentCreateCommand(auditRecordSnapshotAttachmentCreateCommand);
		auditRecordSnapshotAttachment.setAddControl(auditRecordSnapshotAttachmentCreateCommand);
		boolean save = auditRecordSnapshotAttachmentGateway.save(auditRecordSnapshotAttachment);
		if (save) {
			return SingleResponse.of(AuditRecordSnapshotAttachmentAppStructMapping.instance.toAuditRecordSnapshotAttachmentVO(auditRecordSnapshotAttachment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据审核记录附件快照创建指令创建审核记录附件快照模型
	 * @param auditRecordSnapshotAttachmentCreateCommand
	 * @return
	 */
	private AuditRecordSnapshotAttachment createByAuditRecordSnapshotAttachmentCreateCommand(AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand){
		AuditRecordSnapshotAttachment auditRecordSnapshotAttachment = AuditRecordSnapshotAttachment.create();
		AuditRecordSnapshotAttachmentCreateCommandToAuditRecordSnapshotAttachmentMapping.instance.fillAuditRecordSnapshotAttachmentByAuditRecordSnapshotAttachmentCreateCommand(auditRecordSnapshotAttachment, auditRecordSnapshotAttachmentCreateCommand);
		return auditRecordSnapshotAttachment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AuditRecordSnapshotAttachmentCreateCommandToAuditRecordSnapshotAttachmentMapping{
		AuditRecordSnapshotAttachmentCreateCommandToAuditRecordSnapshotAttachmentMapping instance = Mappers.getMapper( AuditRecordSnapshotAttachmentCreateCommandToAuditRecordSnapshotAttachmentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param auditRecordSnapshotAttachment
		 * @param auditRecordSnapshotAttachmentCreateCommand
		 */
		void fillAuditRecordSnapshotAttachmentByAuditRecordSnapshotAttachmentCreateCommand(@MappingTarget AuditRecordSnapshotAttachment auditRecordSnapshotAttachment, AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param auditRecordSnapshotAttachmentGateway
	 */
	@Autowired
	public void setAuditRecordSnapshotAttachmentGateway(AuditRecordSnapshotAttachmentGateway auditRecordSnapshotAttachmentGateway) {
		this.auditRecordSnapshotAttachmentGateway = auditRecordSnapshotAttachmentGateway;
	}
}
