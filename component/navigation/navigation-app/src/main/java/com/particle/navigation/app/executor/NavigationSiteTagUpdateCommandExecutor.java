package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationSiteTagAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSiteTagUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import com.particle.navigation.domain.NavigationSiteTag;
import com.particle.navigation.domain.NavigationSiteTagId;
import com.particle.navigation.domain.gateway.NavigationSiteTagGateway;
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
 * 导航网站标签 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationSiteTagUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSiteTagGateway navigationSiteTagGateway;

	/**
	 * 执行 导航网站标签 更新指令
	 * @param navigationSiteTagUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSiteTagVO> execute(@Valid NavigationSiteTagUpdateCommand navigationSiteTagUpdateCommand) {
		NavigationSiteTag navigationSiteTag = createByNavigationSiteTagUpdateCommand(navigationSiteTagUpdateCommand);
		navigationSiteTag.setUpdateControl(navigationSiteTagUpdateCommand);
		boolean save = navigationSiteTagGateway.save(navigationSiteTag);
		if (save) {
			return SingleResponse.of(NavigationSiteTagAppStructMapping.instance.toNavigationSiteTagVO(navigationSiteTag));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站标签更新指令创建导航网站标签模型
	 * @param navigationSiteTagUpdateCommand
	 * @return
	 */
	private NavigationSiteTag createByNavigationSiteTagUpdateCommand(NavigationSiteTagUpdateCommand navigationSiteTagUpdateCommand){
		NavigationSiteTag navigationSiteTag = NavigationSiteTag.create();
		NavigationSiteTagUpdateCommandToNavigationSiteTagMapping.instance.fillNavigationSiteTagByNavigationSiteTagUpdateCommand(navigationSiteTag, navigationSiteTagUpdateCommand);
		return navigationSiteTag;
	}

	@Mapper
	interface NavigationSiteTagUpdateCommandToNavigationSiteTagMapping{
		NavigationSiteTagUpdateCommandToNavigationSiteTagMapping instance = Mappers.getMapper(NavigationSiteTagUpdateCommandToNavigationSiteTagMapping.class );

		default NavigationSiteTagId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationSiteTagId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSiteTag
		 * @param navigationSiteTagUpdateCommand
		 */
		void fillNavigationSiteTagByNavigationSiteTagUpdateCommand(@MappingTarget NavigationSiteTag navigationSiteTag, NavigationSiteTagUpdateCommand navigationSiteTagUpdateCommand);
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
