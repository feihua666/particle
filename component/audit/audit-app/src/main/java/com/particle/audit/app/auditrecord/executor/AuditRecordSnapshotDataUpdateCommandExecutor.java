package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotDataAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotDataId;
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
 * 审核记录数据快照 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AuditRecordSnapshotDataUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotDataGateway auditRecordSnapshotDataGateway;

	/**
	 * 执行 审核记录数据快照 更新指令
	 * @param auditRecordSnapshotDataUpdateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotDataVO> execute(@Valid AuditRecordSnapshotDataUpdateCommand auditRecordSnapshotDataUpdateCommand) {
		AuditRecordSnapshotData auditRecordSnapshotData = createByAuditRecordSnapshotDataUpdateCommand(auditRecordSnapshotDataUpdateCommand);
		auditRecordSnapshotData.setUpdateControl(auditRecordSnapshotDataUpdateCommand);
		boolean save = auditRecordSnapshotDataGateway.save(auditRecordSnapshotData);
		if (save) {
			return SingleResponse.of(AuditRecordSnapshotDataAppStructMapping.instance.toAuditRecordSnapshotDataVO(auditRecordSnapshotData));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据审核记录数据快照更新指令创建审核记录数据快照模型
	 * @param auditRecordSnapshotDataUpdateCommand
	 * @return
	 */
	private AuditRecordSnapshotData createByAuditRecordSnapshotDataUpdateCommand(AuditRecordSnapshotDataUpdateCommand auditRecordSnapshotDataUpdateCommand){
		AuditRecordSnapshotData auditRecordSnapshotData = AuditRecordSnapshotData.create();
		AuditRecordSnapshotDataUpdateCommandToAuditRecordSnapshotDataMapping.instance.fillAuditRecordSnapshotDataByAuditRecordSnapshotDataUpdateCommand(auditRecordSnapshotData, auditRecordSnapshotDataUpdateCommand);
		return auditRecordSnapshotData;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AuditRecordSnapshotDataUpdateCommandToAuditRecordSnapshotDataMapping{
		AuditRecordSnapshotDataUpdateCommandToAuditRecordSnapshotDataMapping instance = Mappers.getMapper(AuditRecordSnapshotDataUpdateCommandToAuditRecordSnapshotDataMapping.class );

		default AuditRecordSnapshotDataId map(Long id){
			if (id == null) {
				return null;
			}
			return AuditRecordSnapshotDataId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param auditRecordSnapshotData
		 * @param auditRecordSnapshotDataUpdateCommand
		 */
		void fillAuditRecordSnapshotDataByAuditRecordSnapshotDataUpdateCommand(@MappingTarget AuditRecordSnapshotData auditRecordSnapshotData, AuditRecordSnapshotDataUpdateCommand auditRecordSnapshotDataUpdateCommand);
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
