package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocParamFieldGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档参数字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Component
@Validated
public class OpenplatformDocApiDocParamFieldCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway;

	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;

	/**
	 * 执行开放接口文档参数字段添加指令
	 * @param openplatformDocApiDocParamFieldCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> execute(@Valid OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand) {
		OpenplatformDocApiDocParamField openplatformDocApiDocParamField = createByOpenplatformDocApiDocParamFieldCreateCommand(openplatformDocApiDocParamFieldCreateCommand);
		OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocParamField.getOpenplatformDocApiDocId());
		openplatformDocApiDocParamField.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());

		openplatformDocApiDocParamField.setAddControl(openplatformDocApiDocParamFieldCreateCommand);
		boolean save = openplatformDocApiDocParamFieldGateway.save(openplatformDocApiDocParamField);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocParamFieldAppStructMapping.instance.toOpenplatformDocApiDocParamFieldVO(openplatformDocApiDocParamField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档参数字段创建指令创建开放接口文档参数字段模型
	 * @param openplatformDocApiDocParamFieldCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocParamField createByOpenplatformDocApiDocParamFieldCreateCommand(OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand){
		OpenplatformDocApiDocParamField openplatformDocApiDocParamField = OpenplatformDocApiDocParamField.create();
		OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping.instance.fillOpenplatformDocApiDocParamFieldByOpenplatformDocApiDocParamFieldCreateCommand(openplatformDocApiDocParamField, openplatformDocApiDocParamFieldCreateCommand);
		return openplatformDocApiDocParamField;
	}

	@Mapper
	interface  OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping{
		OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping instance = Mappers.getMapper( OpenplatformDocApiDocParamFieldCreateCommandToOpenplatformDocApiDocParamFieldMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocParamField
		 * @param openplatformDocApiDocParamFieldCreateCommand
		 */
		void fillOpenplatformDocApiDocParamFieldByOpenplatformDocApiDocParamFieldCreateCommand(@MappingTarget OpenplatformDocApiDocParamField openplatformDocApiDocParamField, OpenplatformDocApiDocParamFieldCreateCommand openplatformDocApiDocParamFieldCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocParamFieldGateway(OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway) {
		this.openplatformDocApiDocParamFieldGateway = openplatformDocApiDocParamFieldGateway;
	}
	@Autowired
	public void setiOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}
}
