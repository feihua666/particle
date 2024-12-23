package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteTagRelAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import com.particle.navigation.domain.NavigationSiteTagRel;
import com.particle.navigation.domain.NavigationSiteTagRelId;
import com.particle.navigation.domain.gateway.NavigationSiteTagRelGateway;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航网站标签关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Component
@Validated
public class NavigationSiteTagRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagRelGateway navigationSiteTagRelGateway;
	private INavigationSiteTagRelService iNavigationSiteTagRelService;

	/**
	 * 执行 导航网站标签关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagRelVO> execute(@Valid IdCommand deleteCommand) {
		NavigationSiteTagRelId navigationSiteTagRelId = NavigationSiteTagRelId.of(deleteCommand.getId());
		NavigationSiteTagRel byId = navigationSiteTagRelGateway.getById(navigationSiteTagRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationSiteTagRelGateway.delete(navigationSiteTagRelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationSiteTagRelAppStructMapping.instance.toNavigationSiteTagRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 根据 navigationSiteId 删除
	 * @param navigationSiteIdCommand
	 * @return
	 */
	public Response deleteByNavigationSiteId(@Valid IdCommand navigationSiteIdCommand) {
		boolean result = iNavigationSiteTagRelService.deleteByColumn(navigationSiteIdCommand.getId(), NavigationSiteTagRelDO::getNavigationSiteId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 navigationSiteTagId 删除
	 * @param navigationSiteTagIdCommand
	 * @return
	 */
	public Response deleteByNavigationSiteTagId(@Valid IdCommand navigationSiteTagIdCommand) {
		boolean result = iNavigationSiteTagRelService.deleteByColumn(navigationSiteTagIdCommand.getId(), NavigationSiteTagRelDO::getNavigationSiteTagId);
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
