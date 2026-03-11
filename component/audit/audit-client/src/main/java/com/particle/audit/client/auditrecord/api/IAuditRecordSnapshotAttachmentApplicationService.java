package com.particle.audit.client.auditrecord.api;

import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 审核记录附件快照 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
public interface IAuditRecordSnapshotAttachmentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param auditRecordSnapshotAttachmentCreateCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotAttachmentVO> create(AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotAttachmentVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param auditRecordSnapshotAttachmentUpdateCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotAttachmentVO> update(AuditRecordSnapshotAttachmentUpdateCommand auditRecordSnapshotAttachmentUpdateCommand);
}
