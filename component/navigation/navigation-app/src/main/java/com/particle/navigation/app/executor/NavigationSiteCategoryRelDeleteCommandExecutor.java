package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteCategoryRelAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import com.particle.navigation.domain.NavigationSiteCategoryRel;
import com.particle.navigation.domain.NavigationSiteCategoryRelId;
import com.particle.navigation.domain.gateway.NavigationSiteCategoryRelGateway;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航网站分类关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Component
@Validated
public class NavigationSiteCategoryRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteCategoryRelGateway navigationSiteCategoryRelGateway;
	private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;

	/**
	 * 执行 导航网站分类关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteCategoryRelVO> execute(@Valid IdCommand deleteCommand) {
		NavigationSiteCategoryRelId navigationSiteCategoryRelId = NavigationSiteCategoryRelId.of(deleteCommand.getId());
		NavigationSiteCategoryRel byId = navigationSiteCategoryRelGateway.getById(navigationSiteCategoryRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationSiteCategoryRelGateway.delete(navigationSiteCategoryRelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationSiteCategoryRelAppStructMapping.instance.toNavigationSiteCategoryRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 根据 navigationSiteId 删除
	 * @param navigationSiteIdCommand
	 * @return
	 */
	public Response deleteByNavigationSiteId(@Valid IdCommand navigationSiteIdCommand) {
		boolean result = iNavigationSiteCategoryRelService.deleteByColumn(navigationSiteIdCommand.getId(), NavigationSiteCategoryRelDO::getNavigationSiteId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 navigationCategoryId 删除
	 * @param navigationCategoryIdCommand
	 * @return
	 */
	public Response deleteByNavigationCategoryId(@Valid IdCommand navigationCategoryIdCommand) {
		boolean result = iNavigationSiteCategoryRelService.deleteByColumn(navigationCategoryIdCommand.getId(), NavigationSiteCategoryRelDO::getNavigationCategoryId);
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
