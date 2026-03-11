package com.particle.audit.client.auditrecord.api;

import com.particle.audit.client.auditrecord.dto.command.AuditRecordCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 审核记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
public interface IAuditRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param auditRecordCreateCommand
	 * @return
	 */
	SingleResponse<AuditRecordVO> create(AuditRecordCreateCommand auditRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AuditRecordVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param auditRecordUpdateCommand
	 * @return
	 */
	SingleResponse<AuditRecordVO> update(AuditRecordUpdateCommand auditRecordUpdateCommand);
}
