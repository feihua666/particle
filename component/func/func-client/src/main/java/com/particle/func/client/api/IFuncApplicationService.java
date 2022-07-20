package com.particle.func.client.api;

import com.particle.func.client.dto.command.FuncCreateCommand;
import com.particle.func.client.dto.command.FuncQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.FuncQueryDetailCommand;
import com.particle.func.client.dto.command.FuncDeleteCommand;
import com.particle.func.client.dto.command.FuncUpdateCommand;
import com.particle.func.client.dto.command.FuncPageQueryCommand;
import com.particle.func.client.dto.command.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

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
	SingleResponse<FuncVO> delete(FuncDeleteCommand funcDeleteCommand);

	/**
	 * 更新领域对象
	 * @param funcUpdateCommand
	 * @return
	 */
	SingleResponse<FuncVO> update(FuncUpdateCommand funcUpdateCommand);

	/**
	 * 查询详情，仅更新时使用
	 * @param funcQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<FuncVO> queryDetailForUpdate(FuncQueryDetailForUpdateCommand funcQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param funcQueryDetailCommand
	 * @return
	 */
	SingleResponse<FuncVO> queryDetail(FuncQueryDetailCommand funcQueryDetailCommand);

	/**
	 * 列表查询
	 * @param funcQueryListCommand
	 * @return
	 */
	MultiResponse<FuncVO> queryList(FuncQueryListCommand funcQueryListCommand);

	/**
	 * 分页查询
	 * @param funcPageQueryCommand
	 * @return
	 */
	PageResponse<FuncVO> pageQuery(FuncPageQueryCommand funcPageQueryCommand);

}
