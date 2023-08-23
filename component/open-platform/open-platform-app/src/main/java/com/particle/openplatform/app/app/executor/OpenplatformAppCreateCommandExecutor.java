package com.particle.openplatform.app.app.executor;

import com.particle.openplatform.app.app.structmapping.OpenplatformAppAppStructMapping;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import com.particle.openplatform.domain.app.OpenplatformApp;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppGateway;
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
 * 开放平台应用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Component
@Validated
public class OpenplatformAppCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppGateway openplatformAppGateway;

	/**
	 * 执行开放平台应用添加指令
	 * @param openplatformAppCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppVO> execute(@Valid OpenplatformAppCreateCommand openplatformAppCreateCommand) {
		OpenplatformApp openplatformApp = createByOpenplatformAppCreateCommand(openplatformAppCreateCommand);
		openplatformApp.setAddControl(openplatformAppCreateCommand);
		boolean save = openplatformAppGateway.save(openplatformApp);
		if (save) {
			return SingleResponse.of(OpenplatformAppAppStructMapping.instance.toOpenplatformAppVO(openplatformApp));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用创建指令创建开放平台应用模型
	 * @param openplatformAppCreateCommand
	 * @return
	 */
	private OpenplatformApp createByOpenplatformAppCreateCommand(OpenplatformAppCreateCommand openplatformAppCreateCommand){
		OpenplatformApp openplatformApp = OpenplatformApp.create();
		OpenplatformAppCreateCommandToOpenplatformAppMapping.instance.fillOpenplatformAppByOpenplatformAppCreateCommand(openplatformApp, openplatformAppCreateCommand);
		return openplatformApp;
	}

	@Mapper
	interface  OpenplatformAppCreateCommandToOpenplatformAppMapping{
		OpenplatformAppCreateCommandToOpenplatformAppMapping instance = Mappers.getMapper( OpenplatformAppCreateCommandToOpenplatformAppMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformApp
		 * @param openplatformAppCreateCommand
		 */
		void fillOpenplatformAppByOpenplatformAppCreateCommand(@MappingTarget OpenplatformApp openplatformApp, OpenplatformAppCreateCommand openplatformAppCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformAppGateway
	 */
	@Autowired
	public void setOpenplatformAppGateway(OpenplatformAppGateway openplatformAppGateway) {
		this.openplatformAppGateway = openplatformAppGateway;
	}
}
