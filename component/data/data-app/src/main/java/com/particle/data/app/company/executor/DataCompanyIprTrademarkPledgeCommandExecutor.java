package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标质押信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway;
	private IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkPledgeGateway(DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway) {
		this.dataCompanyIprTrademarkPledgeGateway = dataCompanyIprTrademarkPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkPledgeService(IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService) {
		this.iDataCompanyIprTrademarkPledgeService = iDataCompanyIprTrademarkPledgeService;
	}
}
