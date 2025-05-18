package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportShareholderGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业年报股东 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway;
	private IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportShareholderGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportShareholderGateway(DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway) {
		this.dataCompanyAnnualReportShareholderGateway = dataCompanyAnnualReportShareholderGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportShareholderService(IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService) {
		this.iDataCompanyAnnualReportShareholderService = iDataCompanyAnnualReportShareholderService;
	}
}
