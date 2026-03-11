package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotAttachmentAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachment;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotAttachmentId;
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
 * 审核记录附件快照 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AuditRecordSnapshotAttachmentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotAttachmentGateway auditRecordSnapshotAttachmentGateway;

	/**
	 * 执行 审核记录附件快照 更新指令
	 * @param auditRecordSnapshotAttachmentUpdateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotAttachmentVO> execute(@Valid AuditRecordSnapshotAttachmentUpdateCommand auditRecordSnapshotAttachmentUpdateCommand) {
		AuditRecordSnapshotAttachment auditRecordSnapshotAttachment = createByAuditRecordSnapshotAttachmentUpdateCommand(auditRecordSnapshotAttachmentUpdateCommand);
		auditRecordSnapshotAttachment.setUpdateControl(auditRecordSnapshotAttachmentUpdateCommand);
		boolean save = auditRecordSnapshotAttachmentGateway.save(auditRecordSnapshotAttachment);
		if (save) {
			return SingleResponse.of(AuditRecordSnapshotAttachmentAppStructMapping.instance.toAuditRecordSnapshotAttachmentVO(auditRecordSnapshotAttachment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据审核记录附件快照更新指令创建审核记录附件快照模型
	 * @param auditRecordSnapshotAttachmentUpdateCommand
	 * @return
	 */
	private AuditRecordSnapshotAttachment createByAuditRecordSnapshotAttachmentUpdateCommand(AuditRecordSnapshotAttachmentUpdateCommand auditRecordSnapshotAttachmentUpdateCommand){
		AuditRecordSnapshotAttachment auditRecordSnapshotAttachment = AuditRecordSnapshotAttachment.create();
		AuditRecordSnapshotAttachmentUpdateCommandToAuditRecordSnapshotAttachmentMapping.instance.fillAuditRecordSnapshotAttachmentByAuditRecordSnapshotAttachmentUpdateCommand(auditRecordSnapshotAttachment, auditRecordSnapshotAttachmentUpdateCommand);
		return auditRecordSnapshotAttachment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AuditRecordSnapshotAttachmentUpdateCommandToAuditRecordSnapshotAttachmentMapping{
		AuditRecordSnapshotAttachmentUpdateCommandToAuditRecordSnapshotAttachmentMapping instance = Mappers.getMapper(AuditRecordSnapshotAttachmentUpdateCommandToAuditRecordSnapshotAttachmentMapping.class );

		default AuditRecordSnapshotAttachmentId map(Long id){
			if (id == null) {
				return null;
			}
			return AuditRecordSnapshotAttachmentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param auditRecordSnapshotAttachment
		 * @param auditRecordSnapshotAttachmentUpdateCommand
		 */
		void fillAuditRecordSnapshotAttachmentByAuditRecordSnapshotAttachmentUpdateCommand(@MappingTarget AuditRecordSnapshotAttachment auditRecordSnapshotAttachment, AuditRecordSnapshotAttachmentUpdateCommand auditRecordSnapshotAttachmentUpdateCommand);
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
