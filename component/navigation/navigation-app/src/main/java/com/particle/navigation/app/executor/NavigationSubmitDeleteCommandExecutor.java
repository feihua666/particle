package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSubmitAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.NavigationSubmitId;
import com.particle.navigation.domain.gateway.NavigationSubmitGateway;
import com.particle.navigation.infrastructure.service.INavigationSubmitService;
import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 导航提交 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Component
@Validated
public class NavigationSubmitDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSubmitGateway navigationSubmitGateway;
	private INavigationSubmitService iNavigationSubmitService;

	/**
	 * 执行 导航提交 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationSubmitVO> execute(@Valid IdCommand deleteCommand) {
		NavigationSubmitId navigationSubmitId = NavigationSubmitId.of(deleteCommand.getId());
		NavigationSubmit byId = navigationSubmitGateway.getById(navigationSubmitId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationSubmitGateway.delete(navigationSubmitId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationSubmitAppStructMapping.instance.toNavigationSubmitVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
}
