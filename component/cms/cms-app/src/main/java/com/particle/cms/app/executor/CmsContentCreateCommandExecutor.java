package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentCreateCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.gateway.CmsContentGateway;
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
 * 内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Component
@Validated
public class CmsContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentGateway cmsContentGateway;

	/**
	 * 执行内容添加指令
	 * @param cmsContentCreateCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> execute(@Valid CmsContentCreateCommand cmsContentCreateCommand) {
		CmsContent cmsContent = createByCmsContentCreateCommand(cmsContentCreateCommand);
		cmsContent.initForAdd();
		cmsContent.setAddControl(cmsContentCreateCommand);
		boolean save = cmsContentGateway.save(cmsContent);
		if (save) {
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(cmsContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容创建指令创建内容模型
	 * @param cmsContentCreateCommand
	 * @return
	 */
	private CmsContent createByCmsContentCreateCommand(CmsContentCreateCommand cmsContentCreateCommand){
		CmsContent cmsContent = CmsContent.create();
		CmsContentCreateCommandToCmsContentMapping.instance.fillCmsContentByCmsContentCreateCommand(cmsContent, cmsContentCreateCommand);
		return cmsContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsContentCreateCommandToCmsContentMapping{
		CmsContentCreateCommandToCmsContentMapping instance = Mappers.getMapper( CmsContentCreateCommandToCmsContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContent
		 * @param cmsContentCreateCommand
		 */
		void fillCmsContentByCmsContentCreateCommand(@MappingTarget CmsContent cmsContent, CmsContentCreateCommand cmsContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsContentGateway
	 */
	@Autowired
	public void setCmsContentGateway(CmsContentGateway cmsContentGateway) {
		this.cmsContentGateway = cmsContentGateway;
	}
}
