package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentParty;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentPartyId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业裁判文书当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentPartyGateway dataCompanyJudgmentDocumentPartyGateway;
	private IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService;

	/**
	 * 执行 企业裁判文书当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyJudgmentDocumentPartyId dataCompanyJudgmentDocumentPartyId = DataCompanyJudgmentDocumentPartyId.of(deleteCommand.getId());
		DataCompanyJudgmentDocumentParty byId = dataCompanyJudgmentDocumentPartyGateway.getById(dataCompanyJudgmentDocumentPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyJudgmentDocumentPartyGateway.delete(dataCompanyJudgmentDocumentPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyJudgmentDocumentPartyAppStructMapping.instance.toDataCompanyJudgmentDocumentPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
