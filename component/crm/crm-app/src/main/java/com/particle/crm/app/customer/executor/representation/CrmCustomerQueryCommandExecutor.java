package com.particle.crm.app.customer.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.customer.structmapping.CrmCustomerAppStructMapping;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerService;
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
 * 客户 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Component
@Validated
public class CrmCustomerQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCustomerService iCrmCustomerService;

	/**
	 * 执行 客户 列表查询指令
	 * @param crmCustomerQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCustomerVO> execute(@Valid CrmCustomerQueryListCommand crmCustomerQueryListCommand) {
		List<CrmCustomerDO> crmCustomerDO = iCrmCustomerService.list(crmCustomerQueryListCommand);
		List<CrmCustomerVO> crmCustomerVOs = CrmCustomerAppStructMapping.instance.crmCustomerDOsToCrmCustomerVOs(crmCustomerDO);
		return MultiResponse.of(crmCustomerVOs);
	}
	/**
	 * 执行 客户 分页查询指令
	 * @param crmCustomerPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCustomerVO> execute(@Valid CrmCustomerPageQueryCommand crmCustomerPageQueryCommand) {
		Page<CrmCustomerDO> page = iCrmCustomerService.listPage(crmCustomerPageQueryCommand);
		return CrmCustomerAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerVO> executeDetail(IdCommand detailCommand) {
		CrmCustomerDO byId = iCrmCustomerService.getById(detailCommand.getId());
		CrmCustomerVO crmCustomerVO = CrmCustomerAppStructMapping.instance.crmCustomerDOToCrmCustomerVO(byId);
		return SingleResponse.of(crmCustomerVO);
	}
	/**
	 * 执行 客户 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCustomerDO byId = iCrmCustomerService.getById(detailForUpdateCommand.getId());
		CrmCustomerVO crmCustomerVO = CrmCustomerAppStructMapping.instance.crmCustomerDOToCrmCustomerVO(byId);
		return SingleResponse.of(crmCustomerVO);
	}

	@Autowired
	public void setICrmCustomerService(ICrmCustomerService iCrmCustomerService) {
		this.iCrmCustomerService = iCrmCustomerService;
	}
}
