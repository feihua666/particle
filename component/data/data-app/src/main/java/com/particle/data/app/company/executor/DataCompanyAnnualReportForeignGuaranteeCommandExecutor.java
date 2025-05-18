package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignGuaranteeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignGuaranteeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报对外担保 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway;
	private IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportForeignGuaranteeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeGateway(DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway) {
		this.dataCompanyAnnualReportForeignGuaranteeGateway = dataCompanyAnnualReportForeignGuaranteeGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportForeignGuaranteeService(IDataCompanyAnnualReportForeignGuaranteeService iDataCompanyAnnualReportForeignGuaranteeService) {
		this.iDataCompanyAnnualReportForeignGuaranteeService = iDataCompanyAnnualReportForeignGuaranteeService;
	}
}
