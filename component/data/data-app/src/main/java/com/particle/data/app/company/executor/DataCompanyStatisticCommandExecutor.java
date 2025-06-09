package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyStatisticGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业统计 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Component
@Validated
public class DataCompanyStatisticCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyStatisticGateway dataCompanyStatisticGateway;
	private IDataCompanyStatisticService iDataCompanyStatisticService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyStatisticGateway
	 */
	@Autowired
	public void setDataCompanyStatisticGateway(DataCompanyStatisticGateway dataCompanyStatisticGateway) {
		this.dataCompanyStatisticGateway = dataCompanyStatisticGateway;
	}
	@Autowired
	public void setIDataCompanyStatisticService(IDataCompanyStatisticService iDataCompanyStatisticService) {
		this.iDataCompanyStatisticService = iDataCompanyStatisticService;
	}
}
