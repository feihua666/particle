package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportSocialSecurityGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报社保 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway;
	private IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportSocialSecurityGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityGateway(DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway) {
		this.dataCompanyAnnualReportSocialSecurityGateway = dataCompanyAnnualReportSocialSecurityGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportSocialSecurityService(IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService) {
		this.iDataCompanyAnnualReportSocialSecurityService = iDataCompanyAnnualReportSocialSecurityService;
	}
}
