package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCode;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateResponseCodeGateway;
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
 * 开放接口文档模板响应码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateResponseCodeCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateResponseCodeGateway openplatformDocApiDocTemplateResponseCodeGateway;

	/**
	 * 执行开放接口文档模板响应码添加指令
	 * @param openplatformDocApiDocTemplateResponseCodeCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> execute(@Valid OpenplatformDocApiDocTemplateResponseCodeCreateCommand openplatformDocApiDocTemplateResponseCodeCreateCommand) {
		OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode = createByOpenplatformDocApiDocTemplateResponseCodeCreateCommand(openplatformDocApiDocTemplateResponseCodeCreateCommand);
		openplatformDocApiDocTemplateResponseCode.setAddControl(openplatformDocApiDocTemplateResponseCodeCreateCommand);
		boolean save = openplatformDocApiDocTemplateResponseCodeGateway.save(openplatformDocApiDocTemplateResponseCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.toOpenplatformDocApiDocTemplateResponseCodeVO(openplatformDocApiDocTemplateResponseCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板响应码创建指令创建开放接口文档模板响应码模型
	 * @param openplatformDocApiDocTemplateResponseCodeCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateResponseCode createByOpenplatformDocApiDocTemplateResponseCodeCreateCommand(OpenplatformDocApiDocTemplateResponseCodeCreateCommand openplatformDocApiDocTemplateResponseCodeCreateCommand){
		OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode = OpenplatformDocApiDocTemplateResponseCode.create();
		OpenplatformDocApiDocTemplateResponseCodeCreateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping.instance.fillOpenplatformDocApiDocTemplateResponseCodeByOpenplatformDocApiDocTemplateResponseCodeCreateCommand(openplatformDocApiDocTemplateResponseCode, openplatformDocApiDocTemplateResponseCodeCreateCommand);
		return openplatformDocApiDocTemplateResponseCode;
	}

	@Mapper
	interface  OpenplatformDocApiDocTemplateResponseCodeCreateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping{
		OpenplatformDocApiDocTemplateResponseCodeCreateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping instance = Mappers.getMapper( OpenplatformDocApiDocTemplateResponseCodeCreateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateResponseCode
		 * @param openplatformDocApiDocTemplateResponseCodeCreateCommand
		 */
		void fillOpenplatformDocApiDocTemplateResponseCodeByOpenplatformDocApiDocTemplateResponseCodeCreateCommand(@MappingTarget OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode, OpenplatformDocApiDocTemplateResponseCodeCreateCommand openplatformDocApiDocTemplateResponseCodeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateResponseCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateResponseCodeGateway(OpenplatformDocApiDocTemplateResponseCodeGateway openplatformDocApiDocTemplateResponseCodeGateway) {
		this.openplatformDocApiDocTemplateResponseCodeGateway = openplatformDocApiDocTemplateResponseCodeGateway;
	}
}
