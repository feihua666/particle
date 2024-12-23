package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationSiteTagCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagAssignNavigationSiteCommand;
import com.particle.navigation.domain.gateway.NavigationSiteTagRelGateway;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航网站标签关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Component
@Validated
public class NavigationSiteTagRelCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagRelGateway navigationSiteTagRelGateway;
	private INavigationSiteTagRelService iNavigationSiteTagRelService;
	/**
	 * 网站分配网站标签
	 * @param navigationSiteAssignNavigationSiteTagCommand
	 * @return
	 */
	public Response navigationSiteAssignNavigationSiteTag(@Valid NavigationSiteAssignNavigationSiteTagCommand navigationSiteAssignNavigationSiteTagCommand) {
		boolean result = iNavigationSiteTagRelService.removeAndAssignRel(navigationSiteAssignNavigationSiteTagCommand.getNavigationSiteId(),
				navigationSiteAssignNavigationSiteTagCommand.getCheckedNavigationSiteTagIds(),navigationSiteAssignNavigationSiteTagCommand.getUncheckedNavigationSiteTagIds(),
				navigationSiteAssignNavigationSiteTagCommand.getIsLazyLoad(), NavigationSiteTagRelDO::getNavigationSiteId,NavigationSiteTagRelDO::getNavigationSiteTagId,
				(relDto)->new NavigationSiteTagRelDO().setNavigationSiteId(relDto.getMainId()).setNavigationSiteTagId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 网站标签分配网站
	 * @param navigationSiteTagAssignNavigationSiteCommand
	 * @return
	 */
	public Response navigationSiteTagAssignNavigationSite(@Valid NavigationSiteTagAssignNavigationSiteCommand navigationSiteTagAssignNavigationSiteCommand) {
		boolean result = iNavigationSiteTagRelService.removeAndAssignRel(navigationSiteTagAssignNavigationSiteCommand.getNavigationSiteTagId(),
				navigationSiteTagAssignNavigationSiteCommand.getCheckedNavigationSiteIds(),navigationSiteTagAssignNavigationSiteCommand.getUncheckedNavigationSiteIds(),
				navigationSiteTagAssignNavigationSiteCommand.getIsLazyLoad(), NavigationSiteTagRelDO::getNavigationSiteTagId,NavigationSiteTagRelDO::getNavigationSiteId,
				(relDto)->new NavigationSiteTagRelDO().setNavigationSiteTagId(relDto.getMainId()).setNavigationSiteId(relDto.getOtherId()));
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param navigationSiteTagRelGateway
	 */
	@Autowired
	public void setNavigationSiteTagRelGateway(NavigationSiteTagRelGateway navigationSiteTagRelGateway) {
		this.navigationSiteTagRelGateway = navigationSiteTagRelGateway;
	}
	@Autowired
	public void setINavigationSiteTagRelService(INavigationSiteTagRelService iNavigationSiteTagRelService) {
		this.iNavigationSiteTagRelService = iNavigationSiteTagRelService;
	}
}
