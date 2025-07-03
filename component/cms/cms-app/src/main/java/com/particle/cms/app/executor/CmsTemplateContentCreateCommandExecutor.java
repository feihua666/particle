package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsTemplateContentAppStructMapping;
import com.particle.cms.client.dto.command.CmsTemplateContentCreateCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import com.particle.cms.domain.CmsTemplateContent;
import com.particle.cms.domain.gateway.CmsTemplateContentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 模板内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Component
@Validated
public class CmsTemplateContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateContentGateway cmsTemplateContentGateway;

	/**
	 * 执行模板内容添加指令
	 * @param cmsTemplateContentCreateCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateContentVO> execute(@Valid CmsTemplateContentCreateCommand cmsTemplateContentCreateCommand) {
		CmsTemplateContent cmsTemplateContent = createByCmsTemplateContentCreateCommand(cmsTemplateContentCreateCommand);
		cmsTemplateContent.setAddControl(cmsTemplateContentCreateCommand);
		boolean save = cmsTemplateContentGateway.save(cmsTemplateContent);
		if (save) {
			return SingleResponse.of(CmsTemplateContentAppStructMapping.instance.toCmsTemplateContentVO(cmsTemplateContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据模板内容创建指令创建模板内容模型
	 * @param cmsTemplateContentCreateCommand
	 * @return
	 */
	private CmsTemplateContent createByCmsTemplateContentCreateCommand(CmsTemplateContentCreateCommand cmsTemplateContentCreateCommand){
		CmsTemplateContent cmsTemplateContent = CmsTemplateContent.create();
		CmsTemplateContentCreateCommandToCmsTemplateContentMapping.instance.fillCmsTemplateContentByCmsTemplateContentCreateCommand(cmsTemplateContent, cmsTemplateContentCreateCommand);
		return cmsTemplateContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsTemplateContentCreateCommandToCmsTemplateContentMapping{
		CmsTemplateContentCreateCommandToCmsTemplateContentMapping instance = Mappers.getMapper( CmsTemplateContentCreateCommandToCmsTemplateContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsTemplateContent
		 * @param cmsTemplateContentCreateCommand
		 */
		void fillCmsTemplateContentByCmsTemplateContentCreateCommand(@MappingTarget CmsTemplateContent cmsTemplateContent, CmsTemplateContentCreateCommand cmsTemplateContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsTemplateContentGateway
	 */
	@Autowired
	public void setCmsTemplateContentGateway(CmsTemplateContentGateway cmsTemplateContentGateway) {
		this.cmsTemplateContentGateway = cmsTemplateContentGateway;
	}
}
