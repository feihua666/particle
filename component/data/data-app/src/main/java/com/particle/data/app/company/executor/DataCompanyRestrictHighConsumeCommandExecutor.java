package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业限制高消费 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway;
	private IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyRestrictHighConsumeGateway
	 */
	@Autowired
	public void setDataCompanyRestrictHighConsumeGateway(DataCompanyRestrictHighConsumeGateway dataCompanyRestrictHighConsumeGateway) {
		this.dataCompanyRestrictHighConsumeGateway = dataCompanyRestrictHighConsumeGateway;
	}
	@Autowired
	public void setIDataCompanyRestrictHighConsumeService(IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService) {
		this.iDataCompanyRestrictHighConsumeService = iDataCompanyRestrictHighConsumeService;
	}
}
