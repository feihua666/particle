package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationSiteTagCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagAssignNavigationSiteCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
/**
 * <p>
 * 导航网站标签关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
public interface INavigationSiteTagRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationSiteTagRelCreateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagRelVO> create(NavigationSiteTagRelCreateCommand navigationSiteTagRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationSiteTagRelUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationSiteTagRelVO> update(NavigationSiteTagRelUpdateCommand navigationSiteTagRelUpdateCommand);


	/**
	 * 网站分配网站标签
	 * @param cf
	 * @return
	 */
	Response navigationSiteAssignNavigationSiteTag(NavigationSiteAssignNavigationSiteTagCommand cf);

	/**
	 * 网站标签分配网站
	 * @param cf
	 * @return
	 */
	Response navigationSiteTagAssignNavigationSite(NavigationSiteTagAssignNavigationSiteCommand cf);

	/**
	 * 根据网站id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByNavigationSiteId(IdCommand idCommand);

	/**
	 * 根据网站标签id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByNavigationSiteTagId(IdCommand idCommand);

}
