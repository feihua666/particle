package com.particle.navigation.app.executor;

import com.particle.navigation.app.structmapping.NavigationSubmitAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSubmitCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.gateway.NavigationSubmitGateway;
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
 * 导航提交 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Component
@Validated
public class NavigationSubmitCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSubmitGateway navigationSubmitGateway;

	/**
	 * 执行导航提交添加指令
	 * @param navigationSubmitCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationSubmitVO> execute(@Valid NavigationSubmitCreateCommand navigationSubmitCreateCommand) {
		NavigationSubmit navigationSubmit = createByNavigationSubmitCreateCommand(navigationSubmitCreateCommand);
		navigationSubmit.setAddControl(navigationSubmitCreateCommand);

		navigationSubmit.initForAdd();
		boolean save = navigationSubmitGateway.save(navigationSubmit);
		if (save) {
			return SingleResponse.of(NavigationSubmitAppStructMapping.instance.toNavigationSubmitVO(navigationSubmit));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航提交创建指令创建导航提交模型
	 * @param navigationSubmitCreateCommand
	 * @return
	 */
	private NavigationSubmit createByNavigationSubmitCreateCommand(NavigationSubmitCreateCommand navigationSubmitCreateCommand){
		NavigationSubmit navigationSubmit = NavigationSubmit.create();
		NavigationSubmitCreateCommandToNavigationSubmitMapping.instance.fillNavigationSubmitByNavigationSubmitCreateCommand(navigationSubmit, navigationSubmitCreateCommand);
		return navigationSubmit;
	}

	@Mapper
	interface  NavigationSubmitCreateCommandToNavigationSubmitMapping{
		NavigationSubmitCreateCommandToNavigationSubmitMapping instance = Mappers.getMapper( NavigationSubmitCreateCommandToNavigationSubmitMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSubmit
		 * @param navigationSubmitCreateCommand
		 */
		void fillNavigationSubmitByNavigationSubmitCreateCommand(@MappingTarget NavigationSubmit navigationSubmit, NavigationSubmitCreateCommand navigationSubmitCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationSubmitGateway
	 */
	@Autowired
	public void setNavigationSubmitGateway(NavigationSubmitGateway navigationSubmitGateway) {
		this.navigationSubmitGateway = navigationSubmitGateway;
	}
}
