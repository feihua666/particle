package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumePartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业限制高消费当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway;
	private IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyRestrictHighConsumePartyGateway
	 */
	@Autowired
	public void setDataCompanyRestrictHighConsumePartyGateway(DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway) {
		this.dataCompanyRestrictHighConsumePartyGateway = dataCompanyRestrictHighConsumePartyGateway;
	}
	@Autowired
	public void setIDataCompanyRestrictHighConsumePartyService(IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService) {
		this.iDataCompanyRestrictHighConsumePartyService = iDataCompanyRestrictHighConsumePartyService;
	}
}
