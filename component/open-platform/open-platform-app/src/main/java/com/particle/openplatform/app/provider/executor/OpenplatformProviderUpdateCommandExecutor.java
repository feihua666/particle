package com.particle.openplatform.app.provider.executor;

import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderUpdateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.domain.provider.OpenplatformProvider;
import com.particle.openplatform.domain.provider.OpenplatformProviderId;
import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderGateway;
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
 * 开放平台开放接口供应商 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformProviderUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderGateway openplatformProviderGateway;

	/**
	 * 执行 开放平台开放接口供应商 更新指令
	 * @param openplatformProviderUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderVO> execute(@Valid OpenplatformProviderUpdateCommand openplatformProviderUpdateCommand) {
		OpenplatformProvider openplatformProvider = createByOpenplatformProviderUpdateCommand(openplatformProviderUpdateCommand);
		openplatformProvider.setUpdateControl(openplatformProviderUpdateCommand);
		boolean save = openplatformProviderGateway.save(openplatformProvider);
		if (save) {
			return SingleResponse.of(OpenplatformProviderAppStructMapping.instance.toOpenplatformProviderVO(openplatformProvider));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口供应商更新指令创建开放平台开放接口供应商模型
	 * @param openplatformProviderUpdateCommand
	 * @return
	 */
	private OpenplatformProvider createByOpenplatformProviderUpdateCommand(OpenplatformProviderUpdateCommand openplatformProviderUpdateCommand){
		OpenplatformProvider openplatformProvider = OpenplatformProvider.create();
		OpenplatformProviderUpdateCommandToOpenplatformProviderMapping.instance.fillOpenplatformProviderByOpenplatformProviderUpdateCommand(openplatformProvider, openplatformProviderUpdateCommand);
		return openplatformProvider;
	}

	@Mapper
	interface OpenplatformProviderUpdateCommandToOpenplatformProviderMapping{
		OpenplatformProviderUpdateCommandToOpenplatformProviderMapping instance = Mappers.getMapper(OpenplatformProviderUpdateCommandToOpenplatformProviderMapping.class );

		default OpenplatformProviderId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformProviderId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProvider
		 * @param openplatformProviderUpdateCommand
		 */
		void fillOpenplatformProviderByOpenplatformProviderUpdateCommand(@MappingTarget OpenplatformProvider openplatformProvider, OpenplatformProviderUpdateCommand openplatformProviderUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderGateway
	 */
	@Autowired
	public void setOpenplatformProviderGateway(OpenplatformProviderGateway openplatformProviderGateway) {
		this.openplatformProviderGateway = openplatformProviderGateway;
	}
}
