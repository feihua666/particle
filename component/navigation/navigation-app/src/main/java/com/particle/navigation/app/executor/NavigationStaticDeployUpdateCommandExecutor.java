package com.particle.navigation.app.executor;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.navigation.app.structmapping.NavigationStaticDeployAppStructMapping;
import com.particle.navigation.client.dto.command.NavigationStaticDeployUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import com.particle.navigation.domain.NavigationStaticDeploy;
import com.particle.navigation.domain.NavigationStaticDeployId;
import com.particle.navigation.domain.gateway.NavigationStaticDeployGateway;
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
import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站静态部署 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class NavigationStaticDeployUpdateCommandExecutor  extends AbstractBaseExecutor {

	private NavigationStaticDeployGateway navigationStaticDeployGateway;

	/**
	 * 执行部署时间更新
	 * @param idCommand
	 * @param deployAt
	 * @return
	 */
	public Response updateLastDeployAt(IdCommand idCommand, LocalDateTime deployAt){
		NavigationStaticDeploy navigationStaticDeploy = navigationStaticDeployGateway.getById(NavigationStaticDeployId.of(idCommand.getId()));
		navigationStaticDeploy.changeLastDeployAt(deployAt);
		boolean save = navigationStaticDeployGateway.save(navigationStaticDeploy);
		if (save) {
			return Response.buildSuccess();
		}
		return Response.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行 导航网站静态部署 更新指令
	 * @param navigationStaticDeployUpdateCommand
	 * @return
	 */
	public SingleResponse<NavigationStaticDeployVO> execute(@Valid NavigationStaticDeployUpdateCommand navigationStaticDeployUpdateCommand) {
		NavigationStaticDeploy navigationStaticDeploy = createByNavigationStaticDeployUpdateCommand(navigationStaticDeployUpdateCommand);
		navigationStaticDeploy.setUpdateControl(navigationStaticDeployUpdateCommand);
		boolean save = navigationStaticDeployGateway.save(navigationStaticDeploy);
		if (save) {
			return SingleResponse.of(NavigationStaticDeployAppStructMapping.instance.toNavigationStaticDeployVO(navigationStaticDeploy));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据导航网站静态部署更新指令创建导航网站静态部署模型
	 * @param navigationStaticDeployUpdateCommand
	 * @return
	 */
	private NavigationStaticDeploy createByNavigationStaticDeployUpdateCommand(NavigationStaticDeployUpdateCommand navigationStaticDeployUpdateCommand){
		NavigationStaticDeploy navigationStaticDeploy = NavigationStaticDeploy.create();
		NavigationStaticDeployUpdateCommandToNavigationStaticDeployMapping.instance.fillNavigationStaticDeployByNavigationStaticDeployUpdateCommand(navigationStaticDeploy, navigationStaticDeployUpdateCommand);
		return navigationStaticDeploy;
	}

	@Mapper
	interface NavigationStaticDeployUpdateCommandToNavigationStaticDeployMapping{
		NavigationStaticDeployUpdateCommandToNavigationStaticDeployMapping instance = Mappers.getMapper(NavigationStaticDeployUpdateCommandToNavigationStaticDeployMapping.class );

		default NavigationStaticDeployId map(Long id){
			if (id == null) {
				return null;
			}
			return NavigationStaticDeployId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param navigationStaticDeploy
		 * @param navigationStaticDeployUpdateCommand
		 */
		void fillNavigationStaticDeployByNavigationStaticDeployUpdateCommand(@MappingTarget NavigationStaticDeploy navigationStaticDeploy, NavigationStaticDeployUpdateCommand navigationStaticDeployUpdateCommand);
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
