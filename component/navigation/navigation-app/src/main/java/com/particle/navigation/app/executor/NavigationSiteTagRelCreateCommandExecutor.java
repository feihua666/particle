package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteTagRelAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import com.particle.navigation.domain.NavigationSiteTagRel;
import com.particle.navigation.domain.gateway.NavigationSiteTagRelGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
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
public class NavigationSiteTagRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagRelGateway navigationSiteTagRelGateway;

	/**
	 * 执行导航网站标签关系添加指令
	 * @param navigationSiteTagRelCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagRelVO> execute(@Valid NavigationSiteTagRelCreateCommand navigationSiteTagRelCreateCommand) {
		NavigationSiteTagRel navigationSiteTagRel = createByNavigationSiteTagRelCreateCommand(navigationSiteTagRelCreateCommand);
		navigationSiteTagRel.setAddControl(navigationSiteTagRelCreateCommand);
		boolean save = navigationSiteTagRelGateway.save(navigationSiteTagRel);
		if (save) {
			return SingleResponse.of(NavigationSiteTagRelAppStructMapping.instance.toNavigationSiteTagRelVO(navigationSiteTagRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站标签关系创建指令创建导航网站标签关系模型
	 * @param navigationSiteTagRelCreateCommand
	 * @return
	 */
	private NavigationSiteTagRel createByNavigationSiteTagRelCreateCommand(NavigationSiteTagRelCreateCommand navigationSiteTagRelCreateCommand){
		NavigationSiteTagRel navigationSiteTagRel = NavigationSiteTagRel.create();
		NavigationSiteTagRelCreateCommandToNavigationSiteTagRelMapping.instance.fillNavigationSiteTagRelByNavigationSiteTagRelCreateCommand(navigationSiteTagRel, navigationSiteTagRelCreateCommand);
		return navigationSiteTagRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  NavigationSiteTagRelCreateCommandToNavigationSiteTagRelMapping{
		NavigationSiteTagRelCreateCommandToNavigationSiteTagRelMapping instance = Mappers.getMapper( NavigationSiteTagRelCreateCommandToNavigationSiteTagRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSiteTagRel
		 * @param navigationSiteTagRelCreateCommand
		 */
		void fillNavigationSiteTagRelByNavigationSiteTagRelCreateCommand(@MappingTarget NavigationSiteTagRel navigationSiteTagRel, NavigationSiteTagRelCreateCommand navigationSiteTagRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationSiteTagRelGateway
	 */
	@Autowired
	public void setNavigationSiteTagRelGateway(NavigationSiteTagRelGateway navigationSiteTagRelGateway) {
		this.navigationSiteTagRelGateway = navigationSiteTagRelGateway;
	}
}
