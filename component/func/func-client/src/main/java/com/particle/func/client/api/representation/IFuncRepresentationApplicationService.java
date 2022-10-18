package com.particle.func.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryDetailCommand;
import com.particle.func.client.dto.command.representation.FuncQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 菜单功能 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IFuncRepresentationApplicationService extends IBaseApplicationService {

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
