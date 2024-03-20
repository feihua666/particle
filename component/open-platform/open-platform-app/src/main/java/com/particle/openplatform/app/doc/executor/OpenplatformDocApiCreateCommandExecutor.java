package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiGateway;
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
 * 开放接口文档接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Component
@Validated
public class OpenplatformDocApiCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiGateway openplatformDocApiGateway;
	private OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway;

	/**
	 * 执行开放接口文档接口添加指令
	 * @param openplatformDocApiCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiVO> execute(@Valid OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand) {
		OpenplatformDocApi openplatformDocApi = createByOpenplatformDocApiCreateCommand(openplatformDocApiCreateCommand);
		openplatformDocApi.setAddControl(openplatformDocApiCreateCommand);
		boolean save = openplatformDocApiGateway.save(openplatformDocApi);
		if (save) {
			// 添加到目录
			Long openplatformDocDirId = openplatformDocApiCreateCommand.getOpenplatformDocDirId();
			if (openplatformDocDirId != null) {
				openplatformDocApiDirRelGateway.save(OpenplatformDocApiDirRel.create(openplatformDocApi.getId().getId(), openplatformDocDirId));
			}
			return SingleResponse.of(OpenplatformDocApiAppStructMapping.instance.toOpenplatformDocApiVO(openplatformDocApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档接口创建指令创建开放接口文档接口模型
	 * @param openplatformDocApiCreateCommand
	 * @return
	 */
	private OpenplatformDocApi createByOpenplatformDocApiCreateCommand(OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand){
		OpenplatformDocApi openplatformDocApi = OpenplatformDocApi.create();
		OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping.instance.fillOpenplatformDocApiByOpenplatformDocApiCreateCommand(openplatformDocApi, openplatformDocApiCreateCommand);
		return openplatformDocApi;
	}

	@Mapper
	interface  OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping{
		OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping instance = Mappers.getMapper( OpenplatformDocApiCreateCommandToOpenplatformDocApiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApi
		 * @param openplatformDocApiCreateCommand
		 */
		void fillOpenplatformDocApiByOpenplatformDocApiCreateCommand(@MappingTarget OpenplatformDocApi openplatformDocApi, OpenplatformDocApiCreateCommand openplatformDocApiCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiGateway
	 */
	@Autowired
	public void setOpenplatformDocApiGateway(OpenplatformDocApiGateway openplatformDocApiGateway) {
		this.openplatformDocApiGateway = openplatformDocApiGateway;
	}

	@Autowired
	public void setOpenplatformDocApiDirRelGateway(OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway) {
		this.openplatformDocApiDirRelGateway = openplatformDocApiDirRelGateway;
	}
}
