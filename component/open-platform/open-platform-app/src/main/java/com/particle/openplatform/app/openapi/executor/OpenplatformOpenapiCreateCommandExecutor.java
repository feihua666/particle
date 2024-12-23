package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiGateway;
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
 * 开放平台开放接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Component
@Validated
public class OpenplatformOpenapiCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiGateway openplatformOpenapiGateway;

	/**
	 * 执行开放平台开放接口添加指令
	 * @param openplatformOpenapiCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> execute(@Valid OpenplatformOpenapiCreateCommand openplatformOpenapiCreateCommand) {
		OpenplatformOpenapi openplatformOpenapi = createByOpenplatformOpenapiCreateCommand(openplatformOpenapiCreateCommand);
		openplatformOpenapi.setAddControl(openplatformOpenapiCreateCommand);
		boolean save = openplatformOpenapiGateway.save(openplatformOpenapi);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiAppStructMapping.instance.toOpenplatformOpenapiVO(openplatformOpenapi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口创建指令创建开放平台开放接口模型
	 * @param openplatformOpenapiCreateCommand
	 * @return
	 */
	private OpenplatformOpenapi createByOpenplatformOpenapiCreateCommand(OpenplatformOpenapiCreateCommand openplatformOpenapiCreateCommand){
		OpenplatformOpenapi openplatformOpenapi = OpenplatformOpenapi.create();
		OpenplatformOpenapiCreateCommandToOpenplatformOpenapiMapping.instance.fillOpenplatformOpenapiByOpenplatformOpenapiCreateCommand(openplatformOpenapi, openplatformOpenapiCreateCommand);
		return openplatformOpenapi;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformOpenapiCreateCommandToOpenplatformOpenapiMapping{
		OpenplatformOpenapiCreateCommandToOpenplatformOpenapiMapping instance = Mappers.getMapper( OpenplatformOpenapiCreateCommandToOpenplatformOpenapiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapi
		 * @param openplatformOpenapiCreateCommand
		 */
		void fillOpenplatformOpenapiByOpenplatformOpenapiCreateCommand(@MappingTarget OpenplatformOpenapi openplatformOpenapi, OpenplatformOpenapiCreateCommand openplatformOpenapiCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiGateway(OpenplatformOpenapiGateway openplatformOpenapiGateway) {
		this.openplatformOpenapiGateway = openplatformOpenapiGateway;
	}
}
