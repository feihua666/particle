package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway;
	private IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkPartyGateway(DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway) {
		this.dataCompanyIprTrademarkPartyGateway = dataCompanyIprTrademarkPartyGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkPartyService(IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService) {
		this.iDataCompanyIprTrademarkPartyService = iDataCompanyIprTrademarkPartyService;
	}
}
