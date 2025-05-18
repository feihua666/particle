package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利许可信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway;
	private IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLicenseGateway(DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway) {
		this.dataCompanyIprPatentLicenseGateway = dataCompanyIprPatentLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentLicenseService(IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService) {
		this.iDataCompanyIprPatentLicenseService = iDataCompanyIprPatentLicenseService;
	}
}
