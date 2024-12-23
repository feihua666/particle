package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteTagAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteTagCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import com.particle.navigation.domain.NavigationSiteTag;
import com.particle.navigation.domain.gateway.NavigationSiteTagGateway;
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
 * 导航网站标签 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Component
@Validated
public class NavigationSiteTagCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagGateway navigationSiteTagGateway;

	/**
	 * 执行导航网站标签添加指令
	 * @param navigationSiteTagCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagVO> execute(@Valid NavigationSiteTagCreateCommand navigationSiteTagCreateCommand) {
		NavigationSiteTag navigationSiteTag = createByNavigationSiteTagCreateCommand(navigationSiteTagCreateCommand);
		navigationSiteTag.setAddControl(navigationSiteTagCreateCommand);
		boolean save = navigationSiteTagGateway.save(navigationSiteTag);
		if (save) {
			return SingleResponse.of(NavigationSiteTagAppStructMapping.instance.toNavigationSiteTagVO(navigationSiteTag));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站标签创建指令创建导航网站标签模型
	 * @param navigationSiteTagCreateCommand
	 * @return
	 */
	private NavigationSiteTag createByNavigationSiteTagCreateCommand(NavigationSiteTagCreateCommand navigationSiteTagCreateCommand){
		NavigationSiteTag navigationSiteTag = NavigationSiteTag.create();
		NavigationSiteTagCreateCommandToNavigationSiteTagMapping.instance.fillNavigationSiteTagByNavigationSiteTagCreateCommand(navigationSiteTag, navigationSiteTagCreateCommand);
		return navigationSiteTag;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  NavigationSiteTagCreateCommandToNavigationSiteTagMapping{
		NavigationSiteTagCreateCommandToNavigationSiteTagMapping instance = Mappers.getMapper( NavigationSiteTagCreateCommandToNavigationSiteTagMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSiteTag
		 * @param navigationSiteTagCreateCommand
		 */
		void fillNavigationSiteTagByNavigationSiteTagCreateCommand(@MappingTarget NavigationSiteTag navigationSiteTag, NavigationSiteTagCreateCommand navigationSiteTagCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationSiteTagGateway
	 */
	@Autowired
	public void setNavigationSiteTagGateway(NavigationSiteTagGateway navigationSiteTagGateway) {
		this.navigationSiteTagGateway = navigationSiteTagGateway;
	}
}
