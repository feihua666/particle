package com.particle.audit.client.auditrecord.api.representation;

import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.client.auditrecord.dto.data.AuditResultDictVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 审核记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAuditRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AuditRecordVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AuditRecordVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param auditRecordQueryListCommand
	 * @return
	 */
	MultiResponse<AuditRecordVO> queryList(AuditRecordQueryListCommand auditRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param auditRecordPageQueryCommand
	 * @return
	 */
	PageResponse<AuditRecordVO> pageQuery(AuditRecordPageQueryCommand auditRecordPageQueryCommand);


	/**
	 * 审核结果字典
	 * @return
	 */
	public SingleResponse<AuditResultDictVO> auditResultDict();
}
