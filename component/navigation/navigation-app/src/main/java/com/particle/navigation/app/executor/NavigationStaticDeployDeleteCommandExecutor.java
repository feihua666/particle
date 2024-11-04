package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationStaticDeployAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import com.particle.navigation.domain.NavigationStaticDeploy;
import com.particle.navigation.domain.NavigationStaticDeployId;
import com.particle.navigation.domain.gateway.NavigationStaticDeployGateway;
import com.particle.navigation.infrastructure.service.INavigationStaticDeployService;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 导航网站静态部署 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Component
@Validated
public class NavigationStaticDeployDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationStaticDeployGateway navigationStaticDeployGateway;
	private INavigationStaticDeployService iNavigationStaticDeployService;

	/**
	 * 执行 导航网站静态部署 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationStaticDeployVO> execute(@Valid IdCommand deleteCommand) {
		NavigationStaticDeployId navigationStaticDeployId = NavigationStaticDeployId.of(deleteCommand.getId());
		NavigationStaticDeploy byId = navigationStaticDeployGateway.getById(navigationStaticDeployId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationStaticDeployGateway.delete(navigationStaticDeployId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationStaticDeployAppStructMapping.instance.toNavigationStaticDeployVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
