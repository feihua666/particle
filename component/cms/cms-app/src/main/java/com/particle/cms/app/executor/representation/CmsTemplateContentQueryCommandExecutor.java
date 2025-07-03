package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsTemplateContentAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentQueryListCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;
import com.particle.cms.infrastructure.service.ICmsTemplateContentService;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentPageQueryCommand;
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
 * 模板内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Component
@Validated
public class CmsTemplateContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsTemplateContentService iCmsTemplateContentService;

	/**
	 * 执行 模板内容 列表查询指令
	 * @param cmsTemplateContentQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsTemplateContentVO> execute(@Valid CmsTemplateContentQueryListCommand cmsTemplateContentQueryListCommand) {
		List<CmsTemplateContentDO> cmsTemplateContentDO = iCmsTemplateContentService.list(cmsTemplateContentQueryListCommand);
		List<CmsTemplateContentVO> cmsTemplateContentVOs = CmsTemplateContentAppStructMapping.instance.cmsTemplateContentDOsToCmsTemplateContentVOs(cmsTemplateContentDO);
		return MultiResponse.of(cmsTemplateContentVOs);
	}
	/**
	 * 执行 模板内容 分页查询指令
	 * @param cmsTemplateContentPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsTemplateContentVO> execute(@Valid CmsTemplateContentPageQueryCommand cmsTemplateContentPageQueryCommand) {
		Page<CmsTemplateContentDO> page = iCmsTemplateContentService.listPage(cmsTemplateContentPageQueryCommand);
		return CmsTemplateContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 模板内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateContentVO> executeDetail(IdCommand detailCommand) {
		CmsTemplateContentDO byId = iCmsTemplateContentService.getById(detailCommand.getId());
		CmsTemplateContentVO cmsTemplateContentVO = CmsTemplateContentAppStructMapping.instance.cmsTemplateContentDOToCmsTemplateContentVO(byId);
		return SingleResponse.of(cmsTemplateContentVO);
	}
	/**
	 * 执行 模板内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsTemplateContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsTemplateContentDO byId = iCmsTemplateContentService.getById(detailForUpdateCommand.getId());
		CmsTemplateContentVO cmsTemplateContentVO = CmsTemplateContentAppStructMapping.instance.cmsTemplateContentDOToCmsTemplateContentVO(byId);
		return SingleResponse.of(cmsTemplateContentVO);
	}


	@Autowired
	public void setICmsTemplateContentService(ICmsTemplateContentService iCmsTemplateContentService) {
		this.iCmsTemplateContentService = iCmsTemplateContentService;
	}
}
