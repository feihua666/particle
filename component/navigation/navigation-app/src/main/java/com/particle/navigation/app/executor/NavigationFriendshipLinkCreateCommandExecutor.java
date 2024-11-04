package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationFriendshipLinkAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkCreateCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import com.particle.navigation.domain.NavigationFriendshipLink;
import com.particle.navigation.domain.gateway.NavigationFriendshipLinkGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class NavigationFriendshipLinkCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationFriendshipLinkGateway navigationFriendshipLinkGateway;

	/**
	 * 执行导航友情链接添加指令
	 * @param navigationFriendshipLinkCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationFriendshipLinkVO> execute(@Valid NavigationFriendshipLinkCreateCommand navigationFriendshipLinkCreateCommand) {
		NavigationFriendshipLink navigationFriendshipLink = createByNavigationFriendshipLinkCreateCommand(navigationFriendshipLinkCreateCommand);
		navigationFriendshipLink.setAddControl(navigationFriendshipLinkCreateCommand);
		navigationFriendshipLink.initForAdd();

		boolean save = navigationFriendshipLinkGateway.save(navigationFriendshipLink);
		if (save) {
			return SingleResponse.of(NavigationFriendshipLinkAppStructMapping.instance.toNavigationFriendshipLinkVO(navigationFriendshipLink));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航友情链接创建指令创建导航友情链接模型
	 * @param navigationFriendshipLinkCreateCommand
	 * @return
	 */
	private NavigationFriendshipLink createByNavigationFriendshipLinkCreateCommand(NavigationFriendshipLinkCreateCommand navigationFriendshipLinkCreateCommand){
		NavigationFriendshipLink navigationFriendshipLink = NavigationFriendshipLink.create();
		NavigationFriendshipLinkCreateCommandToNavigationFriendshipLinkMapping.instance.fillNavigationFriendshipLinkByNavigationFriendshipLinkCreateCommand(navigationFriendshipLink, navigationFriendshipLinkCreateCommand);
		return navigationFriendshipLink;
	}

	@Mapper
	interface  NavigationFriendshipLinkCreateCommandToNavigationFriendshipLinkMapping{
		NavigationFriendshipLinkCreateCommandToNavigationFriendshipLinkMapping instance = Mappers.getMapper( NavigationFriendshipLinkCreateCommandToNavigationFriendshipLinkMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationFriendshipLink
		 * @param navigationFriendshipLinkCreateCommand
		 */
		void fillNavigationFriendshipLinkByNavigationFriendshipLinkCreateCommand(@MappingTarget NavigationFriendshipLink navigationFriendshipLink, NavigationFriendshipLinkCreateCommand navigationFriendshipLinkCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationFriendshipLinkGateway
	 */
	@Autowired
	public void setNavigationFriendshipLinkGateway(NavigationFriendshipLinkGateway navigationFriendshipLinkGateway) {
		this.navigationFriendshipLinkGateway = navigationFriendshipLinkGateway;
	}
}
