package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyJudgmentDebtorGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业被执行人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDebtorGateway dataCompanyJudgmentDebtorGateway;
	private IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDebtorGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDebtorGateway(DataCompanyJudgmentDebtorGateway dataCompanyJudgmentDebtorGateway) {
		this.dataCompanyJudgmentDebtorGateway = dataCompanyJudgmentDebtorGateway;
	}
	@Autowired
	public void setIDataCompanyJudgmentDebtorService(IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService) {
		this.iDataCompanyJudgmentDebtorService = iDataCompanyJudgmentDebtorService;
	}
}
