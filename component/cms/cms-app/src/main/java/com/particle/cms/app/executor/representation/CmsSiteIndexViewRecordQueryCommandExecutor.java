package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsSiteIndexViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import com.particle.cms.infrastructure.service.ICmsSiteIndexViewRecordService;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordPageQueryCommand;
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
 * 站点首页访问记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Component
@Validated
public class CmsSiteIndexViewRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService;

	/**
	 * 执行 站点首页访问记录 列表查询指令
	 * @param cmsSiteIndexViewRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsSiteIndexViewRecordVO> execute(@Valid CmsSiteIndexViewRecordQueryListCommand cmsSiteIndexViewRecordQueryListCommand) {
		List<CmsSiteIndexViewRecordDO> cmsSiteIndexViewRecordDO = iCmsSiteIndexViewRecordService.list(cmsSiteIndexViewRecordQueryListCommand);
		List<CmsSiteIndexViewRecordVO> cmsSiteIndexViewRecordVOs = CmsSiteIndexViewRecordAppStructMapping.instance.cmsSiteIndexViewRecordDOsToCmsSiteIndexViewRecordVOs(cmsSiteIndexViewRecordDO);
		return MultiResponse.of(cmsSiteIndexViewRecordVOs);
	}
	/**
	 * 执行 站点首页访问记录 分页查询指令
	 * @param cmsSiteIndexViewRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<CmsSiteIndexViewRecordVO> execute(@Valid CmsSiteIndexViewRecordPageQueryCommand cmsSiteIndexViewRecordPageQueryCommand) {
		Page<CmsSiteIndexViewRecordDO> page = iCmsSiteIndexViewRecordService.listPage(cmsSiteIndexViewRecordPageQueryCommand);
		return CmsSiteIndexViewRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 站点首页访问记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsSiteIndexViewRecordVO> executeDetail(IdCommand detailCommand) {
		CmsSiteIndexViewRecordDO byId = iCmsSiteIndexViewRecordService.getById(detailCommand.getId());
		CmsSiteIndexViewRecordVO cmsSiteIndexViewRecordVO = CmsSiteIndexViewRecordAppStructMapping.instance.cmsSiteIndexViewRecordDOToCmsSiteIndexViewRecordVO(byId);
		return SingleResponse.of(cmsSiteIndexViewRecordVO);
	}
	/**
	 * 执行 站点首页访问记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsSiteIndexViewRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsSiteIndexViewRecordDO byId = iCmsSiteIndexViewRecordService.getById(detailForUpdateCommand.getId());
		CmsSiteIndexViewRecordVO cmsSiteIndexViewRecordVO = CmsSiteIndexViewRecordAppStructMapping.instance.cmsSiteIndexViewRecordDOToCmsSiteIndexViewRecordVO(byId);
		return SingleResponse.of(cmsSiteIndexViewRecordVO);
	}


	@Autowired
	public void setICmsSiteIndexViewRecordService(ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService) {
		this.iCmsSiteIndexViewRecordService = iCmsSiteIndexViewRecordService;
	}
}
