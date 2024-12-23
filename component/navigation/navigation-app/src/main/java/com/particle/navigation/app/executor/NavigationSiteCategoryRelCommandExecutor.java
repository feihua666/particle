package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.navigation.client.dto.command.NavigationCategoryAssignNavigationSiteCommand;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationCategoryCommand;
import com.particle.navigation.domain.gateway.NavigationSiteCategoryRelGateway;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航网站分类关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Component
@Validated
public class NavigationSiteCategoryRelCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteCategoryRelGateway navigationSiteCategoryRelGateway;
	private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;
	/**
	 * 导航网站分配导航分类
	 * @param navigationSiteAssignNavigationCategoryCommand
	 * @return
	 */
	public Response navigationSiteAssignNavigationCategory(@Valid NavigationSiteAssignNavigationCategoryCommand navigationSiteAssignNavigationCategoryCommand) {
		boolean result = iNavigationSiteCategoryRelService.removeAndAssignRel(navigationSiteAssignNavigationCategoryCommand.getNavigationSiteId(),
				navigationSiteAssignNavigationCategoryCommand.getCheckedNavigationCategoryIds(),navigationSiteAssignNavigationCategoryCommand.getUncheckedNavigationCategoryIds(),
				navigationSiteAssignNavigationCategoryCommand.getIsLazyLoad(), NavigationSiteCategoryRelDO::getNavigationSiteId,NavigationSiteCategoryRelDO::getNavigationCategoryId,
				(relDto)->new NavigationSiteCategoryRelDO().setNavigationSiteId(relDto.getMainId()).setNavigationCategoryId(relDto.getOtherId()).setSeq(navigationSiteAssignNavigationCategoryCommand.getSeq()));
		return Response.buildSuccess();
	}

	/**
	 * 导航分类分配导航网站
	 * @param navigationCategoryAssignNavigationSiteCommand
	 * @return
	 */
	public Response navigationCategoryAssignNavigationSite(@Valid NavigationCategoryAssignNavigationSiteCommand navigationCategoryAssignNavigationSiteCommand) {
		boolean result = iNavigationSiteCategoryRelService.removeAndAssignRel(navigationCategoryAssignNavigationSiteCommand.getNavigationCategoryId(),
				navigationCategoryAssignNavigationSiteCommand.getCheckedNavigationSiteIds(),navigationCategoryAssignNavigationSiteCommand.getUncheckedNavigationSiteIds(),
				navigationCategoryAssignNavigationSiteCommand.getIsLazyLoad(), NavigationSiteCategoryRelDO::getNavigationCategoryId,NavigationSiteCategoryRelDO::getNavigationSiteId,
				(relDto)->new NavigationSiteCategoryRelDO().setNavigationCategoryId(relDto.getMainId()).setNavigationSiteId(relDto.getOtherId()).setSeq(navigationCategoryAssignNavigationSiteCommand.getSeq()));
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param navigationSiteCategoryRelGateway
	 */
	@Autowired
	public void setNavigationSiteCategoryRelGateway(NavigationSiteCategoryRelGateway navigationSiteCategoryRelGateway) {
		this.navigationSiteCategoryRelGateway = navigationSiteCategoryRelGateway;
	}
	@Autowired
	public void setINavigationSiteCategoryRelService(INavigationSiteCategoryRelService iNavigationSiteCategoryRelService) {
		this.iNavigationSiteCategoryRelService = iNavigationSiteCategoryRelService;
	}
}
