package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.AuditRecordId;
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

/**
 * <p>
 * 审核记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AuditRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordGateway auditRecordGateway;

	/**
	 * 执行 审核记录 更新指令
	 * @param auditRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordVO> execute(@Valid AuditRecordUpdateCommand auditRecordUpdateCommand) {
		AuditRecord auditRecord = createByAuditRecordUpdateCommand(auditRecordUpdateCommand);
		auditRecord.setUpdateControl(auditRecordUpdateCommand);
		boolean save = auditRecordGateway.save(auditRecord);
		if (save) {
			return SingleResponse.of(AuditRecordAppStructMapping.instance.toAuditRecordVO(auditRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据审核记录更新指令创建审核记录模型
	 * @param auditRecordUpdateCommand
	 * @return
	 */
	private AuditRecord createByAuditRecordUpdateCommand(AuditRecordUpdateCommand auditRecordUpdateCommand){
		AuditRecord auditRecord = AuditRecord.create();
		AuditRecordUpdateCommandToAuditRecordMapping.instance.fillAuditRecordByAuditRecordUpdateCommand(auditRecord, auditRecordUpdateCommand);
		return auditRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AuditRecordUpdateCommandToAuditRecordMapping{
		AuditRecordUpdateCommandToAuditRecordMapping instance = Mappers.getMapper(AuditRecordUpdateCommandToAuditRecordMapping.class );

		default AuditRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return AuditRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param auditRecord
		 * @param auditRecordUpdateCommand
		 */
		void fillAuditRecordByAuditRecordUpdateCommand(@MappingTarget AuditRecord auditRecord, AuditRecordUpdateCommand auditRecordUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param auditRecordGateway
	 */
	@Autowired
	public void setAuditRecordGateway(AuditRecordGateway auditRecordGateway) {
		this.auditRecordGateway = auditRecordGateway;
	}
}
