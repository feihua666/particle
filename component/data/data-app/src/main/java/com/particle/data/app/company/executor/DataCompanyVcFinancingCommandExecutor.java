package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyVcFinancingGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Component
@Validated
public class DataCompanyVcFinancingCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway;
	private IDataCompanyVcFinancingService iDataCompanyVcFinancingService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingGateway(DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway) {
		this.dataCompanyVcFinancingGateway = dataCompanyVcFinancingGateway;
	}
	@Autowired
	public void setIDataCompanyVcFinancingService(IDataCompanyVcFinancingService iDataCompanyVcFinancingService) {
		this.iDataCompanyVcFinancingService = iDataCompanyVcFinancingService;
	}
}
