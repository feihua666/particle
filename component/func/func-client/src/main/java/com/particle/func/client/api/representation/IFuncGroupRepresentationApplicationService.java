package com.particle.func.client.api.representation;

import com.particle.func.client.dto.command.representation.FuncGroupQueryDetailForUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryDetailCommand;
import com.particle.func.client.dto.command.representation.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 功能组 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
public interface IFuncGroupRepresentationApplicationService extends IBaseApplicationService {

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
