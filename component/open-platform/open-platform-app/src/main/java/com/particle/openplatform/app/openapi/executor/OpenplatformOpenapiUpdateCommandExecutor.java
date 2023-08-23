package com.particle.openplatform.app.openapi.executor;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiGateway;
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
 * 开放平台开放接口 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiGateway openplatformOpenapiGateway;

	/**
	 * 执行 开放平台开放接口 更新指令
	 * @param openplatformOpenapiUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> execute(@Valid OpenplatformOpenapiUpdateCommand openplatformOpenapiUpdateCommand) {
		OpenplatformOpenapi openplatformOpenapi = createByOpenplatformOpenapiUpdateCommand(openplatformOpenapiUpdateCommand);
		openplatformOpenapi.setUpdateControl(openplatformOpenapiUpdateCommand);
		boolean save = openplatformOpenapiGateway.save(openplatformOpenapi);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiAppStructMapping.instance.toOpenplatformOpenapiVO(openplatformOpenapi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口更新指令创建开放平台开放接口模型
	 * @param openplatformOpenapiUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapi createByOpenplatformOpenapiUpdateCommand(OpenplatformOpenapiUpdateCommand openplatformOpenapiUpdateCommand){
		OpenplatformOpenapi openplatformOpenapi = OpenplatformOpenapi.create();
		OpenplatformOpenapiUpdateCommandToOpenplatformOpenapiMapping.instance.fillOpenplatformOpenapiByOpenplatformOpenapiUpdateCommand(openplatformOpenapi, openplatformOpenapiUpdateCommand);
		return openplatformOpenapi;
	}

	@Mapper
	interface OpenplatformOpenapiUpdateCommandToOpenplatformOpenapiMapping{
		OpenplatformOpenapiUpdateCommandToOpenplatformOpenapiMapping instance = Mappers.getMapper(OpenplatformOpenapiUpdateCommandToOpenplatformOpenapiMapping.class );

		default OpenplatformOpenapiId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapi
		 * @param openplatformOpenapiUpdateCommand
		 */
		void fillOpenplatformOpenapiByOpenplatformOpenapiUpdateCommand(@MappingTarget OpenplatformOpenapi openplatformOpenapi, OpenplatformOpenapiUpdateCommand openplatformOpenapiUpdateCommand);
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
