package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyAdministrativeLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业行政许可 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway;
	private IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAdministrativeLicenseGateway(DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway) {
		this.dataCompanyAdministrativeLicenseGateway = dataCompanyAdministrativeLicenseGateway;
	}
	@Autowired
	public void setIDataCompanyAdministrativeLicenseService(IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService) {
		this.iDataCompanyAdministrativeLicenseService = iDataCompanyAdministrativeLicenseService;
	}
}
