package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAssetsGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAssetsService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业资产状况信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Component
@Validated
public class DataCompanyAnnualReportAssetsCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAssetsGateway dataCompanyAnnualReportAssetsGateway;
	private IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportAssetsGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportAssetsGateway(DataCompanyAnnualReportAssetsGateway dataCompanyAnnualReportAssetsGateway) {
		this.dataCompanyAnnualReportAssetsGateway = dataCompanyAnnualReportAssetsGateway;
	}
	@Autowired
	public void setIDataCompanyAnnualReportAssetsService(IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService) {
		this.iDataCompanyAnnualReportAssetsService = iDataCompanyAnnualReportAssetsService;
	}
}
