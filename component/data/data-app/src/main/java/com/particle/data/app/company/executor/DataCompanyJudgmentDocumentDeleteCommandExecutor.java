package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocument;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业裁判文书 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentGateway dataCompanyJudgmentDocumentGateway;
	private IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService;

	/**
	 * 执行 企业裁判文书 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyJudgmentDocumentId dataCompanyJudgmentDocumentId = DataCompanyJudgmentDocumentId.of(deleteCommand.getId());
		DataCompanyJudgmentDocument byId = dataCompanyJudgmentDocumentGateway.getById(dataCompanyJudgmentDocumentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyJudgmentDocumentGateway.delete(dataCompanyJudgmentDocumentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyJudgmentDocumentAppStructMapping.instance.toDataCompanyJudgmentDocumentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
