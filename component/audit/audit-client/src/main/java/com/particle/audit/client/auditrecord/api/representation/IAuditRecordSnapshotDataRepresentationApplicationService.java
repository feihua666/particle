package com.particle.audit.client.auditrecord.api.representation;

import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 审核记录数据快照 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAuditRecordSnapshotDataRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotDataVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AuditRecordSnapshotDataVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param auditRecordSnapshotDataQueryListCommand
	 * @return
	 */
	MultiResponse<AuditRecordSnapshotDataVO> queryList(AuditRecordSnapshotDataQueryListCommand auditRecordSnapshotDataQueryListCommand);

	/**
	 * 分页查询
	 * @param auditRecordSnapshotDataPageQueryCommand
	 * @return
	 */
	PageResponse<AuditRecordSnapshotDataVO> pageQuery(AuditRecordSnapshotDataPageQueryCommand auditRecordSnapshotDataPageQueryCommand);

}
