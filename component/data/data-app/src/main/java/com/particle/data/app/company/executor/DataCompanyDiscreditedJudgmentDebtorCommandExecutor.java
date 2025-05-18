package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyDiscreditedJudgmentDebtorGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDiscreditedJudgmentDebtorService;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业失信被执行人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDiscreditedJudgmentDebtorGateway dataCompanyDiscreditedJudgmentDebtorGateway;
	private IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyDiscreditedJudgmentDebtorGateway
	 */
	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorGateway(DataCompanyDiscreditedJudgmentDebtorGateway dataCompanyDiscreditedJudgmentDebtorGateway) {
		this.dataCompanyDiscreditedJudgmentDebtorGateway = dataCompanyDiscreditedJudgmentDebtorGateway;
	}
	@Autowired
	public void setIDataCompanyDiscreditedJudgmentDebtorService(IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService) {
		this.iDataCompanyDiscreditedJudgmentDebtorService = iDataCompanyDiscreditedJudgmentDebtorService;
	}
}
