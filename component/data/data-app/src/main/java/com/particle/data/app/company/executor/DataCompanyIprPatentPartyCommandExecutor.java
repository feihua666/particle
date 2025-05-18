package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyIprPatentPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Component
@Validated
public class DataCompanyIprPatentPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway;
	private IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPartyGateway(DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway) {
		this.dataCompanyIprPatentPartyGateway = dataCompanyIprPatentPartyGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentPartyService(IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService) {
		this.iDataCompanyIprPatentPartyService = iDataCompanyIprPatentPartyService;
	}
}
