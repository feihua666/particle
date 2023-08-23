package com.particle.openplatform.app.openapi.executor;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiFeeAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFee;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiFeeGateway;
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
 * 开放平台开放接口费用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Component
@Validated
public class OpenplatformOpenapiFeeCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiFeeGateway openplatformOpenapiFeeGateway;

	/**
	 * 执行开放平台开放接口费用添加指令
	 * @param openplatformOpenapiFeeCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiFeeVO> execute(@Valid OpenplatformOpenapiFeeCreateCommand openplatformOpenapiFeeCreateCommand) {
		OpenplatformOpenapiFee openplatformOpenapiFee = createByOpenplatformOpenapiFeeCreateCommand(openplatformOpenapiFeeCreateCommand);
		openplatformOpenapiFee.setAddControl(openplatformOpenapiFeeCreateCommand);
		boolean save = openplatformOpenapiFeeGateway.save(openplatformOpenapiFee);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiFeeAppStructMapping.instance.toOpenplatformOpenapiFeeVO(openplatformOpenapiFee));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口费用创建指令创建开放平台开放接口费用模型
	 * @param openplatformOpenapiFeeCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiFee createByOpenplatformOpenapiFeeCreateCommand(OpenplatformOpenapiFeeCreateCommand openplatformOpenapiFeeCreateCommand){
		OpenplatformOpenapiFee openplatformOpenapiFee = OpenplatformOpenapiFee.create();
		OpenplatformOpenapiFeeCreateCommandToOpenplatformOpenapiFeeMapping.instance.fillOpenplatformOpenapiFeeByOpenplatformOpenapiFeeCreateCommand(openplatformOpenapiFee, openplatformOpenapiFeeCreateCommand);
		return openplatformOpenapiFee;
	}

	@Mapper
	interface  OpenplatformOpenapiFeeCreateCommandToOpenplatformOpenapiFeeMapping{
		OpenplatformOpenapiFeeCreateCommandToOpenplatformOpenapiFeeMapping instance = Mappers.getMapper( OpenplatformOpenapiFeeCreateCommandToOpenplatformOpenapiFeeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiFee
		 * @param openplatformOpenapiFeeCreateCommand
		 */
		void fillOpenplatformOpenapiFeeByOpenplatformOpenapiFeeCreateCommand(@MappingTarget OpenplatformOpenapiFee openplatformOpenapiFee, OpenplatformOpenapiFeeCreateCommand openplatformOpenapiFeeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiFeeGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiFeeGateway(OpenplatformOpenapiFeeGateway openplatformOpenapiFeeGateway) {
		this.openplatformOpenapiFeeGateway = openplatformOpenapiFeeGateway;
	}
}
