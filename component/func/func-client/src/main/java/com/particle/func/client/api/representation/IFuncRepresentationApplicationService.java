package com.particle.func.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

import java.util.List;

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
	SingleResponse<FuncVO> queryDetailForUpdate(IdCommand funcQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param funcQueryDetailCommand
	 * @return
	 */
	SingleResponse<FuncVO> queryDetail(IdCommand funcQueryDetailCommand);

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


	/**
	 * 根据id获取，主要用于登录用户获取自己的功能使用
	 * @param ids
	 * @param isDisabled 禁用状态，为null不考虑
	 * @return
	 */
	MultiResponse<FuncVO> queryListByIds(List<Long> ids, Boolean isDisabled);

}
