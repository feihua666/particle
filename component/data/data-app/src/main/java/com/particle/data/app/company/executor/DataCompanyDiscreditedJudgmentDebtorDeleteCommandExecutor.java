package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyDiscreditedJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtorId;
import com.particle.data.domain.company.gateway.DataCompanyDiscreditedJudgmentDebtorGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyDiscreditedJudgmentDebtorService;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业失信被执行人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDiscreditedJudgmentDebtorGateway dataCompanyDiscreditedJudgmentDebtorGateway;
	private IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService;

	/**
	 * 执行 企业失信被执行人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyDiscreditedJudgmentDebtorId dataCompanyDiscreditedJudgmentDebtorId = DataCompanyDiscreditedJudgmentDebtorId.of(deleteCommand.getId());
		DataCompanyDiscreditedJudgmentDebtor byId = dataCompanyDiscreditedJudgmentDebtorGateway.getById(dataCompanyDiscreditedJudgmentDebtorId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyDiscreditedJudgmentDebtorGateway.delete(dataCompanyDiscreditedJudgmentDebtorId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.toDataCompanyDiscreditedJudgmentDebtorVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
