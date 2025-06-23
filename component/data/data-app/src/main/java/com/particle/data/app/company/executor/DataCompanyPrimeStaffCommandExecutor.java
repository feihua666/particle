package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业主要人员 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Component
@Validated
public class DataCompanyPrimeStaffCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway;
	private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyPrimeStaffGateway
	 */
	@Autowired
	public void setDataCompanyPrimeStaffGateway(DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway) {
		this.dataCompanyPrimeStaffGateway = dataCompanyPrimeStaffGateway;
	}
	@Autowired
	public void setIDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
		this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
	}
}
