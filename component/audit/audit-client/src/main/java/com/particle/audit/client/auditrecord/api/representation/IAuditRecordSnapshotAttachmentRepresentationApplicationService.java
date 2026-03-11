package com.particle.audit.client.auditrecord.api.representation;

import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 审核记录附件快照 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAuditRecordSnapshotAttachmentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotAttachmentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotAttachmentVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param auditRecordSnapshotAttachmentQueryListCommand
	 * @return
	 */
	MultiResponse<AuditRecordSnapshotAttachmentVO> queryList(AuditRecordSnapshotAttachmentQueryListCommand auditRecordSnapshotAttachmentQueryListCommand);

	/**
	 * 分页查询
	 * @param auditRecordSnapshotAttachmentPageQueryCommand
	 * @return
	 */
	PageResponse<AuditRecordSnapshotAttachmentVO> pageQuery(AuditRecordSnapshotAttachmentPageQueryCommand auditRecordSnapshotAttachmentPageQueryCommand);

}
