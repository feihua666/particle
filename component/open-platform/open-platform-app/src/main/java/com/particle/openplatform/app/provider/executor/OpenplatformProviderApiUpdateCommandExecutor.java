package com.particle.openplatform.app.provider.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderApiAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiUpdateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import com.particle.openplatform.domain.provider.OpenplatformProviderApi;
import com.particle.openplatform.domain.provider.OpenplatformProviderApiId;
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
 * 开放平台供应商接口 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformProviderApiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderApiGateway openplatformProviderApiGateway;

	/**
	 * 执行 开放平台供应商接口 更新指令
	 * @param openplatformProviderApiUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderApiVO> execute(@Valid OpenplatformProviderApiUpdateCommand openplatformProviderApiUpdateCommand) {
		OpenplatformProviderApi openplatformProviderApi = createByOpenplatformProviderApiUpdateCommand(openplatformProviderApiUpdateCommand);
		openplatformProviderApi.setUpdateControl(openplatformProviderApiUpdateCommand);
		boolean save = openplatformProviderApiGateway.save(openplatformProviderApi);
		if (save) {
			return SingleResponse.of(OpenplatformProviderApiAppStructMapping.instance.toOpenplatformProviderApiVO(openplatformProviderApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商接口更新指令创建开放平台供应商接口模型
	 * @param openplatformProviderApiUpdateCommand
	 * @return
	 */
	private OpenplatformProviderApi createByOpenplatformProviderApiUpdateCommand(OpenplatformProviderApiUpdateCommand openplatformProviderApiUpdateCommand){
		OpenplatformProviderApi openplatformProviderApi = OpenplatformProviderApi.create();
		OpenplatformProviderApiUpdateCommandToOpenplatformProviderApiMapping.instance.fillOpenplatformProviderApiByOpenplatformProviderApiUpdateCommand(openplatformProviderApi, openplatformProviderApiUpdateCommand);
		return openplatformProviderApi;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformProviderApiUpdateCommandToOpenplatformProviderApiMapping{
		OpenplatformProviderApiUpdateCommandToOpenplatformProviderApiMapping instance = Mappers.getMapper(OpenplatformProviderApiUpdateCommandToOpenplatformProviderApiMapping.class );

		default OpenplatformProviderApiId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformProviderApiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderApi
		 * @param openplatformProviderApiUpdateCommand
		 */
		void fillOpenplatformProviderApiByOpenplatformProviderApiUpdateCommand(@MappingTarget OpenplatformProviderApi openplatformProviderApi, OpenplatformProviderApiUpdateCommand openplatformProviderApiUpdateCommand);
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
