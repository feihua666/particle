package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.domain.auditrecord.gateway.AuditRecordGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordService;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 审核记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Component
@Validated
public class AuditRecordCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordGateway auditRecordGateway;
	private IAuditRecordService iAuditRecordService;
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
