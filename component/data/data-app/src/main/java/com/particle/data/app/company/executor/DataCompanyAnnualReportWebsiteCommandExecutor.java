package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportWebsiteGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报网站网店 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Component
@Validated
public class DataCompanyAnnualReportWebsiteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway;
	private IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportWebsiteGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportWebsiteGateway(DataCompanyAnnualReportWebsiteGateway dataCompanyAnnualReportWebsiteGateway) {
		this.dataCompanyAnnualReportWebsiteGateway = dataCompanyAnnualReportWebsiteGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportWebsiteService(IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService) {
		this.iDataCompanyAnnualReportWebsiteService = iDataCompanyAnnualReportWebsiteService;
	}
}
