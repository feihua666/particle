package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业裁判文书当事人 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentPartyGateway dataCompanyJudgmentDocumentPartyGateway;
	private IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDocumentPartyGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDocumentPartyGateway(DataCompanyJudgmentDocumentPartyGateway dataCompanyJudgmentDocumentPartyGateway) {
		this.dataCompanyJudgmentDocumentPartyGateway = dataCompanyJudgmentDocumentPartyGateway;
	}
	@Autowired
	public void setIDataCompanyJudgmentDocumentPartyService(IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService) {
		this.iDataCompanyJudgmentDocumentPartyService = iDataCompanyJudgmentDocumentPartyService;
	}
}
