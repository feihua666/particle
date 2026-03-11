package com.particle.audit.app.auditrecord.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.audit.app.auditrecord.structmapping.AuditRecordAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.*;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordGateway;
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

import java.util.List;

/**
 * <p>
 * 审核记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Component
@Validated
public class AuditRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordGateway auditRecordGateway;
	private AuditRecordSnapshotDataCreateCommandExecutor auditRecordSnapshotDataCreateCommandExecutor;
	private AuditRecordSnapshotAttachmentCreateCommandExecutor auditRecordSnapshotAttachmentCreateCommandExecutor;

	/**
	 * 执行审核记录添加指令
	 * @param auditRecordCreateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordVO> execute(@Valid AuditRecordCreateCommand auditRecordCreateCommand) {
		AuditRecord auditRecord = createByAuditRecordCreateCommand(auditRecordCreateCommand);
		auditRecord.setAddControl(auditRecordCreateCommand);
		boolean save = auditRecordGateway.save(auditRecord);
		if (save) {
			// 添加数据快照
			List<AuditRecordSnapshotDataCreateSimpleCommand> snapshotDataList = auditRecordCreateCommand.getSnapshotDataList();
            if (CollectionUtil.isNotEmpty(snapshotDataList)) {
				for (AuditRecordSnapshotDataCreateSimpleCommand auditRecordSnapshotDataCreateSimpleCommand : snapshotDataList) {
					AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand = AuditRecordSnapshotDataCreateCommand.create(
							auditRecord.getId().getId(),
							auditRecord.getDataId(),
							auditRecordSnapshotDataCreateSimpleCommand
					);
					auditRecordSnapshotDataCreateCommandExecutor.execute(auditRecordSnapshotDataCreateCommand);
				}
            }
			// 添加附件快照
			List<AuditRecordSnapshotAttachmentCreateSimpleCommand> snapshotAttachmentList = auditRecordCreateCommand.getSnapshotAttachmentList();
            if (CollectionUtil.isNotEmpty(snapshotAttachmentList)) {
				for (AuditRecordSnapshotAttachmentCreateSimpleCommand auditRecordSnapshotAttachmentCreateSimpleCommand : snapshotAttachmentList) {
					AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand = AuditRecordSnapshotAttachmentCreateCommand.create(
							auditRecord.getId().getId(),
							auditRecord.getDataId(),
							auditRecordSnapshotAttachmentCreateSimpleCommand
					);
					auditRecordSnapshotAttachmentCreateCommandExecutor.execute(auditRecordSnapshotAttachmentCreateCommand);
				}
            }
			return SingleResponse.of(AuditRecordAppStructMapping.instance.toAuditRecordVO(auditRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据审核记录创建指令创建审核记录模型
	 * @param auditRecordCreateCommand
	 * @return
	 */
	private AuditRecord createByAuditRecordCreateCommand(AuditRecordCreateCommand auditRecordCreateCommand){
		AuditRecord auditRecord = AuditRecord.create();
		AuditRecordCreateCommandToAuditRecordMapping.instance.fillAuditRecordByAuditRecordCreateCommand(auditRecord, auditRecordCreateCommand);
		return auditRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AuditRecordCreateCommandToAuditRecordMapping{
		AuditRecordCreateCommandToAuditRecordMapping instance = Mappers.getMapper( AuditRecordCreateCommandToAuditRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param auditRecord
		 * @param auditRecordCreateCommand
		 */
		void fillAuditRecordByAuditRecordCreateCommand(@MappingTarget AuditRecord auditRecord, AuditRecordCreateCommand auditRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param auditRecordGateway
	 */
	@Autowired
	public void setAuditRecordGateway(AuditRecordGateway auditRecordGateway) {
		this.auditRecordGateway = auditRecordGateway;
	}
	@Autowired
	public void setAuditRecordSnapshotDataCreateCommandExecutor(AuditRecordSnapshotDataCreateCommandExecutor auditRecordSnapshotDataCreateCommandExecutor) {
		this.auditRecordSnapshotDataCreateCommandExecutor = auditRecordSnapshotDataCreateCommandExecutor;
	}
	@Autowired
	public void setAuditRecordSnapshotAttachmentCreateCommandExecutor(AuditRecordSnapshotAttachmentCreateCommandExecutor auditRecordSnapshotAttachmentCreateCommandExecutor) {
		this.auditRecordSnapshotAttachmentCreateCommandExecutor = auditRecordSnapshotAttachmentCreateCommandExecutor;
	}
}
