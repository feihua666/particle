package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationSiteTagRelAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import com.particle.navigation.domain.NavigationSiteTagRel;
import com.particle.navigation.domain.NavigationSiteTagRelId;
import com.particle.navigation.domain.gateway.NavigationSiteTagRelGateway;
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
 * 导航网站标签关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationSiteTagRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagRelGateway navigationSiteTagRelGateway;

	/**
	 * 执行 导航网站标签关系 更新指令
	 * @param navigationSiteTagRelUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagRelVO> execute(@Valid NavigationSiteTagRelUpdateCommand navigationSiteTagRelUpdateCommand) {
		NavigationSiteTagRel navigationSiteTagRel = createByNavigationSiteTagRelUpdateCommand(navigationSiteTagRelUpdateCommand);
		navigationSiteTagRel.setUpdateControl(navigationSiteTagRelUpdateCommand);
		boolean save = navigationSiteTagRelGateway.save(navigationSiteTagRel);
		if (save) {
			return SingleResponse.of(NavigationSiteTagRelAppStructMapping.instance.toNavigationSiteTagRelVO(navigationSiteTagRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站标签关系更新指令创建导航网站标签关系模型
	 * @param navigationSiteTagRelUpdateCommand
	 * @return
	 */
	private NavigationSiteTagRel createByNavigationSiteTagRelUpdateCommand(NavigationSiteTagRelUpdateCommand navigationSiteTagRelUpdateCommand){
		NavigationSiteTagRel navigationSiteTagRel = NavigationSiteTagRel.create();
		NavigationSiteTagRelUpdateCommandToNavigationSiteTagRelMapping.instance.fillNavigationSiteTagRelByNavigationSiteTagRelUpdateCommand(navigationSiteTagRel, navigationSiteTagRelUpdateCommand);
		return navigationSiteTagRel;
	}

	@Mapper
	interface NavigationSiteTagRelUpdateCommandToNavigationSiteTagRelMapping{
		NavigationSiteTagRelUpdateCommandToNavigationSiteTagRelMapping instance = Mappers.getMapper(NavigationSiteTagRelUpdateCommandToNavigationSiteTagRelMapping.class );

		default NavigationSiteTagRelId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationSiteTagRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSiteTagRel
		 * @param navigationSiteTagRelUpdateCommand
		 */
		void fillNavigationSiteTagRelByNavigationSiteTagRelUpdateCommand(@MappingTarget NavigationSiteTagRel navigationSiteTagRel, NavigationSiteTagRelUpdateCommand navigationSiteTagRelUpdateCommand);
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
