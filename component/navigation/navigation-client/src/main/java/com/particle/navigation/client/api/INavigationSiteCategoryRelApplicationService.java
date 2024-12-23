package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.NavigationCategoryAssignNavigationSiteCommand;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationCategoryCommand;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
/**
 * <p>
 * 导航网站分类关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
public interface INavigationSiteCategoryRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationSiteCategoryRelCreateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteCategoryRelVO> create(NavigationSiteCategoryRelCreateCommand navigationSiteCategoryRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationSiteCategoryRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationSiteCategoryRelUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteCategoryRelVO> update(NavigationSiteCategoryRelUpdateCommand navigationSiteCategoryRelUpdateCommand);


	/**
	 * 导航网站分配导航分类
	 * @param cf
	 * @return
	 */
	Response navigationSiteAssignNavigationCategory(NavigationSiteAssignNavigationCategoryCommand cf);

	/**
	 * 导航分类分配导航网站
	 * @param cf
	 * @return
	 */
	Response navigationCategoryAssignNavigationSite(NavigationCategoryAssignNavigationSiteCommand cf);

	/**
	 * 根据导航网站id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByNavigationSiteId(IdCommand idCommand);

	/**
	 * 根据导航分类id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByNavigationCategoryId(IdCommand idCommand);

}
