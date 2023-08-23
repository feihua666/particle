package com.particle.openplatform.app.provider.executor;

import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderCreateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.domain.provider.OpenplatformProvider;
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
 * 开放平台开放接口供应商 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Component
@Validated
public class OpenplatformProviderCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderGateway openplatformProviderGateway;

	/**
	 * 执行开放平台开放接口供应商添加指令
	 * @param openplatformProviderCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderVO> execute(@Valid OpenplatformProviderCreateCommand openplatformProviderCreateCommand) {
		OpenplatformProvider openplatformProvider = createByOpenplatformProviderCreateCommand(openplatformProviderCreateCommand);
		openplatformProvider.setAddControl(openplatformProviderCreateCommand);
		boolean save = openplatformProviderGateway.save(openplatformProvider);
		if (save) {
			return SingleResponse.of(OpenplatformProviderAppStructMapping.instance.toOpenplatformProviderVO(openplatformProvider));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口供应商创建指令创建开放平台开放接口供应商模型
	 * @param openplatformProviderCreateCommand
	 * @return
	 */
	private OpenplatformProvider createByOpenplatformProviderCreateCommand(OpenplatformProviderCreateCommand openplatformProviderCreateCommand){
		OpenplatformProvider openplatformProvider = OpenplatformProvider.create();
		OpenplatformProviderCreateCommandToOpenplatformProviderMapping.instance.fillOpenplatformProviderByOpenplatformProviderCreateCommand(openplatformProvider, openplatformProviderCreateCommand);
		return openplatformProvider;
	}

	@Mapper
	interface  OpenplatformProviderCreateCommandToOpenplatformProviderMapping{
		OpenplatformProviderCreateCommandToOpenplatformProviderMapping instance = Mappers.getMapper( OpenplatformProviderCreateCommandToOpenplatformProviderMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProvider
		 * @param openplatformProviderCreateCommand
		 */
		void fillOpenplatformProviderByOpenplatformProviderCreateCommand(@MappingTarget OpenplatformProvider openplatformProvider, OpenplatformProviderCreateCommand openplatformProviderCreateCommand);
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
