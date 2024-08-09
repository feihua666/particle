package com.particle.oplog.client.error.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
/**
 * <p>
 * 操作异常日志 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
public interface IOpLogErrorApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param opLogErrorCreateCommand
	 * @return
	 */
	SingleResponse<OpLogErrorVO> create(OpLogErrorCreateCommand opLogErrorCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpLogErrorVO> delete(IdCommand deleteCommand);

}
