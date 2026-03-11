package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsTemplateAppStructMapping;
import com.particle.cms.client.dto.command.CmsTemplateUpdateCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import com.particle.cms.domain.CmsTemplate;
import com.particle.cms.domain.CmsTemplateId;
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
 * 模板 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsTemplateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateGateway cmsTemplateGateway;

	/**
	 * 执行 模板 更新指令
	 * @param cmsTemplateUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateVO> execute(@Valid CmsTemplateUpdateCommand cmsTemplateUpdateCommand) {
		CmsTemplate cmsTemplate = createByCmsTemplateUpdateCommand(cmsTemplateUpdateCommand);
		cmsTemplate.initForUpdate();
		cmsTemplate.setUpdateControl(cmsTemplateUpdateCommand);
		boolean save = cmsTemplateGateway.save(cmsTemplate);
		if (save) {
			return SingleResponse.of(CmsTemplateAppStructMapping.instance.toCmsTemplateVO(cmsTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据模板更新指令创建模板模型
	 * @param cmsTemplateUpdateCommand
	 * @return
	 */
	private CmsTemplate createByCmsTemplateUpdateCommand(CmsTemplateUpdateCommand cmsTemplateUpdateCommand){
		CmsTemplate cmsTemplate = CmsTemplate.create();
		CmsTemplateUpdateCommandToCmsTemplateMapping.instance.fillCmsTemplateByCmsTemplateUpdateCommand(cmsTemplate, cmsTemplateUpdateCommand);
		return cmsTemplate;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsTemplateUpdateCommandToCmsTemplateMapping{
		CmsTemplateUpdateCommandToCmsTemplateMapping instance = Mappers.getMapper(CmsTemplateUpdateCommandToCmsTemplateMapping.class );

		default CmsTemplateId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsTemplateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsTemplate
		 * @param cmsTemplateUpdateCommand
		 */
		void fillCmsTemplateByCmsTemplateUpdateCommand(@MappingTarget CmsTemplate cmsTemplate, CmsTemplateUpdateCommand cmsTemplateUpdateCommand);
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
