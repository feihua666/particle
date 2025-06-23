package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权出质 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Component
@Validated
public class DataCompanyIprPledgeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway;
	private IDataCompanyIprPledgeService iDataCompanyIprPledgeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprPledgeGateway(DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway) {
		this.dataCompanyIprPledgeGateway = dataCompanyIprPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPledgeService(IDataCompanyIprPledgeService iDataCompanyIprPledgeService) {
		this.iDataCompanyIprPledgeService = iDataCompanyIprPledgeService;
	}
}
