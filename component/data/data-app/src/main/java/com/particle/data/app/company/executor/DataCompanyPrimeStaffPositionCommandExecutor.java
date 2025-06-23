package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffPositionGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业主要人员职位 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway;
	private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyPrimeStaffPositionGateway
	 */
	@Autowired
	public void setDataCompanyPrimeStaffPositionGateway(DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway) {
		this.dataCompanyPrimeStaffPositionGateway = dataCompanyPrimeStaffPositionGateway;
	}
	@Autowired
	public void setIDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
		this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
	}
}
