package com.particle.oplog.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;

/**
 * <p>
 * 操作日志审计数据 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
public interface IOpLogAuditDataApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpLogAuditDataVO> delete(IdCommand deleteCommand);

}
