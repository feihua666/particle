package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;

/**
 * <p>
 * 导航网站标签关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationSiteTagRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationSiteTagRelQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationSiteTagRelVO> queryList(NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationSiteTagRelPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationSiteTagRelVO> pageQuery(NavigationSiteTagRelPageQueryCommand navigationSiteTagRelPageQueryCommand);

	/**
	 * 查询网站已分配的网站标签id
	 * @param navigationSiteIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationSiteTagIdsByNavigationSiteId(IdCommand navigationSiteIdCommand);

	/**
	 * 查询网站标签已分配的网站id
	 * @param navigationSiteTagIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryNavigationSiteIdsByNavigationSiteTagId(IdCommand navigationSiteTagIdCommand);
}
