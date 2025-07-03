package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentCategoryAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentCategoryUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.domain.CmsContentCategory;
import com.particle.cms.domain.CmsContentCategoryId;
import com.particle.cms.domain.gateway.CmsContentCategoryGateway;
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
 * 内容分类 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsContentCategoryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentCategoryGateway cmsContentCategoryGateway;

	/**
	 * 执行 内容分类 更新指令
	 * @param cmsContentCategoryUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentCategoryVO> execute(@Valid CmsContentCategoryUpdateCommand cmsContentCategoryUpdateCommand) {
		CmsContentCategory cmsContentCategory = createByCmsContentCategoryUpdateCommand(cmsContentCategoryUpdateCommand);
		cmsContentCategory.setUpdateControl(cmsContentCategoryUpdateCommand);
		boolean save = cmsContentCategoryGateway.save(cmsContentCategory);
		if (save) {
			return SingleResponse.of(CmsContentCategoryAppStructMapping.instance.toCmsContentCategoryVO(cmsContentCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容分类更新指令创建内容分类模型
	 * @param cmsContentCategoryUpdateCommand
	 * @return
	 */
	private CmsContentCategory createByCmsContentCategoryUpdateCommand(CmsContentCategoryUpdateCommand cmsContentCategoryUpdateCommand){
		CmsContentCategory cmsContentCategory = CmsContentCategory.create();
		CmsContentCategoryUpdateCommandToCmsContentCategoryMapping.instance.fillCmsContentCategoryByCmsContentCategoryUpdateCommand(cmsContentCategory, cmsContentCategoryUpdateCommand);
		return cmsContentCategory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsContentCategoryUpdateCommandToCmsContentCategoryMapping{
		CmsContentCategoryUpdateCommandToCmsContentCategoryMapping instance = Mappers.getMapper(CmsContentCategoryUpdateCommandToCmsContentCategoryMapping.class );

		default CmsContentCategoryId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsContentCategoryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContentCategory
		 * @param cmsContentCategoryUpdateCommand
		 */
		void fillCmsContentCategoryByCmsContentCategoryUpdateCommand(@MappingTarget CmsContentCategory cmsContentCategory, CmsContentCategoryUpdateCommand cmsContentCategoryUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsContentCategoryGateway
	 */
	@Autowired
	public void setCmsContentCategoryGateway(CmsContentCategoryGateway cmsContentCategoryGateway) {
		this.cmsContentCategoryGateway = cmsContentCategoryGateway;
	}
}
