package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationSiteCategoryRelAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import com.particle.navigation.domain.NavigationSiteCategoryRel;
import com.particle.navigation.domain.gateway.NavigationSiteCategoryRelGateway;
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
 * 导航网站分类关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Component
@Validated
public class NavigationSiteCategoryRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteCategoryRelGateway navigationSiteCategoryRelGateway;

	/**
	 * 执行导航网站分类关系添加指令
	 * @param navigationSiteCategoryRelCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteCategoryRelVO> execute(@Valid NavigationSiteCategoryRelCreateCommand navigationSiteCategoryRelCreateCommand) {
		NavigationSiteCategoryRel navigationSiteCategoryRel = createByNavigationSiteCategoryRelCreateCommand(navigationSiteCategoryRelCreateCommand);
		navigationSiteCategoryRel.setAddControl(navigationSiteCategoryRelCreateCommand);
		boolean save = navigationSiteCategoryRelGateway.save(navigationSiteCategoryRel);
		if (save) {
			return SingleResponse.of(NavigationSiteCategoryRelAppStructMapping.instance.toNavigationSiteCategoryRelVO(navigationSiteCategoryRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站分类关系创建指令创建导航网站分类关系模型
	 * @param navigationSiteCategoryRelCreateCommand
	 * @return
	 */
	private NavigationSiteCategoryRel createByNavigationSiteCategoryRelCreateCommand(NavigationSiteCategoryRelCreateCommand navigationSiteCategoryRelCreateCommand){
		NavigationSiteCategoryRel navigationSiteCategoryRel = NavigationSiteCategoryRel.create();
		NavigationSiteCategoryRelCreateCommandToNavigationSiteCategoryRelMapping.instance.fillNavigationSiteCategoryRelByNavigationSiteCategoryRelCreateCommand(navigationSiteCategoryRel, navigationSiteCategoryRelCreateCommand);
		return navigationSiteCategoryRel;
	}

	@Mapper
	interface  NavigationSiteCategoryRelCreateCommandToNavigationSiteCategoryRelMapping{
		NavigationSiteCategoryRelCreateCommandToNavigationSiteCategoryRelMapping instance = Mappers.getMapper( NavigationSiteCategoryRelCreateCommandToNavigationSiteCategoryRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSiteCategoryRel
		 * @param navigationSiteCategoryRelCreateCommand
		 */
		void fillNavigationSiteCategoryRelByNavigationSiteCategoryRelCreateCommand(@MappingTarget NavigationSiteCategoryRel navigationSiteCategoryRel, NavigationSiteCategoryRelCreateCommand navigationSiteCategoryRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationSiteCategoryRelGateway
	 */
	@Autowired
	public void setNavigationSiteCategoryRelGateway(NavigationSiteCategoryRelGateway navigationSiteCategoryRelGateway) {
		this.navigationSiteCategoryRelGateway = navigationSiteCategoryRelGateway;
	}
}
