package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsChannelViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;
import com.particle.cms.infrastructure.service.ICmsChannelViewRecordService;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordPageQueryCommand;
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
 * 栏目访问记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Component
@Validated
public class CmsChannelViewRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsChannelViewRecordService iCmsChannelViewRecordService;

	/**
	 * 执行 栏目访问记录 列表查询指令
	 * @param cmsChannelViewRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsChannelViewRecordVO> execute(@Valid CmsChannelViewRecordQueryListCommand cmsChannelViewRecordQueryListCommand) {
		List<CmsChannelViewRecordDO> cmsChannelViewRecordDO = iCmsChannelViewRecordService.list(cmsChannelViewRecordQueryListCommand);
		List<CmsChannelViewRecordVO> cmsChannelViewRecordVOs = CmsChannelViewRecordAppStructMapping.instance.cmsChannelViewRecordDOsToCmsChannelViewRecordVOs(cmsChannelViewRecordDO);
		return MultiResponse.of(cmsChannelViewRecordVOs);
	}
	/**
	 * 执行 栏目访问记录 分页查询指令
	 * @param cmsChannelViewRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsChannelViewRecordVO> execute(@Valid CmsChannelViewRecordPageQueryCommand cmsChannelViewRecordPageQueryCommand) {
		Page<CmsChannelViewRecordDO> page = iCmsChannelViewRecordService.listPage(cmsChannelViewRecordPageQueryCommand);
		return CmsChannelViewRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 栏目访问记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsChannelViewRecordVO> executeDetail(IdCommand detailCommand) {
		CmsChannelViewRecordDO byId = iCmsChannelViewRecordService.getById(detailCommand.getId());
		CmsChannelViewRecordVO cmsChannelViewRecordVO = CmsChannelViewRecordAppStructMapping.instance.cmsChannelViewRecordDOToCmsChannelViewRecordVO(byId);
		return SingleResponse.of(cmsChannelViewRecordVO);
	}
	/**
	 * 执行 栏目访问记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsChannelViewRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsChannelViewRecordDO byId = iCmsChannelViewRecordService.getById(detailForUpdateCommand.getId());
		CmsChannelViewRecordVO cmsChannelViewRecordVO = CmsChannelViewRecordAppStructMapping.instance.cmsChannelViewRecordDOToCmsChannelViewRecordVO(byId);
		return SingleResponse.of(cmsChannelViewRecordVO);
	}


	@Autowired
	public void setICmsChannelViewRecordService(ICmsChannelViewRecordService iCmsChannelViewRecordService) {
		this.iCmsChannelViewRecordService = iCmsChannelViewRecordService;
	}
}
