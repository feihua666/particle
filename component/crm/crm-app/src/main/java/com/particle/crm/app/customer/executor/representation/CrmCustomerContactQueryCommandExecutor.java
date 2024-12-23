package com.particle.crm.app.customer.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.customer.structmapping.CrmCustomerContactAppStructMapping;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerContactService;
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
 * 客户联系方式 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Component
@Validated
public class CrmCustomerContactQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCustomerContactService iCrmCustomerContactService;

	/**
	 * 执行 客户联系方式 列表查询指令
	 * @param crmCustomerContactQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCustomerContactVO> execute(@Valid CrmCustomerContactQueryListCommand crmCustomerContactQueryListCommand) {
		List<CrmCustomerContactDO> crmCustomerContactDO = iCrmCustomerContactService.list(crmCustomerContactQueryListCommand);
		List<CrmCustomerContactVO> crmCustomerContactVOs = CrmCustomerContactAppStructMapping.instance.crmCustomerContactDOsToCrmCustomerContactVOs(crmCustomerContactDO);
		return MultiResponse.of(crmCustomerContactVOs);
	}
	/**
	 * 执行 客户联系方式 分页查询指令
	 * @param crmCustomerContactPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCustomerContactVO> execute(@Valid CrmCustomerContactPageQueryCommand crmCustomerContactPageQueryCommand) {
		Page<CrmCustomerContactDO> page = iCrmCustomerContactService.listPage(crmCustomerContactPageQueryCommand);
		return CrmCustomerContactAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户联系方式 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerContactVO> executeDetail(IdCommand detailCommand) {
		CrmCustomerContactDO byId = iCrmCustomerContactService.getById(detailCommand.getId());
		CrmCustomerContactVO crmCustomerContactVO = CrmCustomerContactAppStructMapping.instance.crmCustomerContactDOToCrmCustomerContactVO(byId);
		return SingleResponse.of(crmCustomerContactVO);
	}
	/**
	 * 执行 客户联系方式 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerContactVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCustomerContactDO byId = iCrmCustomerContactService.getById(detailForUpdateCommand.getId());
		CrmCustomerContactVO crmCustomerContactVO = CrmCustomerContactAppStructMapping.instance.crmCustomerContactDOToCrmCustomerContactVO(byId);
		return SingleResponse.of(crmCustomerContactVO);
	}

	@Autowired
	public void setICrmCustomerContactService(ICrmCustomerContactService iCrmCustomerContactService) {
		this.iCrmCustomerContactService = iCrmCustomerContactService;
	}
}
