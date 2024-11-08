package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.navigation.client.dto.command.NavigationSiteTagCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
/**
 * <p>
 * 导航网站标签 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
public interface INavigationSiteTagApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationSiteTagCreateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagVO> create(NavigationSiteTagCreateCommand navigationSiteTagCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationSiteTagUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagVO> update(NavigationSiteTagUpdateCommand navigationSiteTagUpdateCommand);
}
