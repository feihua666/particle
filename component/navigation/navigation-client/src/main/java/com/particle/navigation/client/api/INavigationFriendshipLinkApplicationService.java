package com.particle.navigation.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkCreateCommand;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
/**
 * <p>
 * 导航友情链接 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
public interface INavigationFriendshipLinkApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param navigationFriendshipLinkCreateCommand
	 * @return
	 */
	SingleResponse<NavigationFriendshipLinkVO> create(NavigationFriendshipLinkCreateCommand navigationFriendshipLinkCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<NavigationFriendshipLinkVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param navigationFriendshipLinkUpdateCommand
	 * @return
	 */
	SingleResponse<NavigationFriendshipLinkVO> update(NavigationFriendshipLinkUpdateCommand navigationFriendshipLinkUpdateCommand);
}
