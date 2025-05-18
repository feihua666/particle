package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportEquityChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报股权变更 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway;
	private IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportEquityChangeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeGateway(DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway) {
		this.dataCompanyAnnualReportEquityChangeGateway = dataCompanyAnnualReportEquityChangeGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportEquityChangeService(IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService) {
		this.iDataCompanyAnnualReportEquityChangeService = iDataCompanyAnnualReportEquityChangeService;
	}
}
