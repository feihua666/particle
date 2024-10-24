package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.navigation.client.dto.command.NavigationSiteCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
/**
 * <p>
 * 导航网站 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
public interface INavigationSiteApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationSiteCreateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteVO> create(NavigationSiteCreateCommand navigationSiteCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationSiteVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationSiteUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteVO> update(NavigationSiteUpdateCommand navigationSiteUpdateCommand);
}
