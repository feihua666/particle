package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationSiteAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import com.particle.navigation.domain.NavigationSite;
import com.particle.navigation.domain.gateway.NavigationSiteGateway;
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
 * 导航网站 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Component
@Validated
public class NavigationSiteCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteGateway navigationSiteGateway;

	/**
	 * 执行导航网站添加指令
	 * @param navigationSiteCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteVO> execute(@Valid NavigationSiteCreateCommand navigationSiteCreateCommand) {
		NavigationSite navigationSite = createByNavigationSiteCreateCommand(navigationSiteCreateCommand);
		navigationSite.setAddControl(navigationSiteCreateCommand);
		navigationSite.initForAdd();
		boolean save = navigationSiteGateway.save(navigationSite);
		if (save) {
			return SingleResponse.of(NavigationSiteAppStructMapping.instance.toNavigationSiteVO(navigationSite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站创建指令创建导航网站模型
	 * @param navigationSiteCreateCommand
	 * @return
	 */
	private NavigationSite createByNavigationSiteCreateCommand(NavigationSiteCreateCommand navigationSiteCreateCommand){
		NavigationSite navigationSite = NavigationSite.create();
		NavigationSiteCreateCommandToNavigationSiteMapping.instance.fillNavigationSiteByNavigationSiteCreateCommand(navigationSite, navigationSiteCreateCommand);
		return navigationSite;
	}

	@Mapper
	interface  NavigationSiteCreateCommandToNavigationSiteMapping{
		NavigationSiteCreateCommandToNavigationSiteMapping instance = Mappers.getMapper( NavigationSiteCreateCommandToNavigationSiteMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSite
		 * @param navigationSiteCreateCommand
		 */
		void fillNavigationSiteByNavigationSiteCreateCommand(@MappingTarget NavigationSite navigationSite, NavigationSiteCreateCommand navigationSiteCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationSiteGateway
	 */
	@Autowired
	public void setNavigationSiteGateway(NavigationSiteGateway navigationSiteGateway) {
		this.navigationSiteGateway = navigationSiteGateway;
	}
}
