package com.particle.crm.app.relation.executor.representation;

import com.particle.crm.app.relation.structmapping.CrmCustomerRelationDefineAppStructMapping;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefineQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDefineDO;
import com.particle.crm.infrastructure.relation.service.ICrmCustomerRelationDefineService;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefinePageQueryCommand;
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
 * 客户关系定义 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Component
@Validated
public class CrmCustomerRelationDefineQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCustomerRelationDefineService iCrmCustomerRelationDefineService;

	/**
	 * 执行 客户关系定义 列表查询指令
	 * @param crmCustomerRelationDefineQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCustomerRelationDefineVO> execute(@Valid CrmCustomerRelationDefineQueryListCommand crmCustomerRelationDefineQueryListCommand) {
		List<CrmCustomerRelationDefineDO> crmCustomerRelationDefineDO = iCrmCustomerRelationDefineService.list(crmCustomerRelationDefineQueryListCommand);
		List<CrmCustomerRelationDefineVO> crmCustomerRelationDefineVOs = CrmCustomerRelationDefineAppStructMapping.instance.crmCustomerRelationDefineDOsToCrmCustomerRelationDefineVOs(crmCustomerRelationDefineDO);
		return MultiResponse.of(crmCustomerRelationDefineVOs);
	}
	/**
	 * 执行 客户关系定义 分页查询指令
	 * @param crmCustomerRelationDefinePageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCustomerRelationDefineVO> execute(@Valid CrmCustomerRelationDefinePageQueryCommand crmCustomerRelationDefinePageQueryCommand) {
		Page<CrmCustomerRelationDefineDO> page = iCrmCustomerRelationDefineService.listPage(crmCustomerRelationDefinePageQueryCommand);
		return CrmCustomerRelationDefineAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户关系定义 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationDefineVO> executeDetail(IdCommand detailCommand) {
		CrmCustomerRelationDefineDO byId = iCrmCustomerRelationDefineService.getById(detailCommand.getId());
		CrmCustomerRelationDefineVO crmCustomerRelationDefineVO = CrmCustomerRelationDefineAppStructMapping.instance.crmCustomerRelationDefineDOToCrmCustomerRelationDefineVO(byId);
		return SingleResponse.of(crmCustomerRelationDefineVO);
	}
	/**
	 * 执行 客户关系定义 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationDefineVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCustomerRelationDefineDO byId = iCrmCustomerRelationDefineService.getById(detailForUpdateCommand.getId());
		CrmCustomerRelationDefineVO crmCustomerRelationDefineVO = CrmCustomerRelationDefineAppStructMapping.instance.crmCustomerRelationDefineDOToCrmCustomerRelationDefineVO(byId);
		return SingleResponse.of(crmCustomerRelationDefineVO);
	}

	@Autowired
	public void setICrmCustomerRelationDefineService(ICrmCustomerRelationDefineService iCrmCustomerRelationDefineService) {
		this.iCrmCustomerRelationDefineService = iCrmCustomerRelationDefineService;
	}
}
