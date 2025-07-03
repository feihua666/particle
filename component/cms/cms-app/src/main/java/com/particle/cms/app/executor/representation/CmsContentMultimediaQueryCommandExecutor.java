package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsContentMultimediaAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;
import com.particle.cms.infrastructure.service.ICmsContentMultimediaService;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaPageQueryCommand;
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
 * 内容多媒体 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Component
@Validated
public class CmsContentMultimediaQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsContentMultimediaService iCmsContentMultimediaService;

	/**
	 * 执行 内容多媒体 列表查询指令
	 * @param cmsContentMultimediaQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsContentMultimediaVO> execute(@Valid CmsContentMultimediaQueryListCommand cmsContentMultimediaQueryListCommand) {
		List<CmsContentMultimediaDO> cmsContentMultimediaDO = iCmsContentMultimediaService.list(cmsContentMultimediaQueryListCommand);
		List<CmsContentMultimediaVO> cmsContentMultimediaVOs = CmsContentMultimediaAppStructMapping.instance.cmsContentMultimediaDOsToCmsContentMultimediaVOs(cmsContentMultimediaDO);
		return MultiResponse.of(cmsContentMultimediaVOs);
	}
	/**
	 * 执行 内容多媒体 分页查询指令
	 * @param cmsContentMultimediaPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsContentMultimediaVO> execute(@Valid CmsContentMultimediaPageQueryCommand cmsContentMultimediaPageQueryCommand) {
		Page<CmsContentMultimediaDO> page = iCmsContentMultimediaService.listPage(cmsContentMultimediaPageQueryCommand);
		return CmsContentMultimediaAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 内容多媒体 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsContentMultimediaVO> executeDetail(IdCommand detailCommand) {
		CmsContentMultimediaDO byId = iCmsContentMultimediaService.getById(detailCommand.getId());
		CmsContentMultimediaVO cmsContentMultimediaVO = CmsContentMultimediaAppStructMapping.instance.cmsContentMultimediaDOToCmsContentMultimediaVO(byId);
		return SingleResponse.of(cmsContentMultimediaVO);
	}
	/**
	 * 执行 内容多媒体 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentMultimediaVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsContentMultimediaDO byId = iCmsContentMultimediaService.getById(detailForUpdateCommand.getId());
		CmsContentMultimediaVO cmsContentMultimediaVO = CmsContentMultimediaAppStructMapping.instance.cmsContentMultimediaDOToCmsContentMultimediaVO(byId);
		return SingleResponse.of(cmsContentMultimediaVO);
	}


	@Autowired
	public void setICmsContentMultimediaService(ICmsContentMultimediaService iCmsContentMultimediaService) {
		this.iCmsContentMultimediaService = iCmsContentMultimediaService;
	}
}
