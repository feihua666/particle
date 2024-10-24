package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationSitePageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;

/**
 * <p>
 * 导航网站 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationSiteRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationSiteVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationSiteQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationSiteVO> queryList(NavigationSiteQueryListCommand navigationSiteQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationSitePageQueryCommand
	 * @return
	 */
	PageResponse<NavigationSiteVO> pageQuery(NavigationSitePageQueryCommand navigationSitePageQueryCommand);

}
