package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标许可信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway;
	private IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicenseGateway(DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway) {
		this.dataCompanyIprTrademarkLicenseGateway = dataCompanyIprTrademarkLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkLicenseService(IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService) {
		this.iDataCompanyIprTrademarkLicenseService = iDataCompanyIprTrademarkLicenseService;
	}
}
