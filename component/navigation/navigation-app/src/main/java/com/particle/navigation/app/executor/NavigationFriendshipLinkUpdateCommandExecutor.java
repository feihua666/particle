package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationFriendshipLinkAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import com.particle.navigation.domain.NavigationFriendshipLink;
import com.particle.navigation.domain.NavigationFriendshipLinkId;
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
 * 导航友情链接 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationFriendshipLinkUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationFriendshipLinkGateway navigationFriendshipLinkGateway;

	/**
	 * 执行 导航友情链接 更新指令
	 * @param navigationFriendshipLinkUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationFriendshipLinkVO> execute(@Valid NavigationFriendshipLinkUpdateCommand navigationFriendshipLinkUpdateCommand) {
		NavigationFriendshipLink navigationFriendshipLink = createByNavigationFriendshipLinkUpdateCommand(navigationFriendshipLinkUpdateCommand);
		navigationFriendshipLink.setUpdateControl(navigationFriendshipLinkUpdateCommand);
		boolean save = navigationFriendshipLinkGateway.save(navigationFriendshipLink);
		if (save) {
			return SingleResponse.of(NavigationFriendshipLinkAppStructMapping.instance.toNavigationFriendshipLinkVO(navigationFriendshipLink));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航友情链接更新指令创建导航友情链接模型
	 * @param navigationFriendshipLinkUpdateCommand
	 * @return
	 */
	private NavigationFriendshipLink createByNavigationFriendshipLinkUpdateCommand(NavigationFriendshipLinkUpdateCommand navigationFriendshipLinkUpdateCommand){
		NavigationFriendshipLink navigationFriendshipLink = NavigationFriendshipLink.create();
		NavigationFriendshipLinkUpdateCommandToNavigationFriendshipLinkMapping.instance.fillNavigationFriendshipLinkByNavigationFriendshipLinkUpdateCommand(navigationFriendshipLink, navigationFriendshipLinkUpdateCommand);
		return navigationFriendshipLink;
	}

	@Mapper
	interface NavigationFriendshipLinkUpdateCommandToNavigationFriendshipLinkMapping{
		NavigationFriendshipLinkUpdateCommandToNavigationFriendshipLinkMapping instance = Mappers.getMapper(NavigationFriendshipLinkUpdateCommandToNavigationFriendshipLinkMapping.class );

		default NavigationFriendshipLinkId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationFriendshipLinkId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationFriendshipLink
		 * @param navigationFriendshipLinkUpdateCommand
		 */
		void fillNavigationFriendshipLinkByNavigationFriendshipLinkUpdateCommand(@MappingTarget NavigationFriendshipLink navigationFriendshipLink, NavigationFriendshipLinkUpdateCommand navigationFriendshipLinkUpdateCommand);
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
