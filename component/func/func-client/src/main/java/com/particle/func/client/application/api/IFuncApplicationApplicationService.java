package com.particle.func.client.application.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.application.dto.command.FuncApplicationCreateCommand;
import com.particle.func.client.application.dto.command.FuncApplicationUpdateCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 功能应用 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
public interface IFuncApplicationApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param funcApplicationCreateCommand
	 * @return
	 */
	SingleResponse<FuncApplicationVO> create(FuncApplicationCreateCommand funcApplicationCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<FuncApplicationVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param funcApplicationUpdateCommand
	 * @return
	 */
	SingleResponse<FuncApplicationVO> update(FuncApplicationUpdateCommand funcApplicationUpdateCommand);

}
