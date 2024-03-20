package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCodeId;
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
 * 开放接口文档模板响应码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateResponseCodeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateResponseCodeGateway openplatformDocApiDocTemplateResponseCodeGateway;

	/**
	 * 执行 开放接口文档模板响应码 更新指令
	 * @param openplatformDocApiDocTemplateResponseCodeUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> execute(@Valid OpenplatformDocApiDocTemplateResponseCodeUpdateCommand openplatformDocApiDocTemplateResponseCodeUpdateCommand) {
		OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode = createByOpenplatformDocApiDocTemplateResponseCodeUpdateCommand(openplatformDocApiDocTemplateResponseCodeUpdateCommand);
		openplatformDocApiDocTemplateResponseCode.setUpdateControl(openplatformDocApiDocTemplateResponseCodeUpdateCommand);
		boolean save = openplatformDocApiDocTemplateResponseCodeGateway.save(openplatformDocApiDocTemplateResponseCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.toOpenplatformDocApiDocTemplateResponseCodeVO(openplatformDocApiDocTemplateResponseCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板响应码更新指令创建开放接口文档模板响应码模型
	 * @param openplatformDocApiDocTemplateResponseCodeUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplateResponseCode createByOpenplatformDocApiDocTemplateResponseCodeUpdateCommand(OpenplatformDocApiDocTemplateResponseCodeUpdateCommand openplatformDocApiDocTemplateResponseCodeUpdateCommand){
		OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode = OpenplatformDocApiDocTemplateResponseCode.create();
		OpenplatformDocApiDocTemplateResponseCodeUpdateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping.instance.fillOpenplatformDocApiDocTemplateResponseCodeByOpenplatformDocApiDocTemplateResponseCodeUpdateCommand(openplatformDocApiDocTemplateResponseCode, openplatformDocApiDocTemplateResponseCodeUpdateCommand);
		return openplatformDocApiDocTemplateResponseCode;
	}

	@Mapper
	interface OpenplatformDocApiDocTemplateResponseCodeUpdateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping{
		OpenplatformDocApiDocTemplateResponseCodeUpdateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping instance = Mappers.getMapper(OpenplatformDocApiDocTemplateResponseCodeUpdateCommandToOpenplatformDocApiDocTemplateResponseCodeMapping.class );

		default OpenplatformDocApiDocTemplateResponseCodeId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocTemplateResponseCodeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplateResponseCode
		 * @param openplatformDocApiDocTemplateResponseCodeUpdateCommand
		 */
		void fillOpenplatformDocApiDocTemplateResponseCodeByOpenplatformDocApiDocTemplateResponseCodeUpdateCommand(@MappingTarget OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode, OpenplatformDocApiDocTemplateResponseCodeUpdateCommand openplatformDocApiDocTemplateResponseCodeUpdateCommand);
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
