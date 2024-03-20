package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamFieldId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateParamFieldGateway;
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
 * 开放接口文档模板参数字段 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateParamFieldUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway;

	/**
	 * 执行 开放接口文档模板参数字段 更新指令
	 * @param openplatformDocApiDocTemplateParamFieldUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute(@Valid OpenplatformDocApiDocTemplateParamFieldUpdateCommand openplatformDocApiDocTemplateParamFieldUpdateCommand) {
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = createByOpenplatformDocApiDocTemplateParamFieldUpdateCommand(openplatformDocApiDocTemplateParamFieldUpdateCommand);
		openplatformDocApiDocTemplateParamField.setUpdateControl(openplatformDocApiDocTemplateParamFieldUpdateCommand);
		boolean save = openplatformDocApiDocTemplateParamFieldGateway.save(openplatformDocApiDocTemplateParamField);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.toOpenplatformDocApiDocTemplateParamFieldVO(openplatformDocApiDocTemplateParamField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板参数字段更新指令创建开放接口文档模板参数字段模型
	 * @param openplatformDocApiDocTemplateParamFieldUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateParamField createByOpenplatformDocApiDocTemplateParamFieldUpdateCommand(OpenplatformDocApiDocTemplateParamFieldUpdateCommand openplatformDocApiDocTemplateParamFieldUpdateCommand){
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = OpenplatformDocApiDocTemplateParamField.create();
		OpenplatformDocApiDocTemplateParamFieldUpdateCommandToOpenplatformDocApiDocTemplateParamFieldMapping.instance.fillOpenplatformDocApiDocTemplateParamFieldByOpenplatformDocApiDocTemplateParamFieldUpdateCommand(openplatformDocApiDocTemplateParamField, openplatformDocApiDocTemplateParamFieldUpdateCommand);
		return openplatformDocApiDocTemplateParamField;
	}

	@Mapper
	interface OpenplatformDocApiDocTemplateParamFieldUpdateCommandToOpenplatformDocApiDocTemplateParamFieldMapping{
		OpenplatformDocApiDocTemplateParamFieldUpdateCommandToOpenplatformDocApiDocTemplateParamFieldMapping instance = Mappers.getMapper(OpenplatformDocApiDocTemplateParamFieldUpdateCommandToOpenplatformDocApiDocTemplateParamFieldMapping.class );

		default OpenplatformDocApiDocTemplateParamFieldId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocTemplateParamFieldId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateParamField
		 * @param openplatformDocApiDocTemplateParamFieldUpdateCommand
		 */
		void fillOpenplatformDocApiDocTemplateParamFieldByOpenplatformDocApiDocTemplateParamFieldUpdateCommand(@MappingTarget OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField, OpenplatformDocApiDocTemplateParamFieldUpdateCommand openplatformDocApiDocTemplateParamFieldUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldGateway(OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway) {
		this.openplatformDocApiDocTemplateParamFieldGateway = openplatformDocApiDocTemplateParamFieldGateway;
	}
}
