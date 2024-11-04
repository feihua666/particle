package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;

/**
 * <p>
 * 导航提交 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationSubmitRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSubmitVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationSubmitVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationSubmitQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationSubmitVO> queryList(NavigationSubmitQueryListCommand navigationSubmitQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationSubmitPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationSubmitVO> pageQuery(NavigationSubmitPageQueryCommand navigationSubmitPageQueryCommand);

}
