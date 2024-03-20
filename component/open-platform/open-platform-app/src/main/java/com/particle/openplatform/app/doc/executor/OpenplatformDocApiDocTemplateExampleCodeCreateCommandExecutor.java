package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCode;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateExampleCodeGateway;
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
 * 开放接口文档模板示例代码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateExampleCodeCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateExampleCodeGateway openplatformDocApiDocTemplateExampleCodeGateway;

	/**
	 * 执行开放接口文档模板示例代码添加指令
	 * @param openplatformDocApiDocTemplateExampleCodeCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> execute(@Valid OpenplatformDocApiDocTemplateExampleCodeCreateCommand openplatformDocApiDocTemplateExampleCodeCreateCommand) {
		OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode = createByOpenplatformDocApiDocTemplateExampleCodeCreateCommand(openplatformDocApiDocTemplateExampleCodeCreateCommand);
		openplatformDocApiDocTemplateExampleCode.setAddControl(openplatformDocApiDocTemplateExampleCodeCreateCommand);
		boolean save = openplatformDocApiDocTemplateExampleCodeGateway.save(openplatformDocApiDocTemplateExampleCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.toOpenplatformDocApiDocTemplateExampleCodeVO(openplatformDocApiDocTemplateExampleCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板示例代码创建指令创建开放接口文档模板示例代码模型
	 * @param openplatformDocApiDocTemplateExampleCodeCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateExampleCode createByOpenplatformDocApiDocTemplateExampleCodeCreateCommand(OpenplatformDocApiDocTemplateExampleCodeCreateCommand openplatformDocApiDocTemplateExampleCodeCreateCommand){
		OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode = OpenplatformDocApiDocTemplateExampleCode.create();
		OpenplatformDocApiDocTemplateExampleCodeCreateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping.instance.fillOpenplatformDocApiDocTemplateExampleCodeByOpenplatformDocApiDocTemplateExampleCodeCreateCommand(openplatformDocApiDocTemplateExampleCode, openplatformDocApiDocTemplateExampleCodeCreateCommand);
		return openplatformDocApiDocTemplateExampleCode;
	}

	@Mapper
	interface  OpenplatformDocApiDocTemplateExampleCodeCreateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping{
		OpenplatformDocApiDocTemplateExampleCodeCreateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateExampleCodeCreateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateExampleCode
		 * @param openplatformDocApiDocTemplateExampleCodeCreateCommand
		 */
		void fillOpenplatformDocApiDocTemplateExampleCodeByOpenplatformDocApiDocTemplateExampleCodeCreateCommand(@MappingTarget OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode, OpenplatformDocApiDocTemplateExampleCodeCreateCommand openplatformDocApiDocTemplateExampleCodeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateExampleCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateExampleCodeGateway(OpenplatformDocApiDocTemplateExampleCodeGateway openplatformDocApiDocTemplateExampleCodeGateway) {
		this.openplatformDocApiDocTemplateExampleCodeGateway = openplatformDocApiDocTemplateExampleCodeGateway;
	}
}
