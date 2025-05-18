package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.domain.company.DataCompanyJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyJudgmentDebtorId;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDebtorGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业被执行人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDebtorGateway dataCompanyJudgmentDebtorGateway;
	private IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService;

	/**
	 * 执行 企业被执行人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyJudgmentDebtorId dataCompanyJudgmentDebtorId = DataCompanyJudgmentDebtorId.of(deleteCommand.getId());
		DataCompanyJudgmentDebtor byId = dataCompanyJudgmentDebtorGateway.getById(dataCompanyJudgmentDebtorId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyJudgmentDebtorGateway.delete(dataCompanyJudgmentDebtorId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyJudgmentDebtorAppStructMapping.instance.toDataCompanyJudgmentDebtorVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
