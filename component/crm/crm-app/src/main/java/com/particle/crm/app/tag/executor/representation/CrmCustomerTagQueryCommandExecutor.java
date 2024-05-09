package com.particle.crm.app.tag.executor.representation;

import com.particle.crm.app.tag.structmapping.CrmCustomerTagAppStructMapping;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import com.particle.crm.infrastructure.tag.service.ICrmCustomerTagService;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagPageQueryCommand;
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
 * 客户标签 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Component
@Validated
public class CrmCustomerTagQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ICrmCustomerTagService iCrmCustomerTagService;

	/**
	 * 执行 客户标签 列表查询指令
	 * @param crmCustomerTagQueryListCommand
	 * @return
	 */
	public MultiResponse<CrmCustomerTagVO> execute(@Valid CrmCustomerTagQueryListCommand crmCustomerTagQueryListCommand) {
		List<CrmCustomerTagDO> crmCustomerTagDO = iCrmCustomerTagService.list(crmCustomerTagQueryListCommand);
		List<CrmCustomerTagVO> crmCustomerTagVOs = CrmCustomerTagAppStructMapping.instance.crmCustomerTagDOsToCrmCustomerTagVOs(crmCustomerTagDO);
		return MultiResponse.of(crmCustomerTagVOs);
	}
	/**
	 * 执行 客户标签 分页查询指令
	 * @param crmCustomerTagPageQueryCommand
	 * @return
	 */
	public PageResponse<CrmCustomerTagVO> execute(@Valid CrmCustomerTagPageQueryCommand crmCustomerTagPageQueryCommand) {
		Page<CrmCustomerTagDO> page = iCrmCustomerTagService.listPage(crmCustomerTagPageQueryCommand);
		return CrmCustomerTagAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 客户标签 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagVO> executeDetail(IdCommand detailCommand) {
		CrmCustomerTagDO byId = iCrmCustomerTagService.getById(detailCommand.getId());
		CrmCustomerTagVO crmCustomerTagVO = CrmCustomerTagAppStructMapping.instance.crmCustomerTagDOToCrmCustomerTagVO(byId);
		return SingleResponse.of(crmCustomerTagVO);
	}
	/**
	 * 执行 客户标签 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		CrmCustomerTagDO byId = iCrmCustomerTagService.getById(detailForUpdateCommand.getId());
		CrmCustomerTagVO crmCustomerTagVO = CrmCustomerTagAppStructMapping.instance.crmCustomerTagDOToCrmCustomerTagVO(byId);
		return SingleResponse.of(crmCustomerTagVO);
	}

	@Autowired
	public void setICrmCustomerTagService(ICrmCustomerTagService iCrmCustomerTagService) {
		this.iCrmCustomerTagService = iCrmCustomerTagService;
	}
}
