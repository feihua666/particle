package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotDataAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotDataGateway;
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
 * 审核记录数据快照 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Component
@Validated
public class AuditRecordSnapshotDataCreateCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotDataGateway auditRecordSnapshotDataGateway;

	/**
	 * 执行审核记录数据快照添加指令
	 * @param auditRecordSnapshotDataCreateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotDataVO> execute(@Valid AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand) {
		AuditRecordSnapshotData auditRecordSnapshotData = createByAuditRecordSnapshotDataCreateCommand(auditRecordSnapshotDataCreateCommand);
		auditRecordSnapshotData.setAddControl(auditRecordSnapshotDataCreateCommand);
		boolean save = auditRecordSnapshotDataGateway.save(auditRecordSnapshotData);
		if (save) {
			return SingleResponse.of(AuditRecordSnapshotDataAppStructMapping.instance.toAuditRecordSnapshotDataVO(auditRecordSnapshotData));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据审核记录数据快照创建指令创建审核记录数据快照模型
	 * @param auditRecordSnapshotDataCreateCommand
	 * @return
	 */
	private AuditRecordSnapshotData createByAuditRecordSnapshotDataCreateCommand(AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand){
		AuditRecordSnapshotData auditRecordSnapshotData = AuditRecordSnapshotData.create();
		AuditRecordSnapshotDataCreateCommandToAuditRecordSnapshotDataMapping.instance.fillAuditRecordSnapshotDataByAuditRecordSnapshotDataCreateCommand(auditRecordSnapshotData, auditRecordSnapshotDataCreateCommand);
		return auditRecordSnapshotData;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AuditRecordSnapshotDataCreateCommandToAuditRecordSnapshotDataMapping{
		AuditRecordSnapshotDataCreateCommandToAuditRecordSnapshotDataMapping instance = Mappers.getMapper( AuditRecordSnapshotDataCreateCommandToAuditRecordSnapshotDataMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param auditRecordSnapshotData
		 * @param auditRecordSnapshotDataCreateCommand
		 */
		void fillAuditRecordSnapshotDataByAuditRecordSnapshotDataCreateCommand(@MappingTarget AuditRecordSnapshotData auditRecordSnapshotData, AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param auditRecordSnapshotDataGateway
	 */
	@Autowired
	public void setAuditRecordSnapshotDataGateway(AuditRecordSnapshotDataGateway auditRecordSnapshotDataGateway) {
		this.auditRecordSnapshotDataGateway = auditRecordSnapshotDataGateway;
	}
}
