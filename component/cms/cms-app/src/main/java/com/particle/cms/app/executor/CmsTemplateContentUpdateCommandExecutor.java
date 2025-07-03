package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsTemplateContentAppStructMapping;
import com.particle.cms.client.dto.command.CmsTemplateContentUpdateCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import com.particle.cms.domain.CmsTemplateContent;
import com.particle.cms.domain.CmsTemplateContentId;
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
 * 模板内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsTemplateContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsTemplateContentGateway cmsTemplateContentGateway;

	/**
	 * 执行 模板内容 更新指令
	 * @param cmsTemplateContentUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateContentVO> execute(@Valid CmsTemplateContentUpdateCommand cmsTemplateContentUpdateCommand) {
		CmsTemplateContent cmsTemplateContent = createByCmsTemplateContentUpdateCommand(cmsTemplateContentUpdateCommand);
		cmsTemplateContent.setUpdateControl(cmsTemplateContentUpdateCommand);
		boolean save = cmsTemplateContentGateway.save(cmsTemplateContent);
		if (save) {
			return SingleResponse.of(CmsTemplateContentAppStructMapping.instance.toCmsTemplateContentVO(cmsTemplateContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据模板内容更新指令创建模板内容模型
	 * @param cmsTemplateContentUpdateCommand
	 * @return
	 */
	private CmsTemplateContent createByCmsTemplateContentUpdateCommand(CmsTemplateContentUpdateCommand cmsTemplateContentUpdateCommand){
		CmsTemplateContent cmsTemplateContent = CmsTemplateContent.create();
		CmsTemplateContentUpdateCommandToCmsTemplateContentMapping.instance.fillCmsTemplateContentByCmsTemplateContentUpdateCommand(cmsTemplateContent, cmsTemplateContentUpdateCommand);
		return cmsTemplateContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsTemplateContentUpdateCommandToCmsTemplateContentMapping{
		CmsTemplateContentUpdateCommandToCmsTemplateContentMapping instance = Mappers.getMapper(CmsTemplateContentUpdateCommandToCmsTemplateContentMapping.class );

		default CmsTemplateContentId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsTemplateContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsTemplateContent
		 * @param cmsTemplateContentUpdateCommand
		 */
		void fillCmsTemplateContentByCmsTemplateContentUpdateCommand(@MappingTarget CmsTemplateContent cmsTemplateContent, CmsTemplateContentUpdateCommand cmsTemplateContentUpdateCommand);
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
