package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAdministrativeLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAdministrativeLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报行政许可 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Component
@Validated
public class DataCompanyAnnualReportAdministrativeLicenseCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway;
	private IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportAdministrativeLicenseGateway(DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway) {
		this.dataCompanyAnnualReportAdministrativeLicenseGateway = dataCompanyAnnualReportAdministrativeLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportAdministrativeLicenseService(IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService) {
		this.iDataCompanyAnnualReportAdministrativeLicenseService = iDataCompanyAnnualReportAdministrativeLicenseService;
	}
}
