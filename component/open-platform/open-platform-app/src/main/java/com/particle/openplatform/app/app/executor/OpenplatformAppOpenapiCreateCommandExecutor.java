package com.particle.openplatform.app.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppOpenapiAppStructMapping;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppOpenapiCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapi;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppOpenapiGateway;
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
 * 开放平台应用与开放接口配置 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Component
@Validated
public class OpenplatformAppOpenapiCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppOpenapiGateway openplatformAppOpenapiGateway;

	/**
	 * 执行开放平台应用与开放接口配置添加指令
	 * @param openplatformAppOpenapiCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppOpenapiVO> execute(@Valid OpenplatformAppOpenapiCreateCommand openplatformAppOpenapiCreateCommand) {
		OpenplatformAppOpenapi openplatformAppOpenapi = createByOpenplatformAppOpenapiCreateCommand(openplatformAppOpenapiCreateCommand);
		openplatformAppOpenapi.setAddControl(openplatformAppOpenapiCreateCommand);
		boolean save = openplatformAppOpenapiGateway.save(openplatformAppOpenapi);
		if (save) {
			return SingleResponse.of(OpenplatformAppOpenapiAppStructMapping.instance.toOpenplatformAppOpenapiVO(openplatformAppOpenapi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用与开放接口配置创建指令创建开放平台应用与开放接口配置模型
	 * @param openplatformAppOpenapiCreateCommand
	 * @return
	 */
	private OpenplatformAppOpenapi createByOpenplatformAppOpenapiCreateCommand(OpenplatformAppOpenapiCreateCommand openplatformAppOpenapiCreateCommand){
		OpenplatformAppOpenapi openplatformAppOpenapi = OpenplatformAppOpenapi.create();
		OpenplatformAppOpenapiCreateCommandToOpenplatformAppOpenapiMapping.instance.fillOpenplatformAppOpenapiByOpenplatformAppOpenapiCreateCommand(openplatformAppOpenapi, openplatformAppOpenapiCreateCommand);
		return openplatformAppOpenapi;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformAppOpenapiCreateCommandToOpenplatformAppOpenapiMapping{
		OpenplatformAppOpenapiCreateCommandToOpenplatformAppOpenapiMapping instance = Mappers.getMapper( OpenplatformAppOpenapiCreateCommandToOpenplatformAppOpenapiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformAppOpenapi
		 * @param openplatformAppOpenapiCreateCommand
		 */
		void fillOpenplatformAppOpenapiByOpenplatformAppOpenapiCreateCommand(@MappingTarget OpenplatformAppOpenapi openplatformAppOpenapi, OpenplatformAppOpenapiCreateCommand openplatformAppOpenapiCreateCommand);
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
