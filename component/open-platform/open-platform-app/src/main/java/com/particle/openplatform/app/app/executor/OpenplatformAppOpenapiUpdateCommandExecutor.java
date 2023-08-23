package com.particle.openplatform.app.app.executor;

import com.particle.openplatform.app.app.structmapping.OpenplatformAppOpenapiAppStructMapping;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiUpdateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapi;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapiId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppOpenapiGateway;
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
 * 开放平台应用与开放接口配置 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformAppOpenapiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppOpenapiGateway openplatformAppOpenapiGateway;

	/**
	 * 执行 开放平台应用与开放接口配置 更新指令
	 * @param openplatformAppOpenapiUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppOpenapiVO> execute(@Valid OpenplatformAppOpenapiUpdateCommand openplatformAppOpenapiUpdateCommand) {
		OpenplatformAppOpenapi openplatformAppOpenapi = createByOpenplatformAppOpenapiUpdateCommand(openplatformAppOpenapiUpdateCommand);
		openplatformAppOpenapi.setUpdateControl(openplatformAppOpenapiUpdateCommand);
		boolean save = openplatformAppOpenapiGateway.save(openplatformAppOpenapi);
		if (save) {
			return SingleResponse.of(OpenplatformAppOpenapiAppStructMapping.instance.toOpenplatformAppOpenapiVO(openplatformAppOpenapi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用与开放接口配置更新指令创建开放平台应用与开放接口配置模型
	 * @param openplatformAppOpenapiUpdateCommand
	 * @return
	 */
	private OpenplatformAppOpenapi createByOpenplatformAppOpenapiUpdateCommand(OpenplatformAppOpenapiUpdateCommand openplatformAppOpenapiUpdateCommand){
		OpenplatformAppOpenapi openplatformAppOpenapi = OpenplatformAppOpenapi.create();
		OpenplatformAppOpenapiUpdateCommandToOpenplatformAppOpenapiMapping.instance.fillOpenplatformAppOpenapiByOpenplatformAppOpenapiUpdateCommand(openplatformAppOpenapi, openplatformAppOpenapiUpdateCommand);
		return openplatformAppOpenapi;
	}

	@Mapper
	interface OpenplatformAppOpenapiUpdateCommandToOpenplatformAppOpenapiMapping{
		OpenplatformAppOpenapiUpdateCommandToOpenplatformAppOpenapiMapping instance = Mappers.getMapper(OpenplatformAppOpenapiUpdateCommandToOpenplatformAppOpenapiMapping.class );

		default OpenplatformAppOpenapiId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformAppOpenapiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformAppOpenapi
		 * @param openplatformAppOpenapiUpdateCommand
		 */
		void fillOpenplatformAppOpenapiByOpenplatformAppOpenapiUpdateCommand(@MappingTarget OpenplatformAppOpenapi openplatformAppOpenapi, OpenplatformAppOpenapiUpdateCommand openplatformAppOpenapiUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformAppOpenapiGateway
	 */
	@Autowired
	public void setOpenplatformAppOpenapiGateway(OpenplatformAppOpenapiGateway openplatformAppOpenapiGateway) {
		this.openplatformAppOpenapiGateway = openplatformAppOpenapiGateway;
	}
}
