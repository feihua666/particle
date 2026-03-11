package com.particle.oplog.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.dto.command.OpLogCreateCommand;
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
     * 添加/创建一个领域对象
     * @param opLogCreateCommand
     * @return
     */
    SingleResponse<OpLogVO> create(OpLogCreateCommand opLogCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpLogVO> delete(CommonIdCommand deleteCommand);

}
