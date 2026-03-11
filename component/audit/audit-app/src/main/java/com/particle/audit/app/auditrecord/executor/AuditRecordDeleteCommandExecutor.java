package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordAppStructMapping;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.AuditRecordId;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordService;
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
 * 审核记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Component
@Validated
public class AuditRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordGateway auditRecordGateway;
	private IAuditRecordService iAuditRecordService;

	/**
	 * 执行 审核记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AuditRecordVO> execute(@Valid CommonIdCommand deleteCommand) {
		AuditRecordId auditRecordId = AuditRecordId.of(deleteCommand.getId());
		AuditRecord byId = auditRecordGateway.getById(auditRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = auditRecordGateway.delete(auditRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AuditRecordAppStructMapping.instance.toAuditRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
	public void setIAuditRecordService(IAuditRecordService iAuditRecordService) {
		this.iAuditRecordService = iAuditRecordService;
	}
}
