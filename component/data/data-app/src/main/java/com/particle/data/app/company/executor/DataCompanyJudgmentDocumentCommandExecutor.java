package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业裁判文书 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentGateway dataCompanyJudgmentDocumentGateway;
	private IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDocumentGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDocumentGateway(DataCompanyJudgmentDocumentGateway dataCompanyJudgmentDocumentGateway) {
		this.dataCompanyJudgmentDocumentGateway = dataCompanyJudgmentDocumentGateway;
	}
	@Autowired
	public void setIDataCompanyJudgmentDocumentService(IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService) {
		this.iDataCompanyJudgmentDocumentService = iDataCompanyJudgmentDocumentService;
	}
}
