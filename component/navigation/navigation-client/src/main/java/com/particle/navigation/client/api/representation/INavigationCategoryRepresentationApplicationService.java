package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;

/**
 * <p>
 * 导航分类 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationCategoryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationCategoryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationCategoryQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationCategoryVO> queryList(NavigationCategoryQueryListCommand navigationCategoryQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationCategoryPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationCategoryVO> pageQuery(NavigationCategoryPageQueryCommand navigationCategoryPageQueryCommand);

}
