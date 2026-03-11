package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsTemplateAppStructMapping;
import com.particle.cms.client.dto.command.CmsTemplateCreateCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import com.particle.cms.domain.CmsTemplate;
import com.particle.cms.domain.gateway.CmsTemplateGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
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
 * 模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Component
@Validated
public class CmsTemplateCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateGateway cmsTemplateGateway;

	/**
	 * 执行模板添加指令
	 * @param cmsTemplateCreateCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateVO> execute(@Valid CmsTemplateCreateCommand cmsTemplateCreateCommand) {
		CmsTemplate cmsTemplate = createByCmsTemplateCreateCommand(cmsTemplateCreateCommand);
		cmsTemplate.initForAdd();
		cmsTemplate.setAddControl(cmsTemplateCreateCommand);
		boolean save = cmsTemplateGateway.save(cmsTemplate);
		if (save) {
			return SingleResponse.of(CmsTemplateAppStructMapping.instance.toCmsTemplateVO(cmsTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据模板创建指令创建模板模型
	 * @param cmsTemplateCreateCommand
	 * @return
	 */
	private CmsTemplate createByCmsTemplateCreateCommand(CmsTemplateCreateCommand cmsTemplateCreateCommand){
		CmsTemplate cmsTemplate = CmsTemplate.create();
		CmsTemplateCreateCommandToCmsTemplateMapping.instance.fillCmsTemplateByCmsTemplateCreateCommand(cmsTemplate, cmsTemplateCreateCommand);
		return cmsTemplate;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsTemplateCreateCommandToCmsTemplateMapping{
		CmsTemplateCreateCommandToCmsTemplateMapping instance = Mappers.getMapper( CmsTemplateCreateCommandToCmsTemplateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsTemplate
		 * @param cmsTemplateCreateCommand
		 */
		void fillCmsTemplateByCmsTemplateCreateCommand(@MappingTarget CmsTemplate cmsTemplate, CmsTemplateCreateCommand cmsTemplateCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsTemplateGateway
	 */
	@Autowired
	public void setCmsTemplateGateway(CmsTemplateGateway cmsTemplateGateway) {
		this.cmsTemplateGateway = cmsTemplateGateway;
	}
}
