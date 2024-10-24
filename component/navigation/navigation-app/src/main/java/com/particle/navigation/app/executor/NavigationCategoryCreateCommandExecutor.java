package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationCategoryAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationCategoryCreateCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import com.particle.navigation.domain.NavigationCategory;
import com.particle.navigation.domain.gateway.NavigationCategoryGateway;
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
 * 导航分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Component
@Validated
public class NavigationCategoryCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationCategoryGateway navigationCategoryGateway;

	/**
	 * 执行导航分类添加指令
	 * @param navigationCategoryCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationCategoryVO> execute(@Valid NavigationCategoryCreateCommand navigationCategoryCreateCommand) {
		NavigationCategory navigationCategory = createByNavigationCategoryCreateCommand(navigationCategoryCreateCommand);
		navigationCategory.setAddControl(navigationCategoryCreateCommand);
		boolean save = navigationCategoryGateway.save(navigationCategory);
		if (save) {
			return SingleResponse.of(NavigationCategoryAppStructMapping.instance.toNavigationCategoryVO(navigationCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航分类创建指令创建导航分类模型
	 * @param navigationCategoryCreateCommand
	 * @return
	 */
	private NavigationCategory createByNavigationCategoryCreateCommand(NavigationCategoryCreateCommand navigationCategoryCreateCommand){
		NavigationCategory navigationCategory = NavigationCategory.create();
		NavigationCategoryCreateCommandToNavigationCategoryMapping.instance.fillNavigationCategoryByNavigationCategoryCreateCommand(navigationCategory, navigationCategoryCreateCommand);
		return navigationCategory;
	}

	@Mapper
	interface  NavigationCategoryCreateCommandToNavigationCategoryMapping{
		NavigationCategoryCreateCommandToNavigationCategoryMapping instance = Mappers.getMapper( NavigationCategoryCreateCommandToNavigationCategoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationCategory
		 * @param navigationCategoryCreateCommand
		 */
		void fillNavigationCategoryByNavigationCategoryCreateCommand(@MappingTarget NavigationCategory navigationCategory, NavigationCategoryCreateCommand navigationCategoryCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationCategoryGateway
	 */
	@Autowired
	public void setNavigationCategoryGateway(NavigationCategoryGateway navigationCategoryGateway) {
		this.navigationCategoryGateway = navigationCategoryGateway;
	}
}
