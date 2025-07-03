package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsContentViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;
import com.particle.cms.infrastructure.service.ICmsContentViewRecordService;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordPageQueryCommand;
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
 * 内容访问记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Component
@Validated
public class CmsContentViewRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsContentViewRecordService iCmsContentViewRecordService;

	/**
	 * 执行 内容访问记录 列表查询指令
	 * @param cmsContentViewRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsContentViewRecordVO> execute(@Valid CmsContentViewRecordQueryListCommand cmsContentViewRecordQueryListCommand) {
		List<CmsContentViewRecordDO> cmsContentViewRecordDO = iCmsContentViewRecordService.list(cmsContentViewRecordQueryListCommand);
		List<CmsContentViewRecordVO> cmsContentViewRecordVOs = CmsContentViewRecordAppStructMapping.instance.cmsContentViewRecordDOsToCmsContentViewRecordVOs(cmsContentViewRecordDO);
		return MultiResponse.of(cmsContentViewRecordVOs);
	}
	/**
	 * 执行 内容访问记录 分页查询指令
	 * @param cmsContentViewRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsContentViewRecordVO> execute(@Valid CmsContentViewRecordPageQueryCommand cmsContentViewRecordPageQueryCommand) {
		Page<CmsContentViewRecordDO> page = iCmsContentViewRecordService.listPage(cmsContentViewRecordPageQueryCommand);
		return CmsContentViewRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 内容访问记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsContentViewRecordVO> executeDetail(IdCommand detailCommand) {
		CmsContentViewRecordDO byId = iCmsContentViewRecordService.getById(detailCommand.getId());
		CmsContentViewRecordVO cmsContentViewRecordVO = CmsContentViewRecordAppStructMapping.instance.cmsContentViewRecordDOToCmsContentViewRecordVO(byId);
		return SingleResponse.of(cmsContentViewRecordVO);
	}
	/**
	 * 执行 内容访问记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentViewRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsContentViewRecordDO byId = iCmsContentViewRecordService.getById(detailForUpdateCommand.getId());
		CmsContentViewRecordVO cmsContentViewRecordVO = CmsContentViewRecordAppStructMapping.instance.cmsContentViewRecordDOToCmsContentViewRecordVO(byId);
		return SingleResponse.of(cmsContentViewRecordVO);
	}


	@Autowired
	public void setICmsContentViewRecordService(ICmsContentViewRecordService iCmsContentViewRecordService) {
		this.iCmsContentViewRecordService = iCmsContentViewRecordService;
	}
}
