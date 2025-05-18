package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyCaseFilingGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业立案信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Component
@Validated
public class DataCompanyCaseFilingCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway;
	private IDataCompanyCaseFilingService iDataCompanyCaseFilingService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyCaseFilingGateway
	 */
	@Autowired
	public void setDataCompanyCaseFilingGateway(DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway) {
		this.dataCompanyCaseFilingGateway = dataCompanyCaseFilingGateway;
	}
	@Autowired
	public void setIDataCompanyCaseFilingService(IDataCompanyCaseFilingService iDataCompanyCaseFilingService) {
		this.iDataCompanyCaseFilingService = iDataCompanyCaseFilingService;
	}
}
