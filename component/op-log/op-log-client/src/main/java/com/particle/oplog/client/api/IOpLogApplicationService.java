package com.particle.oplog.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.dto.data.OpLogVO;

/**
 * <p>
 * 操作日志 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
public interface IOpLogApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpLogVO> delete(IdCommand deleteCommand);

}
