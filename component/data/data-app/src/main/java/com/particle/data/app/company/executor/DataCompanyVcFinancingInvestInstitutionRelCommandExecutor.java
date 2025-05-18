package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyVcFinancingInvestInstitutionRelGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.client.company.dto.command.CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand;
import com.particle.data.client.company.dto.command.CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资历史投资机构关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Component
@Validated
public class DataCompanyVcFinancingInvestInstitutionRelCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway;
	private IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService;
	/**
	 * 企业融资表ID分配企业投资机构表
	 * @param companyVcFinancingAssignCompanyVcInvestInstitutionCommand
	 * @return
	 */
	public Response companyVcFinancingAssignCompanyVcInvestInstitution(@Valid CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand companyVcFinancingAssignCompanyVcInvestInstitutionCommand) {
		boolean result = iDataCompanyVcFinancingInvestInstitutionRelService.removeAndAssignRel(companyVcFinancingAssignCompanyVcInvestInstitutionCommand.getCompanyVcFinancingId(),
				companyVcFinancingAssignCompanyVcInvestInstitutionCommand.getCheckedCompanyVcInvestInstitutionIds(),companyVcFinancingAssignCompanyVcInvestInstitutionCommand.getUncheckedCompanyVcInvestInstitutionIds(),
				companyVcFinancingAssignCompanyVcInvestInstitutionCommand.getIsLazyLoad(), DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId,DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcInvestInstitutionId,
				(relDto)->new DataCompanyVcFinancingInvestInstitutionRelDO().setCompanyVcFinancingId(relDto.getMainId()).setCompanyVcInvestInstitutionId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 企业投资机构表分配企业融资表ID
	 * @param companyVcInvestInstitutionAssignCompanyVcFinancingCommand
	 * @return
	 */
	public Response companyVcInvestInstitutionAssignCompanyVcFinancing(@Valid CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand companyVcInvestInstitutionAssignCompanyVcFinancingCommand) {
		boolean result = iDataCompanyVcFinancingInvestInstitutionRelService.removeAndAssignRel(companyVcInvestInstitutionAssignCompanyVcFinancingCommand.getCompanyVcInvestInstitutionId(),
				companyVcInvestInstitutionAssignCompanyVcFinancingCommand.getCheckedCompanyVcFinancingIds(),companyVcInvestInstitutionAssignCompanyVcFinancingCommand.getUncheckedCompanyVcFinancingIds(),
				companyVcInvestInstitutionAssignCompanyVcFinancingCommand.getIsLazyLoad(), DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcInvestInstitutionId,DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId,
				(relDto)->new DataCompanyVcFinancingInvestInstitutionRelDO().setCompanyVcInvestInstitutionId(relDto.getMainId()).setCompanyVcFinancingId(relDto.getOtherId()));
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingInvestInstitutionRelGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingInvestInstitutionRelGateway(DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway) {
		this.dataCompanyVcFinancingInvestInstitutionRelGateway = dataCompanyVcFinancingInvestInstitutionRelGateway;
	}
	@Autowired
	public void setIDataCompanyVcFinancingInvestInstitutionRelService(IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService) {
		this.iDataCompanyVcFinancingInvestInstitutionRelService = iDataCompanyVcFinancingInvestInstitutionRelService;
	}
}
