package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.navigation.domain.gateway.NavigationSiteGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航网站 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Component
@Validated
public class NavigationSiteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteGateway navigationSiteGateway;
	private INavigationSiteService iNavigationSiteService;
	/**
	 * 注入使用set方法
	 * @param navigationSiteGateway
	 */
	@Autowired
	public void setNavigationSiteGateway(NavigationSiteGateway navigationSiteGateway) {
		this.navigationSiteGateway = navigationSiteGateway;
	}
	@Autowired
	public void setINavigationSiteService(INavigationSiteService iNavigationSiteService) {
		this.iNavigationSiteService = iNavigationSiteService;
	}
}
