package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocTemplateUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateId;
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
 * 开放接口文档模板 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway;

	/**
	 * 执行 开放接口文档模板 更新指令
	 * @param openplatformDocApiDocTemplateUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateVO> execute(@Valid OpenplatformDocApiDocTemplateUpdateCommand openplatformDocApiDocTemplateUpdateCommand) {
		OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate = createByOpenplatformDocApiDocTemplateUpdateCommand(openplatformDocApiDocTemplateUpdateCommand);
		openplatformDocApiDocTemplate.setUpdateControl(openplatformDocApiDocTemplateUpdateCommand);
		boolean save = openplatformDocApiDocTemplateGateway.save(openplatformDocApiDocTemplate);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateAppStructMapping.instance.toOpenplatformDocApiDocTemplateVO(openplatformDocApiDocTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档模板更新指令创建开放接口文档模板模型
	 * @param openplatformDocApiDocTemplateUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocTemplate createByOpenplatformDocApiDocTemplateUpdateCommand(OpenplatformDocApiDocTemplateUpdateCommand openplatformDocApiDocTemplateUpdateCommand){
		OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate = OpenplatformDocApiDocTemplate.create();
		OpenplatformDocApiDocTemplateUpdateCommandToOpenplatformDocApiDocTemplateMapping.instance.fillOpenplatformDocApiDocTemplateByOpenplatformDocApiDocTemplateUpdateCommand(openplatformDocApiDocTemplate, openplatformDocApiDocTemplateUpdateCommand);
		return openplatformDocApiDocTemplate;
	}

	@Mapper
	interface OpenplatformDocApiDocTemplateUpdateCommandToOpenplatformDocApiDocTemplateMapping{
		OpenplatformDocApiDocTemplateUpdateCommandToOpenplatformDocApiDocTemplateMapping instance = Mappers.getMapper(OpenplatformDocApiDocTemplateUpdateCommandToOpenplatformDocApiDocTemplateMapping.class );

		default OpenplatformDocApiDocTemplateId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocTemplateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocTemplate
		 * @param openplatformDocApiDocTemplateUpdateCommand
		 */
		void fillOpenplatformDocApiDocTemplateByOpenplatformDocApiDocTemplateUpdateCommand(@MappingTarget OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate, OpenplatformDocApiDocTemplateUpdateCommand openplatformDocApiDocTemplateUpdateCommand);
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
