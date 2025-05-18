package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyCaseFilingPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业立案信息当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway;
	private IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyCaseFilingPartyGateway
	 */
	@Autowired
	public void setDataCompanyCaseFilingPartyGateway(DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway) {
		this.dataCompanyCaseFilingPartyGateway = dataCompanyCaseFilingPartyGateway;
	}
	@Autowired
	public void setIDataCompanyCaseFilingPartyService(IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService) {
		this.iDataCompanyCaseFilingPartyService = iDataCompanyCaseFilingPartyService;
	}
}
