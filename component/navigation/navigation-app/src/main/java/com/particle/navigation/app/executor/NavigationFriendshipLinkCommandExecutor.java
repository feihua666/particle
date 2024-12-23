package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.navigation.domain.gateway.NavigationFriendshipLinkGateway;
import com.particle.navigation.infrastructure.service.INavigationFriendshipLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航友情链接 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Component
@Validated
public class NavigationFriendshipLinkCommandExecutor  extends AbstractBaseExecutor {

	private NavigationFriendshipLinkGateway navigationFriendshipLinkGateway;
	private INavigationFriendshipLinkService iNavigationFriendshipLinkService;
	/**
	 * 注入使用set方法
	 * @param navigationFriendshipLinkGateway
	 */
	@Autowired
	public void setNavigationFriendshipLinkGateway(NavigationFriendshipLinkGateway navigationFriendshipLinkGateway) {
		this.navigationFriendshipLinkGateway = navigationFriendshipLinkGateway;
	}
	@Autowired
	public void setINavigationFriendshipLinkService(INavigationFriendshipLinkService iNavigationFriendshipLinkService) {
		this.iNavigationFriendshipLinkService = iNavigationFriendshipLinkService;
	}
}
