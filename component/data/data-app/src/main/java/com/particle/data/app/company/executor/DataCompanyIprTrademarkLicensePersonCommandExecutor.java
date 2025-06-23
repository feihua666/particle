package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicensePersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标许可人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway;
	private IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicensePersonGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonGateway(DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway) {
		this.dataCompanyIprTrademarkLicensePersonGateway = dataCompanyIprTrademarkLicensePersonGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkLicensePersonService(IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService) {
		this.iDataCompanyIprTrademarkLicensePersonService = iDataCompanyIprTrademarkLicensePersonService;
	}
}
