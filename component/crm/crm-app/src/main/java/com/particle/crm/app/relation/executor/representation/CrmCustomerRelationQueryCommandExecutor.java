package com.particle.crm.app.relation.executor.representation;

import com.particle.crm.app.relation.structmapping.CrmCustomerRelationAppStructMapping;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.crm.infrastructure.relation.service.ICrmCustomerRelationService;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationPageQueryCommand;
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
 * 客户与客户关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Component
@Validated
public class CrmCustomerRelationQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCustomerRelationService iCrmCustomerRelationService;

	/**
	 * 执行 客户与客户关系 列表查询指令
	 * @param crmCustomerRelationQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCustomerRelationVO> execute(@Valid CrmCustomerRelationQueryListCommand crmCustomerRelationQueryListCommand) {
		List<CrmCustomerRelationDO> crmCustomerRelationDO = iCrmCustomerRelationService.list(crmCustomerRelationQueryListCommand);
		List<CrmCustomerRelationVO> crmCustomerRelationVOs = CrmCustomerRelationAppStructMapping.instance.crmCustomerRelationDOsToCrmCustomerRelationVOs(crmCustomerRelationDO);
		return MultiResponse.of(crmCustomerRelationVOs);
	}
	/**
	 * 执行 客户与客户关系 分页查询指令
	 * @param crmCustomerRelationPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCustomerRelationVO> execute(@Valid CrmCustomerRelationPageQueryCommand crmCustomerRelationPageQueryCommand) {
		Page<CrmCustomerRelationDO> page = iCrmCustomerRelationService.listPage(crmCustomerRelationPageQueryCommand);
		return CrmCustomerRelationAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户与客户关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationVO> executeDetail(IdCommand detailCommand) {
		CrmCustomerRelationDO byId = iCrmCustomerRelationService.getById(detailCommand.getId());
		CrmCustomerRelationVO crmCustomerRelationVO = CrmCustomerRelationAppStructMapping.instance.crmCustomerRelationDOToCrmCustomerRelationVO(byId);
		return SingleResponse.of(crmCustomerRelationVO);
	}
	/**
	 * 执行 客户与客户关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCustomerRelationDO byId = iCrmCustomerRelationService.getById(detailForUpdateCommand.getId());
		CrmCustomerRelationVO crmCustomerRelationVO = CrmCustomerRelationAppStructMapping.instance.crmCustomerRelationDOToCrmCustomerRelationVO(byId);
		return SingleResponse.of(crmCustomerRelationVO);
	}

	@Autowired
	public void setICrmCustomerRelationService(ICrmCustomerRelationService iCrmCustomerRelationService) {
		this.iCrmCustomerRelationService = iCrmCustomerRelationService;
	}
}
