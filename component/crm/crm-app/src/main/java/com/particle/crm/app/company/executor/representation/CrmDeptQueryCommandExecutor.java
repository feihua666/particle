package com.particle.crm.app.company.executor.representation;

import com.particle.crm.app.company.structmapping.CrmDeptAppStructMapping;
import com.particle.crm.client.company.dto.command.representation.CrmDeptQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.crm.infrastructure.company.dos.CrmDeptDO;
import com.particle.crm.infrastructure.company.service.ICrmDeptService;
import com.particle.crm.client.company.dto.command.representation.CrmDeptPageQueryCommand;
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
 * 客户公司部门 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Component
@Validated
public class CrmDeptQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmDeptService iCrmDeptService;

	/**
	 * 执行 客户公司部门 列表查询指令
	 * @param crmDeptQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmDeptVO> execute(@Valid CrmDeptQueryListCommand crmDeptQueryListCommand) {
		List<CrmDeptDO> crmDeptDO = iCrmDeptService.list(crmDeptQueryListCommand);
		List<CrmDeptVO> crmDeptVOs = CrmDeptAppStructMapping.instance.crmDeptDOsToCrmDeptVOs(crmDeptDO);
		return MultiResponse.of(crmDeptVOs);
	}
	/**
	 * 执行 客户公司部门 分页查询指令
	 * @param crmDeptPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmDeptVO> execute(@Valid CrmDeptPageQueryCommand crmDeptPageQueryCommand) {
		Page<CrmDeptDO> page = iCrmDeptService.listPage(crmDeptPageQueryCommand);
		return CrmDeptAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户公司部门 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmDeptVO> executeDetail(IdCommand detailCommand) {
		CrmDeptDO byId = iCrmDeptService.getById(detailCommand.getId());
		CrmDeptVO crmDeptVO = CrmDeptAppStructMapping.instance.crmDeptDOToCrmDeptVO(byId);
		return SingleResponse.of(crmDeptVO);
	}
	/**
	 * 执行 客户公司部门 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmDeptVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmDeptDO byId = iCrmDeptService.getById(detailForUpdateCommand.getId());
		CrmDeptVO crmDeptVO = CrmDeptAppStructMapping.instance.crmDeptDOToCrmDeptVO(byId);
		return SingleResponse.of(crmDeptVO);
	}

	@Autowired
	public void setICrmDeptService(ICrmDeptService iCrmDeptService) {
		this.iCrmDeptService = iCrmDeptService;
	}
}
