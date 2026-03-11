package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotDataGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotDataService;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 审核记录数据快照 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Component
@Validated
public class AuditRecordSnapshotDataCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotDataGateway auditRecordSnapshotDataGateway;
	private IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService;
	/**
	 * 注入使用set方法
	 * @param auditRecordSnapshotDataGateway
	 */
	@Autowired
	public void setAuditRecordSnapshotDataGateway(AuditRecordSnapshotDataGateway auditRecordSnapshotDataGateway) {
		this.auditRecordSnapshotDataGateway = auditRecordSnapshotDataGateway;
	}
	@Autowired
	public void setIAuditRecordSnapshotDataService(IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService) {
		this.iAuditRecordSnapshotDataService = iAuditRecordSnapshotDataService;
	}
}
