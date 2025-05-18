package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyVcProductCompetitiveProductRelGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.client.company.dto.command.CompanyVcProductAssignCompanyVcCompetitiveProductCommand;
import com.particle.data.client.company.dto.command.CompanyVcCompetitiveProductAssignCompanyVcProductCommand;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资产品竞品关系 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Component
@Validated
public class DataCompanyVcProductCompetitiveProductRelCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway;
	private IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService;
	/**
	 * 企业融资产品表ID分配企业竞品
	 * @param companyVcProductAssignCompanyVcCompetitiveProductCommand
	 * @return
	 */
	public Response companyVcProductAssignCompanyVcCompetitiveProduct(@Valid CompanyVcProductAssignCompanyVcCompetitiveProductCommand companyVcProductAssignCompanyVcCompetitiveProductCommand) {
		boolean result = iDataCompanyVcProductCompetitiveProductRelService.removeAndAssignRel(companyVcProductAssignCompanyVcCompetitiveProductCommand.getCompanyVcProductId(),
				companyVcProductAssignCompanyVcCompetitiveProductCommand.getCheckedCompanyVcCompetitiveProductIds(),companyVcProductAssignCompanyVcCompetitiveProductCommand.getUncheckedCompanyVcCompetitiveProductIds(),
				companyVcProductAssignCompanyVcCompetitiveProductCommand.getIsLazyLoad(), DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId,DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcCompetitiveProductId,
				(relDto)->new DataCompanyVcProductCompetitiveProductRelDO().setCompanyVcProductId(relDto.getMainId()).setCompanyVcCompetitiveProductId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 企业竞品分配企业融资产品表ID
	 * @param companyVcCompetitiveProductAssignCompanyVcProductCommand
	 * @return
	 */
	public Response companyVcCompetitiveProductAssignCompanyVcProduct(@Valid CompanyVcCompetitiveProductAssignCompanyVcProductCommand companyVcCompetitiveProductAssignCompanyVcProductCommand) {
		boolean result = iDataCompanyVcProductCompetitiveProductRelService.removeAndAssignRel(companyVcCompetitiveProductAssignCompanyVcProductCommand.getCompanyVcCompetitiveProductId(),
				companyVcCompetitiveProductAssignCompanyVcProductCommand.getCheckedCompanyVcProductIds(),companyVcCompetitiveProductAssignCompanyVcProductCommand.getUncheckedCompanyVcProductIds(),
				companyVcCompetitiveProductAssignCompanyVcProductCommand.getIsLazyLoad(), DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcCompetitiveProductId,DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId,
				(relDto)->new DataCompanyVcProductCompetitiveProductRelDO().setCompanyVcCompetitiveProductId(relDto.getMainId()).setCompanyVcProductId(relDto.getOtherId()));
		return Response.buildSuccess();
	}
	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductCompetitiveProductRelGateway
	 */
	@Autowired
	public void setDataCompanyVcProductCompetitiveProductRelGateway(DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway) {
		this.dataCompanyVcProductCompetitiveProductRelGateway = dataCompanyVcProductCompetitiveProductRelGateway;
	}
	@Autowired
	public void setIDataCompanyVcProductCompetitiveProductRelService(IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService) {
		this.iDataCompanyVcProductCompetitiveProductRelService = iDataCompanyVcProductCompetitiveProductRelService;
	}
}
