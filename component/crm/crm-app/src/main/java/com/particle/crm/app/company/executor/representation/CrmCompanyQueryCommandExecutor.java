package com.particle.crm.app.company.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.company.structmapping.CrmCompanyAppStructMapping;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.crm.infrastructure.company.service.ICrmCompanyService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 客户公司 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Component
@Validated
public class CrmCompanyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCompanyService iCrmCompanyService;

	/**
	 * 执行 客户公司 列表查询指令
	 * @param crmCompanyQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCompanyVO> execute(@Valid CrmCompanyQueryListCommand crmCompanyQueryListCommand) {
		List<CrmCompanyDO> crmCompanyDO = iCrmCompanyService.list(crmCompanyQueryListCommand);
		List<CrmCompanyVO> crmCompanyVOs = CrmCompanyAppStructMapping.instance.crmCompanyDOsToCrmCompanyVOs(crmCompanyDO);
		return MultiResponse.of(crmCompanyVOs);
	}
	/**
	 * 执行 客户公司 分页查询指令
	 * @param crmCompanyPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCompanyVO> execute(@Valid CrmCompanyPageQueryCommand crmCompanyPageQueryCommand) {
		Page<CrmCompanyDO> page = iCrmCompanyService.listPage(crmCompanyPageQueryCommand);
		return CrmCompanyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户公司 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCompanyVO> executeDetail(IdCommand detailCommand) {
		CrmCompanyDO byId = iCrmCompanyService.getById(detailCommand.getId());
		CrmCompanyVO crmCompanyVO = CrmCompanyAppStructMapping.instance.crmCompanyDOToCrmCompanyVO(byId);
		return SingleResponse.of(crmCompanyVO);
	}
	/**
	 * 执行 客户公司 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCompanyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCompanyDO byId = iCrmCompanyService.getById(detailForUpdateCommand.getId());
		CrmCompanyVO crmCompanyVO = CrmCompanyAppStructMapping.instance.crmCompanyDOToCrmCompanyVO(byId);
		return SingleResponse.of(crmCompanyVO);
	}

	@Autowired
	public void setICrmCompanyService(ICrmCompanyService iCrmCompanyService) {
		this.iCrmCompanyService = iCrmCompanyService;
	}
}
