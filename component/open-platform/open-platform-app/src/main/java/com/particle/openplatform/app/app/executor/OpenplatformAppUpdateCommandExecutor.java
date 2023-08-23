package com.particle.openplatform.app.app.executor;

import com.particle.openplatform.app.app.structmapping.OpenplatformAppAppStructMapping;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppUpdateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import com.particle.openplatform.domain.app.OpenplatformApp;
import com.particle.openplatform.domain.app.OpenplatformAppId;
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
 * 开放平台应用 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformAppUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppGateway openplatformAppGateway;

	/**
	 * 执行 开放平台应用 更新指令
	 * @param openplatformAppUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppVO> execute(@Valid OpenplatformAppUpdateCommand openplatformAppUpdateCommand) {
		OpenplatformApp openplatformApp = createByOpenplatformAppUpdateCommand(openplatformAppUpdateCommand);
		openplatformApp.setUpdateControl(openplatformAppUpdateCommand);
		boolean save = openplatformAppGateway.save(openplatformApp);
		if (save) {
			return SingleResponse.of(OpenplatformAppAppStructMapping.instance.toOpenplatformAppVO(openplatformApp));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用更新指令创建开放平台应用模型
	 * @param openplatformAppUpdateCommand
	 * @return
	 */
	private OpenplatformApp createByOpenplatformAppUpdateCommand(OpenplatformAppUpdateCommand openplatformAppUpdateCommand){
		OpenplatformApp openplatformApp = OpenplatformApp.create();
		OpenplatformAppUpdateCommandToOpenplatformAppMapping.instance.fillOpenplatformAppByOpenplatformAppUpdateCommand(openplatformApp, openplatformAppUpdateCommand);
		return openplatformApp;
	}

	@Mapper
	interface OpenplatformAppUpdateCommandToOpenplatformAppMapping{
		OpenplatformAppUpdateCommandToOpenplatformAppMapping instance = Mappers.getMapper(OpenplatformAppUpdateCommandToOpenplatformAppMapping.class );

		default OpenplatformAppId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformAppId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformApp
		 * @param openplatformAppUpdateCommand
		 */
		void fillOpenplatformAppByOpenplatformAppUpdateCommand(@MappingTarget OpenplatformApp openplatformApp, OpenplatformAppUpdateCommand openplatformAppUpdateCommand);
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
