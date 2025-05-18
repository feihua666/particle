package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业裁判文书内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentContentGateway dataCompanyJudgmentDocumentContentGateway;
	private IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService;
	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDocumentContentGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDocumentContentGateway(DataCompanyJudgmentDocumentContentGateway dataCompanyJudgmentDocumentContentGateway) {
		this.dataCompanyJudgmentDocumentContentGateway = dataCompanyJudgmentDocumentContentGateway;
	}
	@Autowired
	public void setIDataCompanyJudgmentDocumentContentService(IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService) {
		this.iDataCompanyJudgmentDocumentContentService = iDataCompanyJudgmentDocumentContentService;
	}
}
