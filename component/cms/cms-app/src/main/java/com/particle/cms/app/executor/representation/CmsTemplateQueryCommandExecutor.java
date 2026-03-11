package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsTemplateAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsTemplateQueryListCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.cms.infrastructure.service.ICmsTemplateService;
import com.particle.cms.client.dto.command.representation.CmsTemplatePageQueryCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
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
 * 模板 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Component
@Validated
public class CmsTemplateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsTemplateService iCmsTemplateService;

	/**
	 * 执行 模板 列表查询指令
	 * @param cmsTemplateQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsTemplateVO> execute(@Valid CmsTemplateQueryListCommand cmsTemplateQueryListCommand) {
		List<CmsTemplateDO> cmsTemplateDO = iCmsTemplateService.list(cmsTemplateQueryListCommand);
		List<CmsTemplateVO> cmsTemplateVOs = CmsTemplateAppStructMapping.instance.cmsTemplateDOsToCmsTemplateVOs(cmsTemplateDO);
		return MultiResponse.of(cmsTemplateVOs);
	}
	/**
	 * 执行 模板 分页查询指令
	 * @param cmsTemplatePageQueryCommand
	 * @return
	 */
	public PageResponse<CmsTemplateVO> execute(@Valid CmsTemplatePageQueryCommand cmsTemplatePageQueryCommand) {
		Page<CmsTemplateDO> page = iCmsTemplateService.listPage(cmsTemplatePageQueryCommand);
		return CmsTemplateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 模板 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateVO> executeDetail(CommonIdCommand detailCommand) {
		CmsTemplateDO byId = iCmsTemplateService.getById(detailCommand.getId());
		CmsTemplateVO cmsTemplateVO = CmsTemplateAppStructMapping.instance.cmsTemplateDOToCmsTemplateVO(byId);
		return SingleResponse.of(cmsTemplateVO);
	}
	/**
	 * 执行 模板 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		CmsTemplateDO byId = iCmsTemplateService.getById(detailForUpdateCommand.getId());
		CmsTemplateVO cmsTemplateVO = CmsTemplateAppStructMapping.instance.cmsTemplateDOToCmsTemplateVO(byId);
		return SingleResponse.of(cmsTemplateVO);
	}


	@Autowired
	public void setICmsTemplateService(ICmsTemplateService iCmsTemplateService) {
		this.iCmsTemplateService = iCmsTemplateService;
	}
}
