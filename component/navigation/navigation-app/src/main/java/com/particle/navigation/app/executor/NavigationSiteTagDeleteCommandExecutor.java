package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteTagAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import com.particle.navigation.domain.NavigationSiteTag;
import com.particle.navigation.domain.NavigationSiteTagId;
import com.particle.navigation.domain.gateway.NavigationSiteTagGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteTagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航网站标签 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Component
@Validated
public class NavigationSiteTagDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagGateway navigationSiteTagGateway;
	private INavigationSiteTagService iNavigationSiteTagService;

	/**
	 * 执行 导航网站标签 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagVO> execute(@Valid IdCommand deleteCommand) {
		NavigationSiteTagId navigationSiteTagId = NavigationSiteTagId.of(deleteCommand.getId());
		NavigationSiteTag byId = navigationSiteTagGateway.getById(navigationSiteTagId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationSiteTagGateway.delete(navigationSiteTagId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationSiteTagAppStructMapping.instance.toNavigationSiteTagVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
