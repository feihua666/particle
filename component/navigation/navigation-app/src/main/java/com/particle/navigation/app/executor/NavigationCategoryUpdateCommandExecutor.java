package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationCategoryAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationCategoryUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import com.particle.navigation.domain.NavigationCategory;
import com.particle.navigation.domain.NavigationCategoryId;
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
 * 导航分类 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationCategoryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationCategoryGateway navigationCategoryGateway;

	/**
	 * 执行 导航分类 更新指令
	 * @param navigationCategoryUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationCategoryVO> execute(@Valid NavigationCategoryUpdateCommand navigationCategoryUpdateCommand) {
		NavigationCategory navigationCategory = createByNavigationCategoryUpdateCommand(navigationCategoryUpdateCommand);
		navigationCategory.setUpdateControl(navigationCategoryUpdateCommand);
		boolean save = navigationCategoryGateway.save(navigationCategory);
		if (save) {
			return SingleResponse.of(NavigationCategoryAppStructMapping.instance.toNavigationCategoryVO(navigationCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航分类更新指令创建导航分类模型
	 * @param navigationCategoryUpdateCommand
	 * @return
	 */
	private NavigationCategory createByNavigationCategoryUpdateCommand(NavigationCategoryUpdateCommand navigationCategoryUpdateCommand){
		NavigationCategory navigationCategory = NavigationCategory.create();
		NavigationCategoryUpdateCommandToNavigationCategoryMapping.instance.fillNavigationCategoryByNavigationCategoryUpdateCommand(navigationCategory, navigationCategoryUpdateCommand);
		return navigationCategory;
	}

	@Mapper
	interface NavigationCategoryUpdateCommandToNavigationCategoryMapping{
		NavigationCategoryUpdateCommandToNavigationCategoryMapping instance = Mappers.getMapper(NavigationCategoryUpdateCommandToNavigationCategoryMapping.class );

		default NavigationCategoryId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationCategoryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationCategory
		 * @param navigationCategoryUpdateCommand
		 */
		void fillNavigationCategoryByNavigationCategoryUpdateCommand(@MappingTarget NavigationCategory navigationCategory, NavigationCategoryUpdateCommand navigationCategoryUpdateCommand);
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
