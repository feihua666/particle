package com.particle.audit.client.auditrecord.api;

import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 审核记录数据快照 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
public interface IAuditRecordSnapshotDataApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param auditRecordSnapshotDataCreateCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotDataVO> create(AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotDataVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param auditRecordSnapshotDataUpdateCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotDataVO> update(AuditRecordSnapshotDataUpdateCommand auditRecordSnapshotDataUpdateCommand);
}
