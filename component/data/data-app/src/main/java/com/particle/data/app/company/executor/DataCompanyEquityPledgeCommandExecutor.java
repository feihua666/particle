package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyEquityPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业股权出质 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Component
@Validated
public class DataCompanyEquityPledgeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway;
	private IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyEquityPledgeGateway
	 */
	@Autowired
	public void setDataCompanyEquityPledgeGateway(DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway) {
		this.dataCompanyEquityPledgeGateway = dataCompanyEquityPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyEquityPledgeService(IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService) {
		this.iDataCompanyEquityPledgeService = iDataCompanyEquityPledgeService;
	}
}
