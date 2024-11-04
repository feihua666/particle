package com.particle.navigation.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;

/**
 * <p>
 * 导航友情链接 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface INavigationFriendshipLinkRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationFriendshipLinkVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<NavigationFriendshipLinkVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param navigationFriendshipLinkQueryListCommand
	 * @return
	 */
	MultiResponse<NavigationFriendshipLinkVO> queryList(NavigationFriendshipLinkQueryListCommand navigationFriendshipLinkQueryListCommand);

	/**
	 * 分页查询
	 * @param navigationFriendshipLinkPageQueryCommand
	 * @return
	 */
	PageResponse<NavigationFriendshipLinkVO> pageQuery(NavigationFriendshipLinkPageQueryCommand navigationFriendshipLinkPageQueryCommand);

}
