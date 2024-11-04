package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationFriendshipLinkAppStructMapping;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import com.particle.navigation.domain.NavigationFriendshipLink;
import com.particle.navigation.domain.NavigationFriendshipLinkId;
import com.particle.navigation.domain.gateway.NavigationFriendshipLinkGateway;
import com.particle.navigation.infrastructure.service.INavigationFriendshipLinkService;
import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 导航友情链接 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Component
@Validated
public class NavigationFriendshipLinkDeleteCommandExecutor  extends AbstractBaseExecutor {

	private NavigationFriendshipLinkGateway navigationFriendshipLinkGateway;
	private INavigationFriendshipLinkService iNavigationFriendshipLinkService;

	/**
	 * 执行 导航友情链接 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<NavigationFriendshipLinkVO> execute(@Valid IdCommand deleteCommand) {
		NavigationFriendshipLinkId navigationFriendshipLinkId = NavigationFriendshipLinkId.of(deleteCommand.getId());
		NavigationFriendshipLink byId = navigationFriendshipLinkGateway.getById(navigationFriendshipLinkId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = navigationFriendshipLinkGateway.delete(navigationFriendshipLinkId,deleteCommand);
		if (delete) {
			return SingleResponse.of(NavigationFriendshipLinkAppStructMapping.instance.toNavigationFriendshipLinkVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param navigationFriendshipLinkGateway
	 */
	@Autowired
	public void setNavigationFriendshipLinkGateway(NavigationFriendshipLinkGateway navigationFriendshipLinkGateway) {
		this.navigationFriendshipLinkGateway = navigationFriendshipLinkGateway;
	}
	@Autowired
	public void setINavigationFriendshipLinkService(INavigationFriendshipLinkService iNavigationFriendshipLinkService) {
		this.iNavigationFriendshipLinkService = iNavigationFriendshipLinkService;
	}
}
