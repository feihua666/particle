package com.particle.navigation.app.executor;

import com.particle.navigation.domain.gateway.NavigationStaticDeployGateway;
import com.particle.navigation.infrastructure.service.INavigationStaticDeployService;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 导航网站静态部署 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Component
@Validated
public class NavigationStaticDeployCommandExecutor  extends AbstractBaseExecutor {

	private NavigationStaticDeployGateway navigationStaticDeployGateway;
	private INavigationStaticDeployService iNavigationStaticDeployService;
	/**
	 * 注入使用set方法
	 * @param navigationStaticDeployGateway
	 */
	@Autowired
	public void setNavigationStaticDeployGateway(NavigationStaticDeployGateway navigationStaticDeployGateway) {
		this.navigationStaticDeployGateway = navigationStaticDeployGateway;
	}
	@Autowired
	public void setINavigationStaticDeployService(INavigationStaticDeployService iNavigationStaticDeployService) {
		this.iNavigationStaticDeployService = iNavigationStaticDeployService;
	}
}
