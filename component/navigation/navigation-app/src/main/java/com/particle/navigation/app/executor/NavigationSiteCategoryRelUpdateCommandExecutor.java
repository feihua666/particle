package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSiteCategoryRelAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import com.particle.navigation.domain.NavigationSiteCategoryRel;
import com.particle.navigation.domain.NavigationSiteCategoryRelId;
import com.particle.navigation.domain.gateway.NavigationSiteCategoryRelGateway;
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
 * 导航网站分类关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationSiteCategoryRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteCategoryRelGateway navigationSiteCategoryRelGateway;

	/**
	 * 执行 导航网站分类关系 更新指令
	 * @param navigationSiteCategoryRelUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteCategoryRelVO> execute(@Valid NavigationSiteCategoryRelUpdateCommand navigationSiteCategoryRelUpdateCommand) {
		NavigationSiteCategoryRel navigationSiteCategoryRel = createByNavigationSiteCategoryRelUpdateCommand(navigationSiteCategoryRelUpdateCommand);
		navigationSiteCategoryRel.setUpdateControl(navigationSiteCategoryRelUpdateCommand);
		boolean save = navigationSiteCategoryRelGateway.save(navigationSiteCategoryRel);
		if (save) {
			return SingleResponse.of(NavigationSiteCategoryRelAppStructMapping.instance.toNavigationSiteCategoryRelVO(navigationSiteCategoryRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站分类关系更新指令创建导航网站分类关系模型
	 * @param navigationSiteCategoryRelUpdateCommand
	 * @return
	 */
	private NavigationSiteCategoryRel createByNavigationSiteCategoryRelUpdateCommand(NavigationSiteCategoryRelUpdateCommand navigationSiteCategoryRelUpdateCommand){
		NavigationSiteCategoryRel navigationSiteCategoryRel = NavigationSiteCategoryRel.create();
		NavigationSiteCategoryRelUpdateCommandToNavigationSiteCategoryRelMapping.instance.fillNavigationSiteCategoryRelByNavigationSiteCategoryRelUpdateCommand(navigationSiteCategoryRel, navigationSiteCategoryRelUpdateCommand);
		return navigationSiteCategoryRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface NavigationSiteCategoryRelUpdateCommandToNavigationSiteCategoryRelMapping{
		NavigationSiteCategoryRelUpdateCommandToNavigationSiteCategoryRelMapping instance = Mappers.getMapper(NavigationSiteCategoryRelUpdateCommandToNavigationSiteCategoryRelMapping.class );

		default NavigationSiteCategoryRelId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationSiteCategoryRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSiteCategoryRel
		 * @param navigationSiteCategoryRelUpdateCommand
		 */
		void fillNavigationSiteCategoryRelByNavigationSiteCategoryRelUpdateCommand(@MappingTarget NavigationSiteCategoryRel navigationSiteCategoryRel, NavigationSiteCategoryRelUpdateCommand navigationSiteCategoryRelUpdateCommand);
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
