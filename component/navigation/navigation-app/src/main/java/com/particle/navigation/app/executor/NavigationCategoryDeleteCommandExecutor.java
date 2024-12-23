package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationCategoryAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import com.particle.navigation.domain.NavigationCategory;
import com.particle.navigation.domain.NavigationCategoryId;
import com.particle.navigation.domain.gateway.NavigationCategoryGateway;
import com.particle.navigation.infrastructure.service.INavigationCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 导航分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Component
@Validated
public class NavigationCategoryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationCategoryGateway navigationCategoryGateway;
	private INavigationCategoryService iNavigationCategoryService;

	/**
	 * 执行 导航分类 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationCategoryVO> execute(@Valid IdCommand deleteCommand) {
		NavigationCategoryId navigationCategoryId = NavigationCategoryId.of(deleteCommand.getId());
		NavigationCategory byId = navigationCategoryGateway.getById(navigationCategoryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationCategoryGateway.delete(navigationCategoryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationCategoryAppStructMapping.instance.toNavigationCategoryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
