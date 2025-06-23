package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprIntegratedCircuitGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权集成电路 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway;
	private IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprIntegratedCircuitGateway
	 */
	@Autowired
	public void setDataCompanyIprIntegratedCircuitGateway(DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway) {
		this.dataCompanyIprIntegratedCircuitGateway = dataCompanyIprIntegratedCircuitGateway;
	}
	@Autowired
	public void setIDataCompanyIprIntegratedCircuitService(IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService) {
		this.iDataCompanyIprIntegratedCircuitService = iDataCompanyIprIntegratedCircuitService;
	}
}
