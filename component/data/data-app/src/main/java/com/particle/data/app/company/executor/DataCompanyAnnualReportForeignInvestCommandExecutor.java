package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignInvestGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报对外投资 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway;
	private IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportForeignInvestGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestGateway(DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway) {
		this.dataCompanyAnnualReportForeignInvestGateway = dataCompanyAnnualReportForeignInvestGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportForeignInvestService(IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService) {
		this.iDataCompanyAnnualReportForeignInvestService = iDataCompanyAnnualReportForeignInvestService;
	}
}
