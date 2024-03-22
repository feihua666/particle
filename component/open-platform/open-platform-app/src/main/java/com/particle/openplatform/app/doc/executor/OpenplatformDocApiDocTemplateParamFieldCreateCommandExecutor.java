package com.particle.openplatform.app.doc.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateParamFieldCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateParamFieldGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档模板参数字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateParamFieldCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway;

	private OpenplatformDictGateway openplatformDictGateway;
	/**
	 * 执行开放接口文档模板参数字段添加指令
	 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute(@Valid OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand) {
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = createByOpenplatformDocApiDocTemplateParamFieldCreateCommand(openplatformDocApiDocTemplateParamFieldCreateCommand);

		Long dictGroupDictId = openplatformDocApiDocTemplateParamFieldCreateCommand.getDictGroupDictId();
		String dictGroupDictCode = openplatformDocApiDocTemplateParamFieldCreateCommand.getDictGroupDictCode();
		if (dictGroupDictId == null && StrUtil.isNotEmpty(dictGroupDictCode)) {
			Long idByCode = openplatformDictGateway.getIdByCode(dictGroupDictCode);
			if (idByCode != null) {
				openplatformDocApiDocTemplateParamField.changeDictGroupDictId(idByCode);
			}
		}

		openplatformDocApiDocTemplateParamField.setAddControl(openplatformDocApiDocTemplateParamFieldCreateCommand);
		boolean save = openplatformDocApiDocTemplateParamFieldGateway.save(openplatformDocApiDocTemplateParamField);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.toOpenplatformDocApiDocTemplateParamFieldVO(openplatformDocApiDocTemplateParamField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板参数字段创建指令创建开放接口文档模板参数字段模型
	 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateParamField createByOpenplatformDocApiDocTemplateParamFieldCreateCommand(OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand){
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = OpenplatformDocApiDocTemplateParamField.create();
		OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping.instance.fillOpenplatformDocApiDocTemplateParamFieldByOpenplatformDocApiDocTemplateParamFieldCreateCommand(openplatformDocApiDocTemplateParamField, openplatformDocApiDocTemplateParamFieldCreateCommand);
		return openplatformDocApiDocTemplateParamField;
	}

	@Mapper
	interface  OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping{
		OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateParamFieldCreateCommandToOpenplatformDocApiDocTemplateParamFieldMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateParamField
		 * @param openplatformDocApiDocTemplateParamFieldCreateCommand
		 */
		void fillOpenplatformDocApiDocTemplateParamFieldByOpenplatformDocApiDocTemplateParamFieldCreateCommand(@MappingTarget OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField, OpenplatformDocApiDocTemplateParamFieldCreateCommand openplatformDocApiDocTemplateParamFieldCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldGateway(OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway) {
		this.openplatformDocApiDocTemplateParamFieldGateway = openplatformDocApiDocTemplateParamFieldGateway;
	}
	/**
	 * 注入使用set方法
	 * @param openplatformDictGateway
	 */
	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}
}
