package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentContentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContent;
import com.particle.data.domain.company.DataCompanyJudgmentDocumentContentId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDocumentContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业裁判文书内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentContentGateway dataCompanyJudgmentDocumentContentGateway;
	private IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService;

	/**
	 * 执行 企业裁判文书内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyJudgmentDocumentContentId dataCompanyJudgmentDocumentContentId = DataCompanyJudgmentDocumentContentId.of(deleteCommand.getId());
		DataCompanyJudgmentDocumentContent byId = dataCompanyJudgmentDocumentContentGateway.getById(dataCompanyJudgmentDocumentContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyJudgmentDocumentContentGateway.delete(dataCompanyJudgmentDocumentContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyJudgmentDocumentContentAppStructMapping.instance.toDataCompanyJudgmentDocumentContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
