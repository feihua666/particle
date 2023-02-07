package com.particle.func.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.command.FuncMoveCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 菜单功能 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IFuncApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param funcCreateCommand
	 * @return
	 */
	SingleResponse<FuncVO> create(FuncCreateCommand funcCreateCommand);

	/**
	 * 删除领域对象
	 * @param funcDeleteCommand
	 * @return
	 */
	SingleResponse<FuncVO> delete(IdCommand funcDeleteCommand);

	/**
	 * 更新领域对象
	 * @param funcUpdateCommand
	 * @return
	 */
	SingleResponse<FuncVO> update(FuncUpdateCommand funcUpdateCommand);

	/**
	 * 移动菜单功能
	 * @param funcMoveNodeCommand
	 * @return
	 */
	SingleResponse<FuncVO> moveNode(FuncMoveCommand funcMoveNodeCommand);


}
