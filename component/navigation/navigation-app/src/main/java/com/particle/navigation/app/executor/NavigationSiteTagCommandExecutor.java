package com.particle.navigation.app.executor;

import com.particle.navigation.domain.gateway.NavigationSiteTagGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteTagService;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 导航网站标签 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Component
@Validated
public class NavigationSiteTagCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagGateway navigationSiteTagGateway;
	private INavigationSiteTagService iNavigationSiteTagService;
	/**
	 * 注入使用set方法
	 * @param navigationSiteTagGateway
	 */
	@Autowired
	public void setNavigationSiteTagGateway(NavigationSiteTagGateway navigationSiteTagGateway) {
		this.navigationSiteTagGateway = navigationSiteTagGateway;
	}
	@Autowired
	public void setINavigationSiteTagService(INavigationSiteTagService iNavigationSiteTagService) {
		this.iNavigationSiteTagService = iNavigationSiteTagService;
	}
}
