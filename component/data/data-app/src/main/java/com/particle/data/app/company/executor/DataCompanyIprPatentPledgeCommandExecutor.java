package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利质押信息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway;
	private IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPledgeGateway(DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway) {
		this.dataCompanyIprPatentPledgeGateway = dataCompanyIprPatentPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentPledgeService(IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService) {
		this.iDataCompanyIprPatentPledgeService = iDataCompanyIprPatentPledgeService;
	}
}
