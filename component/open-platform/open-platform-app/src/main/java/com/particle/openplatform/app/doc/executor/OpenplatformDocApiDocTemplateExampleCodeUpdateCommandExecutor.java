package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateExampleCodeGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档模板示例代码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateExampleCodeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateExampleCodeGateway openplatformDocApiDocTemplateExampleCodeGateway;

	/**
	 * 执行 开放接口文档模板示例代码 更新指令
	 * @param openplatformDocApiDocTemplateExampleCodeUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> execute(@Valid OpenplatformDocApiDocTemplateExampleCodeUpdateCommand openplatformDocApiDocTemplateExampleCodeUpdateCommand) {
		OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode = createByOpenplatformDocApiDocTemplateExampleCodeUpdateCommand(openplatformDocApiDocTemplateExampleCodeUpdateCommand);
		openplatformDocApiDocTemplateExampleCode.setUpdateControl(openplatformDocApiDocTemplateExampleCodeUpdateCommand);
		boolean save = openplatformDocApiDocTemplateExampleCodeGateway.save(openplatformDocApiDocTemplateExampleCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.toOpenplatformDocApiDocTemplateExampleCodeVO(openplatformDocApiDocTemplateExampleCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板示例代码更新指令创建开放接口文档模板示例代码模型
	 * @param openplatformDocApiDocTemplateExampleCodeUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateExampleCode createByOpenplatformDocApiDocTemplateExampleCodeUpdateCommand(OpenplatformDocApiDocTemplateExampleCodeUpdateCommand openplatformDocApiDocTemplateExampleCodeUpdateCommand){
		OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode = OpenplatformDocApiDocTemplateExampleCode.create();
		OpenplatformDocApiDocTemplateExampleCodeUpdateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping.instance.fillOpenplatformDocApiDocTemplateExampleCodeByOpenplatformDocApiDocTemplateExampleCodeUpdateCommand(openplatformDocApiDocTemplateExampleCode, openplatformDocApiDocTemplateExampleCodeUpdateCommand);
		return openplatformDocApiDocTemplateExampleCode;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformDocApiDocTemplateExampleCodeUpdateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping{
		OpenplatformDocApiDocTemplateExampleCodeUpdateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping instance = Mappers.getMapper(OpenplatformDocApiDocTemplateExampleCodeUpdateCommandToOpenplatformDocApiDocTemplateExampleCodeMapping.class );

		default OpenplatformDocApiDocTemplateExampleCodeId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocTemplateExampleCodeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateExampleCode
		 * @param openplatformDocApiDocTemplateExampleCodeUpdateCommand
		 */
		void fillOpenplatformDocApiDocTemplateExampleCodeByOpenplatformDocApiDocTemplateExampleCodeUpdateCommand(@MappingTarget OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode, OpenplatformDocApiDocTemplateExampleCodeUpdateCommand openplatformDocApiDocTemplateExampleCodeUpdateCommand);
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
