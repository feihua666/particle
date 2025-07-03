package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsChannelAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsChannelQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.infrastructure.service.ICmsChannelService;
import com.particle.cms.client.dto.command.representation.CmsChannelPageQueryCommand;
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
 * 栏目 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Component
@Validated
public class CmsChannelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsChannelService iCmsChannelService;

	/**
	 * 执行 栏目 列表查询指令
	 * @param cmsChannelQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsChannelVO> execute(@Valid CmsChannelQueryListCommand cmsChannelQueryListCommand) {
		List<CmsChannelDO> cmsChannelDO = iCmsChannelService.list(cmsChannelQueryListCommand);
		List<CmsChannelVO> cmsChannelVOs = CmsChannelAppStructMapping.instance.cmsChannelDOsToCmsChannelVOs(cmsChannelDO);
		return MultiResponse.of(cmsChannelVOs);
	}
	/**
	 * 执行 栏目 分页查询指令
	 * @param cmsChannelPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsChannelVO> execute(@Valid CmsChannelPageQueryCommand cmsChannelPageQueryCommand) {
		Page<CmsChannelDO> page = iCmsChannelService.listPage(cmsChannelPageQueryCommand);
		return CmsChannelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 栏目 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsChannelVO> executeDetail(IdCommand detailCommand) {
		CmsChannelDO byId = iCmsChannelService.getById(detailCommand.getId());
		CmsChannelVO cmsChannelVO = CmsChannelAppStructMapping.instance.cmsChannelDOToCmsChannelVO(byId);
		return SingleResponse.of(cmsChannelVO);
	}
	/**
	 * 执行 栏目 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsChannelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsChannelDO byId = iCmsChannelService.getById(detailForUpdateCommand.getId());
		CmsChannelVO cmsChannelVO = CmsChannelAppStructMapping.instance.cmsChannelDOToCmsChannelVO(byId);
		return SingleResponse.of(cmsChannelVO);
	}


	@Autowired
	public void setICmsChannelService(ICmsChannelService iCmsChannelService) {
		this.iCmsChannelService = iCmsChannelService;
	}
}
