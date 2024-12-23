package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.navigation.domain.gateway.NavigationCategoryGateway;
import com.particle.navigation.infrastructure.service.INavigationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航分类 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Component
@Validated
public class NavigationCategoryCommandExecutor  extends AbstractBaseExecutor {

	private NavigationCategoryGateway navigationCategoryGateway;
	private INavigationCategoryService iNavigationCategoryService;
	/**
	 * 注入使用set方法
	 * @param navigationCategoryGateway
	 */
	@Autowired
	public void setNavigationCategoryGateway(NavigationCategoryGateway navigationCategoryGateway) {
		this.navigationCategoryGateway = navigationCategoryGateway;
	}
	@Autowired
	public void setINavigationCategoryService(INavigationCategoryService iNavigationCategoryService) {
		this.iNavigationCategoryService = iNavigationCategoryService;
	}
}
