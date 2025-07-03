package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsContentCategoryAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;
import com.particle.cms.infrastructure.service.ICmsContentCategoryService;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 内容分类 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Component
@Validated
public class CmsContentCategoryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsContentCategoryService iCmsContentCategoryService;

	/**
	 * 执行 内容分类 列表查询指令
	 * @param cmsContentCategoryQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsContentCategoryVO> execute(@Valid CmsContentCategoryQueryListCommand cmsContentCategoryQueryListCommand) {
		List<CmsContentCategoryDO> cmsContentCategoryDO = iCmsContentCategoryService.list(cmsContentCategoryQueryListCommand);
		List<CmsContentCategoryVO> cmsContentCategoryVOs = CmsContentCategoryAppStructMapping.instance.cmsContentCategoryDOsToCmsContentCategoryVOs(cmsContentCategoryDO);
		return MultiResponse.of(cmsContentCategoryVOs);
	}
	/**
	 * 执行 内容分类 分页查询指令
	 * @param cmsContentCategoryPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsContentCategoryVO> execute(@Valid CmsContentCategoryPageQueryCommand cmsContentCategoryPageQueryCommand) {
		Page<CmsContentCategoryDO> page = iCmsContentCategoryService.listPage(cmsContentCategoryPageQueryCommand);
		return CmsContentCategoryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 内容分类 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsContentCategoryVO> executeDetail(IdCommand detailCommand) {
		CmsContentCategoryDO byId = iCmsContentCategoryService.getById(detailCommand.getId());
		CmsContentCategoryVO cmsContentCategoryVO = CmsContentCategoryAppStructMapping.instance.cmsContentCategoryDOToCmsContentCategoryVO(byId);
		return SingleResponse.of(cmsContentCategoryVO);
	}
	/**
	 * 执行 内容分类 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentCategoryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsContentCategoryDO byId = iCmsContentCategoryService.getById(detailForUpdateCommand.getId());
		CmsContentCategoryVO cmsContentCategoryVO = CmsContentCategoryAppStructMapping.instance.cmsContentCategoryDOToCmsContentCategoryVO(byId);
		return SingleResponse.of(cmsContentCategoryVO);
	}


	@Autowired
	public void setICmsContentCategoryService(ICmsContentCategoryService iCmsContentCategoryService) {
		this.iCmsContentCategoryService = iCmsContentCategoryService;
	}
}
