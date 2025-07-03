package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentMultimediaAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentMultimediaUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.domain.CmsContentMultimedia;
import com.particle.cms.domain.CmsContentMultimediaId;
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
 * 内容多媒体 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsContentMultimediaUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentMultimediaGateway cmsContentMultimediaGateway;

	/**
	 * 执行 内容多媒体 更新指令
	 * @param cmsContentMultimediaUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentMultimediaVO> execute(@Valid CmsContentMultimediaUpdateCommand cmsContentMultimediaUpdateCommand) {
		CmsContentMultimedia cmsContentMultimedia = createByCmsContentMultimediaUpdateCommand(cmsContentMultimediaUpdateCommand);
		cmsContentMultimedia.setUpdateControl(cmsContentMultimediaUpdateCommand);
		boolean save = cmsContentMultimediaGateway.save(cmsContentMultimedia);
		if (save) {
			return SingleResponse.of(CmsContentMultimediaAppStructMapping.instance.toCmsContentMultimediaVO(cmsContentMultimedia));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容多媒体更新指令创建内容多媒体模型
	 * @param cmsContentMultimediaUpdateCommand
	 * @return
	 */
	private CmsContentMultimedia createByCmsContentMultimediaUpdateCommand(CmsContentMultimediaUpdateCommand cmsContentMultimediaUpdateCommand){
		CmsContentMultimedia cmsContentMultimedia = CmsContentMultimedia.create();
		CmsContentMultimediaUpdateCommandToCmsContentMultimediaMapping.instance.fillCmsContentMultimediaByCmsContentMultimediaUpdateCommand(cmsContentMultimedia, cmsContentMultimediaUpdateCommand);
		return cmsContentMultimedia;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsContentMultimediaUpdateCommandToCmsContentMultimediaMapping{
		CmsContentMultimediaUpdateCommandToCmsContentMultimediaMapping instance = Mappers.getMapper(CmsContentMultimediaUpdateCommandToCmsContentMultimediaMapping.class );

		default CmsContentMultimediaId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsContentMultimediaId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContentMultimedia
		 * @param cmsContentMultimediaUpdateCommand
		 */
		void fillCmsContentMultimediaByCmsContentMultimediaUpdateCommand(@MappingTarget CmsContentMultimedia cmsContentMultimedia, CmsContentMultimediaUpdateCommand cmsContentMultimediaUpdateCommand);
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
