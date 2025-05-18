package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Component
@Validated
public class DataCompanyAnnualReportCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway;
	private IDataCompanyAnnualReportService iDataCompanyAnnualReportService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportGateway(DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway) {
		this.dataCompanyAnnualReportGateway = dataCompanyAnnualReportGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportService(IDataCompanyAnnualReportService iDataCompanyAnnualReportService) {
		this.iDataCompanyAnnualReportService = iDataCompanyAnnualReportService;
	}
}
