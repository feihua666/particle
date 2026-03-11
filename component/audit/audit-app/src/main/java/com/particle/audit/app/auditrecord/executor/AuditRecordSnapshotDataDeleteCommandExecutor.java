package com.particle.audit.app.auditrecord.executor;

import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotDataAppStructMapping;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotDataId;
import com.particle.audit.domain.auditrecord.gateway.AuditRecordSnapshotDataGateway;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotDataService;
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
 * 审核记录数据快照 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Component
@Validated
public class AuditRecordSnapshotDataDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AuditRecordSnapshotDataGateway auditRecordSnapshotDataGateway;
	private IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService;

	/**
	 * 执行 审核记录数据快照 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotDataVO> execute(@Valid CommonIdCommand deleteCommand) {
		AuditRecordSnapshotDataId auditRecordSnapshotDataId = AuditRecordSnapshotDataId.of(deleteCommand.getId());
		AuditRecordSnapshotData byId = auditRecordSnapshotDataGateway.getById(auditRecordSnapshotDataId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = auditRecordSnapshotDataGateway.delete(auditRecordSnapshotDataId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AuditRecordSnapshotDataAppStructMapping.instance.toAuditRecordSnapshotDataVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
