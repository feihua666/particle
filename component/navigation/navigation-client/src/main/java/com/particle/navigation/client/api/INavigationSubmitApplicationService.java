package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.NavigationSubmitCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSubmitUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
/**
 * <p>
 * 导航提交 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
public interface INavigationSubmitApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationSubmitCreateCommand
	 * @return
	 */
	SingleResponse<NavigationSubmitVO> create(NavigationSubmitCreateCommand navigationSubmitCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationSubmitVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationSubmitUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSubmitVO> update(NavigationSubmitUpdateCommand navigationSubmitUpdateCommand);

	/**
	 * 确认提交
	 * @param idCommand
	 * @return
	 */
	Response sureSubmit(IdCommand idCommand);
}
