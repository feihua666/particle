package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentMultimediaAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentMultimediaCreateCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.domain.CmsContentMultimedia;
import com.particle.cms.domain.gateway.CmsContentMultimediaGateway;
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
 * 内容多媒体 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Component
@Validated
public class CmsContentMultimediaCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentMultimediaGateway cmsContentMultimediaGateway;

	/**
	 * 执行内容多媒体添加指令
	 * @param cmsContentMultimediaCreateCommand
	 * @return
	 */
	public SingleResponse<CmsContentMultimediaVO> execute(@Valid CmsContentMultimediaCreateCommand cmsContentMultimediaCreateCommand) {
		CmsContentMultimedia cmsContentMultimedia = createByCmsContentMultimediaCreateCommand(cmsContentMultimediaCreateCommand);
		cmsContentMultimedia.setAddControl(cmsContentMultimediaCreateCommand);
		boolean save = cmsContentMultimediaGateway.save(cmsContentMultimedia);
		if (save) {
			return SingleResponse.of(CmsContentMultimediaAppStructMapping.instance.toCmsContentMultimediaVO(cmsContentMultimedia));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容多媒体创建指令创建内容多媒体模型
	 * @param cmsContentMultimediaCreateCommand
	 * @return
	 */
	private CmsContentMultimedia createByCmsContentMultimediaCreateCommand(CmsContentMultimediaCreateCommand cmsContentMultimediaCreateCommand){
		CmsContentMultimedia cmsContentMultimedia = CmsContentMultimedia.create();
		CmsContentMultimediaCreateCommandToCmsContentMultimediaMapping.instance.fillCmsContentMultimediaByCmsContentMultimediaCreateCommand(cmsContentMultimedia, cmsContentMultimediaCreateCommand);
		return cmsContentMultimedia;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsContentMultimediaCreateCommandToCmsContentMultimediaMapping{
		CmsContentMultimediaCreateCommandToCmsContentMultimediaMapping instance = Mappers.getMapper( CmsContentMultimediaCreateCommandToCmsContentMultimediaMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContentMultimedia
		 * @param cmsContentMultimediaCreateCommand
		 */
		void fillCmsContentMultimediaByCmsContentMultimediaCreateCommand(@MappingTarget CmsContentMultimedia cmsContentMultimedia, CmsContentMultimediaCreateCommand cmsContentMultimediaCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsContentMultimediaGateway
	 */
	@Autowired
	public void setCmsContentMultimediaGateway(CmsContentMultimediaGateway cmsContentMultimediaGateway) {
		this.cmsContentMultimediaGateway = cmsContentMultimediaGateway;
	}
}
