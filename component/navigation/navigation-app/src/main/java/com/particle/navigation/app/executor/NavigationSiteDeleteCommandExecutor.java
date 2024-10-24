package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import com.particle.navigation.domain.NavigationSite;
import com.particle.navigation.domain.NavigationSiteId;
import com.particle.navigation.domain.gateway.NavigationSiteGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteService;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 导航网站 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Component
@Validated
public class NavigationSiteDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteGateway navigationSiteGateway;
	private INavigationSiteService iNavigationSiteService;

	/**
	 * 执行 导航网站 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteVO> execute(@Valid IdCommand deleteCommand) {
		NavigationSiteId navigationSiteId = NavigationSiteId.of(deleteCommand.getId());
		NavigationSite byId = navigationSiteGateway.getById(navigationSiteId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationSiteGateway.delete(navigationSiteId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationSiteAppStructMapping.instance.toNavigationSiteVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
