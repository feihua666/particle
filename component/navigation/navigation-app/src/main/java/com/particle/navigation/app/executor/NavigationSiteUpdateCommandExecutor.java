package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationSiteAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import com.particle.navigation.domain.NavigationSite;
import com.particle.navigation.domain.NavigationSiteId;
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
 * 导航网站 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationSiteUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteGateway navigationSiteGateway;

	/**
	 * 执行 导航网站 更新指令
	 * @param navigationSiteUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteVO> execute(@Valid NavigationSiteUpdateCommand navigationSiteUpdateCommand) {
		NavigationSite navigationSite = createByNavigationSiteUpdateCommand(navigationSiteUpdateCommand);
		navigationSite.setUpdateControl(navigationSiteUpdateCommand);
		boolean save = navigationSiteGateway.save(navigationSite);
		if (save) {
			return SingleResponse.of(NavigationSiteAppStructMapping.instance.toNavigationSiteVO(navigationSite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站更新指令创建导航网站模型
	 * @param navigationSiteUpdateCommand
	 * @return
	 */
	private NavigationSite createByNavigationSiteUpdateCommand(NavigationSiteUpdateCommand navigationSiteUpdateCommand){
		NavigationSite navigationSite = NavigationSite.create();
		NavigationSiteUpdateCommandToNavigationSiteMapping.instance.fillNavigationSiteByNavigationSiteUpdateCommand(navigationSite, navigationSiteUpdateCommand);
		return navigationSite;
	}

	@Mapper
	interface NavigationSiteUpdateCommandToNavigationSiteMapping{
		NavigationSiteUpdateCommandToNavigationSiteMapping instance = Mappers.getMapper(NavigationSiteUpdateCommandToNavigationSiteMapping.class );

		default NavigationSiteId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationSiteId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSite
		 * @param navigationSiteUpdateCommand
		 */
		void fillNavigationSiteByNavigationSiteUpdateCommand(@MappingTarget NavigationSite navigationSite, NavigationSiteUpdateCommand navigationSiteUpdateCommand);
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
