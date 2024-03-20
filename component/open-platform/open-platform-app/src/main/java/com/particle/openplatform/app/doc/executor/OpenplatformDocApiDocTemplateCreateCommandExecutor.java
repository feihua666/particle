package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateGateway;
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
 * 开放接口文档模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway;

	/**
	 * 执行开放接口文档模板添加指令
	 * @param openplatformDocApiDocTemplateCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateVO> execute(@Valid OpenplatformDocApiDocTemplateCreateCommand openplatformDocApiDocTemplateCreateCommand) {
		OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate = createByOpenplatformDocApiDocTemplateCreateCommand(openplatformDocApiDocTemplateCreateCommand);
		openplatformDocApiDocTemplate.setAddControl(openplatformDocApiDocTemplateCreateCommand);
		boolean save = openplatformDocApiDocTemplateGateway.save(openplatformDocApiDocTemplate);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateAppStructMapping.instance.toOpenplatformDocApiDocTemplateVO(openplatformDocApiDocTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板创建指令创建开放接口文档模板模型
	 * @param openplatformDocApiDocTemplateCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplate createByOpenplatformDocApiDocTemplateCreateCommand(OpenplatformDocApiDocTemplateCreateCommand openplatformDocApiDocTemplateCreateCommand){
		OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate = OpenplatformDocApiDocTemplate.create();
		OpenplatformDocApiDocTemplateCreateCommandToOpenplatformDocApiDocTemplateMapping.instance.fillOpenplatformDocApiDocTemplateByOpenplatformDocApiDocTemplateCreateCommand(openplatformDocApiDocTemplate, openplatformDocApiDocTemplateCreateCommand);
		return openplatformDocApiDocTemplate;
	}

	@Mapper
	interface  OpenplatformDocApiDocTemplateCreateCommandToOpenplatformDocApiDocTemplateMapping{
		OpenplatformDocApiDocTemplateCreateCommandToOpenplatformDocApiDocTemplateMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateCreateCommandToOpenplatformDocApiDocTemplateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplate
		 * @param openplatformDocApiDocTemplateCreateCommand
		 */
		void fillOpenplatformDocApiDocTemplateByOpenplatformDocApiDocTemplateCreateCommand(@MappingTarget OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate, OpenplatformDocApiDocTemplateCreateCommand openplatformDocApiDocTemplateCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateGateway(OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway) {
		this.openplatformDocApiDocTemplateGateway = openplatformDocApiDocTemplateGateway;
	}
}
