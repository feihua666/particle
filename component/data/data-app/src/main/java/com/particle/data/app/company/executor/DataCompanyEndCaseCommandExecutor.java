package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyEndCaseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业终本案件 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Component
@Validated
public class DataCompanyEndCaseCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEndCaseGateway dataCompanyEndCaseGateway;
	private IDataCompanyEndCaseService iDataCompanyEndCaseService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyEndCaseGateway
	 */
	@Autowired
	public void setDataCompanyEndCaseGateway(DataCompanyEndCaseGateway dataCompanyEndCaseGateway) {
		this.dataCompanyEndCaseGateway = dataCompanyEndCaseGateway;
	}
	@Autowired
	public void setIDataCompanyEndCaseService(IDataCompanyEndCaseService iDataCompanyEndCaseService) {
		this.iDataCompanyEndCaseService = iDataCompanyEndCaseService;
	}
}
