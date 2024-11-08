package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;

/**
 * <p>
 * 导航网站标签 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationSiteTagRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationSiteTagQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationSiteTagVO> queryList(NavigationSiteTagQueryListCommand navigationSiteTagQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationSiteTagPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationSiteTagVO> pageQuery(NavigationSiteTagPageQueryCommand navigationSiteTagPageQueryCommand);

}
