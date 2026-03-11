package com.particle.audit.app.auditrecord.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotAttachmentAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotAttachmentDO;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotAttachmentService;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 审核记录附件快照 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Component
@Validated
public class AuditRecordSnapshotAttachmentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService;

	/**
	 * 执行 审核记录附件快照 列表查询指令
	 * @param auditRecordSnapshotAttachmentQueryListCommand
	 * @return
	 */
	public MultiResponse<AuditRecordSnapshotAttachmentVO> execute(@Valid AuditRecordSnapshotAttachmentQueryListCommand auditRecordSnapshotAttachmentQueryListCommand) {
		List<AuditRecordSnapshotAttachmentDO> auditRecordSnapshotAttachmentDO = iAuditRecordSnapshotAttachmentService.list(auditRecordSnapshotAttachmentQueryListCommand);
		List<AuditRecordSnapshotAttachmentVO> auditRecordSnapshotAttachmentVOs = AuditRecordSnapshotAttachmentAppStructMapping.instance.auditRecordSnapshotAttachmentDOsToAuditRecordSnapshotAttachmentVOs(auditRecordSnapshotAttachmentDO);
		return MultiResponse.of(auditRecordSnapshotAttachmentVOs);
	}
	/**
	 * 执行 审核记录附件快照 分页查询指令
	 * @param auditRecordSnapshotAttachmentPageQueryCommand
	 * @return
	 */
	public PageResponse<AuditRecordSnapshotAttachmentVO> execute(@Valid AuditRecordSnapshotAttachmentPageQueryCommand auditRecordSnapshotAttachmentPageQueryCommand) {
		Page<AuditRecordSnapshotAttachmentDO> page = iAuditRecordSnapshotAttachmentService.listPage(auditRecordSnapshotAttachmentPageQueryCommand);
		return AuditRecordSnapshotAttachmentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 审核记录附件快照 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotAttachmentVO> executeDetail(CommonIdCommand detailCommand) {
		AuditRecordSnapshotAttachmentDO byId = iAuditRecordSnapshotAttachmentService.getById(detailCommand.getId());
		AuditRecordSnapshotAttachmentVO auditRecordSnapshotAttachmentVO = AuditRecordSnapshotAttachmentAppStructMapping.instance.auditRecordSnapshotAttachmentDOToAuditRecordSnapshotAttachmentVO(byId);
		return SingleResponse.of(auditRecordSnapshotAttachmentVO);
	}
	/**
	 * 执行 审核记录附件快照 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotAttachmentVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		AuditRecordSnapshotAttachmentDO byId = iAuditRecordSnapshotAttachmentService.getById(detailForUpdateCommand.getId());
		AuditRecordSnapshotAttachmentVO auditRecordSnapshotAttachmentVO = AuditRecordSnapshotAttachmentAppStructMapping.instance.auditRecordSnapshotAttachmentDOToAuditRecordSnapshotAttachmentVO(byId);
		return SingleResponse.of(auditRecordSnapshotAttachmentVO);
	}


	@Autowired
	public void setIAuditRecordSnapshotAttachmentService(IAuditRecordSnapshotAttachmentService iAuditRecordSnapshotAttachmentService) {
		this.iAuditRecordSnapshotAttachmentService = iAuditRecordSnapshotAttachmentService;
	}
}
