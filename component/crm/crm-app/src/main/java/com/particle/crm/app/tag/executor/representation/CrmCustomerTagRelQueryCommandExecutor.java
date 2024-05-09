package com.particle.crm.app.tag.executor.representation;

import com.particle.crm.app.tag.structmapping.CrmCustomerTagRelAppStructMapping;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.crm.infrastructure.tag.service.ICrmCustomerTagRelService;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户标签关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Component
@Validated
public class CrmCustomerTagRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCustomerTagRelService iCrmCustomerTagRelService;

	/**
	 * 执行 客户标签关系 列表查询指令
	 * @param crmCustomerTagRelQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCustomerTagRelVO> execute(@Valid CrmCustomerTagRelQueryListCommand crmCustomerTagRelQueryListCommand) {
		List<CrmCustomerTagRelDO> crmCustomerTagRelDO = iCrmCustomerTagRelService.list(crmCustomerTagRelQueryListCommand);
		List<CrmCustomerTagRelVO> crmCustomerTagRelVOs = CrmCustomerTagRelAppStructMapping.instance.crmCustomerTagRelDOsToCrmCustomerTagRelVOs(crmCustomerTagRelDO);
		return MultiResponse.of(crmCustomerTagRelVOs);
	}
	/**
	 * 执行 客户标签关系 分页查询指令
	 * @param crmCustomerTagRelPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCustomerTagRelVO> execute(@Valid CrmCustomerTagRelPageQueryCommand crmCustomerTagRelPageQueryCommand) {
		Page<CrmCustomerTagRelDO> page = iCrmCustomerTagRelService.listPage(crmCustomerTagRelPageQueryCommand);
		return CrmCustomerTagRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户标签关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagRelVO> executeDetail(IdCommand detailCommand) {
		CrmCustomerTagRelDO byId = iCrmCustomerTagRelService.getById(detailCommand.getId());
		CrmCustomerTagRelVO crmCustomerTagRelVO = CrmCustomerTagRelAppStructMapping.instance.crmCustomerTagRelDOToCrmCustomerTagRelVO(byId);
		return SingleResponse.of(crmCustomerTagRelVO);
	}
	/**
	 * 执行 客户标签关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCustomerTagRelDO byId = iCrmCustomerTagRelService.getById(detailForUpdateCommand.getId());
		CrmCustomerTagRelVO crmCustomerTagRelVO = CrmCustomerTagRelAppStructMapping.instance.crmCustomerTagRelDOToCrmCustomerTagRelVO(byId);
		return SingleResponse.of(crmCustomerTagRelVO);
	}

	@Autowired
	public void setICrmCustomerTagRelService(ICrmCustomerTagRelService iCrmCustomerTagRelService) {
		this.iCrmCustomerTagRelService = iCrmCustomerTagRelService;
	}
}
