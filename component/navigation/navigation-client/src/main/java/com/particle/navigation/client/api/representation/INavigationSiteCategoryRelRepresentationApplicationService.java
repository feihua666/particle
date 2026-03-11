package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;

/**
 * <p>
 * 导航网站分类关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationSiteCategoryRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteCategoryRelVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationSiteCategoryRelVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationSiteCategoryRelQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationSiteCategoryRelVO> queryList(NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationSiteCategoryRelPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationSiteCategoryRelVO> pageQuery(NavigationSiteCategoryRelPageQueryCommand navigationSiteCategoryRelPageQueryCommand);

	/**
	 * 查询导航网站已分配的导航分类id
	 * @param navigationSiteCommonIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationCategoryIdsByNavigationSiteId(CommonIdCommand navigationSiteCommonIdCommand);

	/**
	 * 查询导航分类已分配的导航网站id
	 * @param navigationCategoryCommonIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationSiteIdsByNavigationCategoryId(CommonIdCommand navigationCategoryCommonIdCommand);
}
