package com.particle.navigation.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.navigation.app.structmapping.NavigationStaticDeployAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationStaticDeployCreateCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import com.particle.navigation.domain.NavigationStaticDeploy;
import com.particle.navigation.domain.gateway.NavigationStaticDeployGateway;
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
 * 导航网站静态部署 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Component
@Validated
public class NavigationStaticDeployCreateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationStaticDeployGateway navigationStaticDeployGateway;

	/**
	 * 执行导航网站静态部署添加指令
	 * @param navigationStaticDeployCreateCommand
	 * @return
	 */
	public SingleResponse<NavigationStaticDeployVO> execute(@Valid NavigationStaticDeployCreateCommand navigationStaticDeployCreateCommand) {
		NavigationStaticDeploy navigationStaticDeploy = createByNavigationStaticDeployCreateCommand(navigationStaticDeployCreateCommand);
		navigationStaticDeploy.setAddControl(navigationStaticDeployCreateCommand);
		boolean save = navigationStaticDeployGateway.save(navigationStaticDeploy);
		if (save) {
			return SingleResponse.of(NavigationStaticDeployAppStructMapping.instance.toNavigationStaticDeployVO(navigationStaticDeploy));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站静态部署创建指令创建导航网站静态部署模型
	 * @param navigationStaticDeployCreateCommand
	 * @return
	 */
	private NavigationStaticDeploy createByNavigationStaticDeployCreateCommand(NavigationStaticDeployCreateCommand navigationStaticDeployCreateCommand){
		NavigationStaticDeploy navigationStaticDeploy = NavigationStaticDeploy.create();
		NavigationStaticDeployCreateCommandToNavigationStaticDeployMapping.instance.fillNavigationStaticDeployByNavigationStaticDeployCreateCommand(navigationStaticDeploy, navigationStaticDeployCreateCommand);
		return navigationStaticDeploy;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  NavigationStaticDeployCreateCommandToNavigationStaticDeployMapping{
		NavigationStaticDeployCreateCommandToNavigationStaticDeployMapping instance = Mappers.getMapper( NavigationStaticDeployCreateCommandToNavigationStaticDeployMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationStaticDeploy
		 * @param navigationStaticDeployCreateCommand
		 */
		void fillNavigationStaticDeployByNavigationStaticDeployCreateCommand(@MappingTarget NavigationStaticDeploy navigationStaticDeploy, NavigationStaticDeployCreateCommand navigationStaticDeployCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param navigationStaticDeployGateway
	 */
	@Autowired
	public void setNavigationStaticDeployGateway(NavigationStaticDeployGateway navigationStaticDeployGateway) {
		this.navigationStaticDeployGateway = navigationStaticDeployGateway;
	}
}
