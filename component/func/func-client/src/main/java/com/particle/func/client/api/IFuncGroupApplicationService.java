package com.particle.func.client.api;

import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.command.FuncGroupQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.FuncGroupQueryDetailCommand;
import com.particle.func.client.dto.command.FuncGroupDeleteCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.command.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 功能组 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IFuncGroupApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param funcGroupCreateCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> create(FuncGroupCreateCommand funcGroupCreateCommand);

	/**
	 * 删除领域对象
	 * @param funcGroupDeleteCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> delete(FuncGroupDeleteCommand funcGroupDeleteCommand);

	/**
	 * 更新领域对象
	 * @param funcGroupUpdateCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> update(FuncGroupUpdateCommand funcGroupUpdateCommand);

	/**
	 * 查询详情，仅更新时使用
	 * @param funcGroupQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> queryDetailForUpdate(FuncGroupQueryDetailForUpdateCommand funcGroupQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param funcGroupQueryDetailCommand
	 * @return
	 */
	SingleResponse<FuncGroupVO> queryDetail(FuncGroupQueryDetailCommand funcGroupQueryDetailCommand);

	/**
	 * 列表查询
	 * @param funcGroupQueryListCommand
	 * @return
	 */
	MultiResponse<FuncGroupVO> queryList(FuncGroupQueryListCommand funcGroupQueryListCommand);

	/**
	 * 分页查询
	 * @param funcGroupPageQueryCommand
	 * @return
	 */
	PageResponse<FuncGroupVO> pageQuery(FuncGroupPageQueryCommand funcGroupPageQueryCommand);

}
