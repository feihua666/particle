package com.particle.audit.app.auditrecord.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.audit.app.auditrecord.structmapping.AuditRecordSnapshotDataAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordSnapshotDataDO;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordSnapshotDataService;
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
 * 审核记录数据快照 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Component
@Validated
public class AuditRecordSnapshotDataQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService;

	/**
	 * 执行 审核记录数据快照 列表查询指令
	 * @param auditRecordSnapshotDataQueryListCommand
	 * @return
	 */
	public MultiResponse<AuditRecordSnapshotDataVO> execute(@Valid AuditRecordSnapshotDataQueryListCommand auditRecordSnapshotDataQueryListCommand) {
		List<AuditRecordSnapshotDataDO> auditRecordSnapshotDataDO = iAuditRecordSnapshotDataService.list(auditRecordSnapshotDataQueryListCommand);
		List<AuditRecordSnapshotDataVO> auditRecordSnapshotDataVOs = AuditRecordSnapshotDataAppStructMapping.instance.auditRecordSnapshotDataDOsToAuditRecordSnapshotDataVOs(auditRecordSnapshotDataDO);
		return MultiResponse.of(auditRecordSnapshotDataVOs);
	}
	/**
	 * 执行 审核记录数据快照 分页查询指令
	 * @param auditRecordSnapshotDataPageQueryCommand
	 * @return
	 */
	public PageResponse<AuditRecordSnapshotDataVO> execute(@Valid AuditRecordSnapshotDataPageQueryCommand auditRecordSnapshotDataPageQueryCommand) {
		Page<AuditRecordSnapshotDataDO> page = iAuditRecordSnapshotDataService.listPage(auditRecordSnapshotDataPageQueryCommand);
		return AuditRecordSnapshotDataAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 审核记录数据快照 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotDataVO> executeDetail(CommonIdCommand detailCommand) {
		AuditRecordSnapshotDataDO byId = iAuditRecordSnapshotDataService.getById(detailCommand.getId());
		AuditRecordSnapshotDataVO auditRecordSnapshotDataVO = AuditRecordSnapshotDataAppStructMapping.instance.auditRecordSnapshotDataDOToAuditRecordSnapshotDataVO(byId);
		return SingleResponse.of(auditRecordSnapshotDataVO);
	}
	/**
	 * 执行 审核记录数据快照 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordSnapshotDataVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		AuditRecordSnapshotDataDO byId = iAuditRecordSnapshotDataService.getById(detailForUpdateCommand.getId());
		AuditRecordSnapshotDataVO auditRecordSnapshotDataVO = AuditRecordSnapshotDataAppStructMapping.instance.auditRecordSnapshotDataDOToAuditRecordSnapshotDataVO(byId);
		return SingleResponse.of(auditRecordSnapshotDataVO);
	}


	@Autowired
	public void setIAuditRecordSnapshotDataService(IAuditRecordSnapshotDataService iAuditRecordSnapshotDataService) {
		this.iAuditRecordSnapshotDataService = iAuditRecordSnapshotDataService;
	}
}
