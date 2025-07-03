package com.particle.cms.app.executor.representation;

import com.particle.cms.app.structmapping.CmsSiteAppStructMapping;
import com.particle.cms.client.dto.command.representation.CmsSiteQueryListCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.cms.infrastructure.service.ICmsSiteService;
import com.particle.cms.client.dto.command.representation.CmsSitePageQueryCommand;
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
 * 站点 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Component
@Validated
public class CmsSiteQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICmsSiteService iCmsSiteService;

	/**
	 * 执行 站点 列表查询指令
	 * @param cmsSiteQueryListCommand
	 * @return
	 */
	public MultiResponse<CmsSiteVO> execute(@Valid CmsSiteQueryListCommand cmsSiteQueryListCommand) {
		List<CmsSiteDO> cmsSiteDO = iCmsSiteService.list(cmsSiteQueryListCommand);
		List<CmsSiteVO> cmsSiteVOs = CmsSiteAppStructMapping.instance.cmsSiteDOsToCmsSiteVOs(cmsSiteDO);
		return MultiResponse.of(cmsSiteVOs);
	}
	/**
	 * 执行 站点 分页查询指令
	 * @param cmsSitePageQueryCommand
	 * @return
	 */
	public PageResponse<CmsSiteVO> execute(@Valid CmsSitePageQueryCommand cmsSitePageQueryCommand) {
		Page<CmsSiteDO> page = iCmsSiteService.listPage(cmsSitePageQueryCommand);
		return CmsSiteAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 站点 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CmsSiteVO> executeDetail(IdCommand detailCommand) {
		CmsSiteDO byId = iCmsSiteService.getById(detailCommand.getId());
		CmsSiteVO cmsSiteVO = CmsSiteAppStructMapping.instance.cmsSiteDOToCmsSiteVO(byId);
		return SingleResponse.of(cmsSiteVO);
	}
	/**
	 * 执行 站点 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsSiteVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CmsSiteDO byId = iCmsSiteService.getById(detailForUpdateCommand.getId());
		CmsSiteVO cmsSiteVO = CmsSiteAppStructMapping.instance.cmsSiteDOToCmsSiteVO(byId);
		return SingleResponse.of(cmsSiteVO);
	}


	@Autowired
	public void setICmsSiteService(ICmsSiteService iCmsSiteService) {
		this.iCmsSiteService = iCmsSiteService;
	}
}
