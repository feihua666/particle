package com.particle.openplatform.app.provider.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderApiAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiCreateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import com.particle.openplatform.domain.provider.OpenplatformProviderApi;
import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderApiGateway;
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
 * 开放平台供应商接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Component
@Validated
public class OpenplatformProviderApiCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderApiGateway openplatformProviderApiGateway;

	/**
	 * 执行开放平台供应商接口添加指令
	 * @param openplatformProviderApiCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderApiVO> execute(@Valid OpenplatformProviderApiCreateCommand openplatformProviderApiCreateCommand) {
		OpenplatformProviderApi openplatformProviderApi = createByOpenplatformProviderApiCreateCommand(openplatformProviderApiCreateCommand);
		openplatformProviderApi.setAddControl(openplatformProviderApiCreateCommand);
		boolean save = openplatformProviderApiGateway.save(openplatformProviderApi);
		if (save) {
			return SingleResponse.of(OpenplatformProviderApiAppStructMapping.instance.toOpenplatformProviderApiVO(openplatformProviderApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商接口创建指令创建开放平台供应商接口模型
	 * @param openplatformProviderApiCreateCommand
	 * @return
	 */
	private OpenplatformProviderApi createByOpenplatformProviderApiCreateCommand(OpenplatformProviderApiCreateCommand openplatformProviderApiCreateCommand){
		OpenplatformProviderApi openplatformProviderApi = OpenplatformProviderApi.create();
		OpenplatformProviderApiCreateCommandToOpenplatformProviderApiMapping.instance.fillOpenplatformProviderApiByOpenplatformProviderApiCreateCommand(openplatformProviderApi, openplatformProviderApiCreateCommand);
		return openplatformProviderApi;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformProviderApiCreateCommandToOpenplatformProviderApiMapping{
		OpenplatformProviderApiCreateCommandToOpenplatformProviderApiMapping instance = Mappers.getMapper( OpenplatformProviderApiCreateCommandToOpenplatformProviderApiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderApi
		 * @param openplatformProviderApiCreateCommand
		 */
		void fillOpenplatformProviderApiByOpenplatformProviderApiCreateCommand(@MappingTarget OpenplatformProviderApi openplatformProviderApi, OpenplatformProviderApiCreateCommand openplatformProviderApiCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderApiGateway
	 */
	@Autowired
	public void setOpenplatformProviderApiGateway(OpenplatformProviderApiGateway openplatformProviderApiGateway) {
		this.openplatformProviderApiGateway = openplatformProviderApiGateway;
	}
}
