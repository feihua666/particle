package com.particle.oplog.client.error.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
/**
 * <p>
 * 操作异常日志内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
public interface IOpLogErrorContentApplicationService extends IBaseApplicationService {
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpLogErrorContentVO> delete(IdCommand deleteCommand);

}
