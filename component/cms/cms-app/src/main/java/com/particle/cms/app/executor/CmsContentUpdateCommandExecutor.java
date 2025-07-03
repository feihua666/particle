package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentPublicCommand;
import com.particle.cms.client.dto.command.CmsContentUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
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
 * 内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentGateway cmsContentGateway;

	/**
	 * 执行 内容 更新指令
	 * @param cmsContentUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> execute(@Valid CmsContentUpdateCommand cmsContentUpdateCommand) {
		CmsContent cmsContent = createByCmsContentUpdateCommand(cmsContentUpdateCommand);
		cmsContent.setUpdateControl(cmsContentUpdateCommand);
		boolean save = cmsContentGateway.save(cmsContent);
		if (save) {
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(cmsContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行 内容 发布指令
	 * @param cmsContentPublicCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> publish(@Valid CmsContentPublicCommand cmsContentPublicCommand) {
		CmsContent cmsContent = CmsContent.create(CmsContentId.of(cmsContentPublicCommand.getId()));
		if (cmsContentPublicCommand.getIsPublic()) {
			cmsContent.publish();
		}else {
			cmsContent.unPublish();
		}

		boolean save = cmsContentGateway.save(cmsContent);
		if (save) {
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(cmsContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据内容更新指令创建内容模型
	 * @param cmsContentUpdateCommand
	 * @return
	 */
	private CmsContent createByCmsContentUpdateCommand(CmsContentUpdateCommand cmsContentUpdateCommand){
		CmsContent cmsContent = CmsContent.create();
		CmsContentUpdateCommandToCmsContentMapping.instance.fillCmsContentByCmsContentUpdateCommand(cmsContent, cmsContentUpdateCommand);
		return cmsContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsContentUpdateCommandToCmsContentMapping{
		CmsContentUpdateCommandToCmsContentMapping instance = Mappers.getMapper(CmsContentUpdateCommandToCmsContentMapping.class );

		default CmsContentId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContent
		 * @param cmsContentUpdateCommand
		 */
		void fillCmsContentByCmsContentUpdateCommand(@MappingTarget CmsContent cmsContent, CmsContentUpdateCommand cmsContentUpdateCommand);
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
