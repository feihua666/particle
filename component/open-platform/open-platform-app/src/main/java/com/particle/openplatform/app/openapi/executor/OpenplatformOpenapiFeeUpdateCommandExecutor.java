package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiFeeAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiFeeUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFee;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiFeeGateway;
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
 * 开放平台开放接口费用 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiFeeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiFeeGateway openplatformOpenapiFeeGateway;

	/**
	 * 执行 开放平台开放接口费用 更新指令
	 * @param openplatformOpenapiFeeUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiFeeVO> execute(@Valid OpenplatformOpenapiFeeUpdateCommand openplatformOpenapiFeeUpdateCommand) {
		OpenplatformOpenapiFee openplatformOpenapiFee = createByOpenplatformOpenapiFeeUpdateCommand(openplatformOpenapiFeeUpdateCommand);
		openplatformOpenapiFee.setUpdateControl(openplatformOpenapiFeeUpdateCommand);
		boolean save = openplatformOpenapiFeeGateway.save(openplatformOpenapiFee);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiFeeAppStructMapping.instance.toOpenplatformOpenapiFeeVO(openplatformOpenapiFee));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口费用更新指令创建开放平台开放接口费用模型
	 * @param openplatformOpenapiFeeUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiFee createByOpenplatformOpenapiFeeUpdateCommand(OpenplatformOpenapiFeeUpdateCommand openplatformOpenapiFeeUpdateCommand){
		OpenplatformOpenapiFee openplatformOpenapiFee = OpenplatformOpenapiFee.create();
		OpenplatformOpenapiFeeUpdateCommandToOpenplatformOpenapiFeeMapping.instance.fillOpenplatformOpenapiFeeByOpenplatformOpenapiFeeUpdateCommand(openplatformOpenapiFee, openplatformOpenapiFeeUpdateCommand);
		return openplatformOpenapiFee;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformOpenapiFeeUpdateCommandToOpenplatformOpenapiFeeMapping{
		OpenplatformOpenapiFeeUpdateCommandToOpenplatformOpenapiFeeMapping instance = Mappers.getMapper(OpenplatformOpenapiFeeUpdateCommandToOpenplatformOpenapiFeeMapping.class );

		default OpenplatformOpenapiFeeId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiFeeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiFee
		 * @param openplatformOpenapiFeeUpdateCommand
		 */
		void fillOpenplatformOpenapiFeeByOpenplatformOpenapiFeeUpdateCommand(@MappingTarget OpenplatformOpenapiFee openplatformOpenapiFee, OpenplatformOpenapiFeeUpdateCommand openplatformOpenapiFeeUpdateCommand);
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
