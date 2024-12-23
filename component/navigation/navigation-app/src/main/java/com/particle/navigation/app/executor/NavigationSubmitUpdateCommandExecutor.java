package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationSubmitAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationSubmitUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.NavigationSubmitId;
import com.particle.navigation.domain.gateway.NavigationSubmitGateway;
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
 * 导航提交 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationSubmitUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationSubmitGateway navigationSubmitGateway;

	/**
	 * 执行 导航提交 更新指令
	 * @param navigationSubmitUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationSubmitVO> execute(@Valid NavigationSubmitUpdateCommand navigationSubmitUpdateCommand) {
		NavigationSubmit navigationSubmit = createByNavigationSubmitUpdateCommand(navigationSubmitUpdateCommand);
		navigationSubmit.setUpdateControl(navigationSubmitUpdateCommand);
		boolean save = navigationSubmitGateway.save(navigationSubmit);
		if (save) {
			return SingleResponse.of(NavigationSubmitAppStructMapping.instance.toNavigationSubmitVO(navigationSubmit));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航提交更新指令创建导航提交模型
	 * @param navigationSubmitUpdateCommand
	 * @return
	 */
	private NavigationSubmit createByNavigationSubmitUpdateCommand(NavigationSubmitUpdateCommand navigationSubmitUpdateCommand){
		NavigationSubmit navigationSubmit = NavigationSubmit.create();
		NavigationSubmitUpdateCommandToNavigationSubmitMapping.instance.fillNavigationSubmitByNavigationSubmitUpdateCommand(navigationSubmit, navigationSubmitUpdateCommand);
		return navigationSubmit;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface NavigationSubmitUpdateCommandToNavigationSubmitMapping{
		NavigationSubmitUpdateCommandToNavigationSubmitMapping instance = Mappers.getMapper(NavigationSubmitUpdateCommandToNavigationSubmitMapping.class );

		default NavigationSubmitId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationSubmitId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationSubmit
		 * @param navigationSubmitUpdateCommand
		 */
		void fillNavigationSubmitByNavigationSubmitUpdateCommand(@MappingTarget NavigationSubmit navigationSubmit, NavigationSubmitUpdateCommand navigationSubmitUpdateCommand);
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
