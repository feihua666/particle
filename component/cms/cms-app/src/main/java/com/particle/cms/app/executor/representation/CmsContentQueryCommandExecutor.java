package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsContentAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsContentQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.infrastructure.service.ICmsContentService;
import com.particle.cms.client.dto.command.representation.CmsContentPageQueryCommand;
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
 * 内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Component
@Validated
public class CmsContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsContentService iCmsContentService;

	/**
	 * 执行 内容 列表查询指令
	 * @param cmsContentQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsContentVO> execute(@Valid CmsContentQueryListCommand cmsContentQueryListCommand) {
		List<CmsContentDO> cmsContentDO = iCmsContentService.list(cmsContentQueryListCommand);
		List<CmsContentVO> cmsContentVOs = CmsContentAppStructMapping.instance.cmsContentDOsToCmsContentVOs(cmsContentDO);
		return MultiResponse.of(cmsContentVOs);
	}
	/**
	 * 执行 内容 分页查询指令
	 * @param cmsContentPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsContentVO> execute(@Valid CmsContentPageQueryCommand cmsContentPageQueryCommand) {
		Page<CmsContentDO> page = iCmsContentService.listPage(cmsContentPageQueryCommand);
		return CmsContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> executeDetail(IdCommand detailCommand) {
		CmsContentDO byId = iCmsContentService.getById(detailCommand.getId());
		CmsContentVO cmsContentVO = CmsContentAppStructMapping.instance.cmsContentDOToCmsContentVO(byId);
		return SingleResponse.of(cmsContentVO);
	}
	/**
	 * 执行 内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsContentDO byId = iCmsContentService.getById(detailForUpdateCommand.getId());
		CmsContentVO cmsContentVO = CmsContentAppStructMapping.instance.cmsContentDOToCmsContentVO(byId);
		return SingleResponse.of(cmsContentVO);
	}


	@Autowired
	public void setICmsContentService(ICmsContentService iCmsContentService) {
		this.iCmsContentService = iCmsContentService;
	}
}
