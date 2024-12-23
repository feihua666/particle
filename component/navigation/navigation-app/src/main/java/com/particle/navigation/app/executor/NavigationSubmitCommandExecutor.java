package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.navigation.client.dto.command.NavigationSiteCreateCommand;
import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.NavigationSubmitId;
import com.particle.navigation.domain.gateway.NavigationSubmitGateway;
import com.particle.navigation.infrastructure.service.INavigationSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航提交 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Component
@Validated
public class NavigationSubmitCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSubmitGateway navigationSubmitGateway;
	private INavigationSubmitService iNavigationSubmitService;

	private NavigationSiteCreateCommandExecutor navigationSiteCreateCommandExecutor;



	/**
	 * 确认提交
	 * @param idCommand
	 * @return
	 */
	public Response sureSubmit(IdCommand idCommand) {
		NavigationSubmit navigationSubmit = navigationSubmitGateway.getById(NavigationSubmitId.of(idCommand.getId()));
		String siteDataJson = navigationSubmit.getSiteDataJson();
		NavigationSiteCreateCommand navigationSiteCreateCommand = NavigationSiteCreateCommand.createFromJson(siteDataJson);
		navigationSiteCreateCommand.setIsPublished(true);
		navigationSiteCreateCommandExecutor.execute(navigationSiteCreateCommand);

		navigationSubmit.changeStatusToSubmitted();
		navigationSubmitGateway.save(navigationSubmit);
		// 修改状态为已提交
		return Response.buildSuccess();
	}


	/**
	 * 注入使用set方法
	 * @param navigationSubmitGateway
	 */
	@Autowired
	public void setNavigationSubmitGateway(NavigationSubmitGateway navigationSubmitGateway) {
		this.navigationSubmitGateway = navigationSubmitGateway;
	}
	@Autowired
	public void setINavigationSubmitService(INavigationSubmitService iNavigationSubmitService) {
		this.iNavigationSubmitService = iNavigationSubmitService;
	}
	@Autowired
	public void setNavigationSiteCreateCommandExecutor(NavigationSiteCreateCommandExecutor navigationSiteCreateCommandExecutor) {
		this.navigationSiteCreateCommandExecutor = navigationSiteCreateCommandExecutor;
	}
}
