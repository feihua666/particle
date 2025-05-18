package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报变更 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway;
	private IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportChangeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportChangeGateway(DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway) {
		this.dataCompanyAnnualReportChangeGateway = dataCompanyAnnualReportChangeGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportChangeService(IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService) {
		this.iDataCompanyAnnualReportChangeService = iDataCompanyAnnualReportChangeService;
	}
}
