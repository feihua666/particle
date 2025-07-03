package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentCategoryAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentCategoryCreateCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.domain.CmsContentCategory;
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
 * 内容分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Component
@Validated
public class CmsContentCategoryCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentCategoryGateway cmsContentCategoryGateway;

	/**
	 * 执行内容分类添加指令
	 * @param cmsContentCategoryCreateCommand
	 * @return
	 */
	public SingleResponse<CmsContentCategoryVO> execute(@Valid CmsContentCategoryCreateCommand cmsContentCategoryCreateCommand) {
		CmsContentCategory cmsContentCategory = createByCmsContentCategoryCreateCommand(cmsContentCategoryCreateCommand);
		cmsContentCategory.setAddControl(cmsContentCategoryCreateCommand);
		boolean save = cmsContentCategoryGateway.save(cmsContentCategory);
		if (save) {
			return SingleResponse.of(CmsContentCategoryAppStructMapping.instance.toCmsContentCategoryVO(cmsContentCategory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容分类创建指令创建内容分类模型
	 * @param cmsContentCategoryCreateCommand
	 * @return
	 */
	private CmsContentCategory createByCmsContentCategoryCreateCommand(CmsContentCategoryCreateCommand cmsContentCategoryCreateCommand){
		CmsContentCategory cmsContentCategory = CmsContentCategory.create();
		CmsContentCategoryCreateCommandToCmsContentCategoryMapping.instance.fillCmsContentCategoryByCmsContentCategoryCreateCommand(cmsContentCategory, cmsContentCategoryCreateCommand);
		return cmsContentCategory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsContentCategoryCreateCommandToCmsContentCategoryMapping{
		CmsContentCategoryCreateCommandToCmsContentCategoryMapping instance = Mappers.getMapper( CmsContentCategoryCreateCommandToCmsContentCategoryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContentCategory
		 * @param cmsContentCategoryCreateCommand
		 */
		void fillCmsContentCategoryByCmsContentCategoryCreateCommand(@MappingTarget CmsContentCategory cmsContentCategory, CmsContentCategoryCreateCommand cmsContentCategoryCreateCommand);
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
