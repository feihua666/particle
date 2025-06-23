package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyEndCaseAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.domain.company.DataCompanyEndCase;
import com.particle.data.domain.company.DataCompanyEndCaseId;
import com.particle.data.domain.company.gateway.DataCompanyEndCaseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业终本案件 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Component
@Validated
public class DataCompanyEndCaseDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEndCaseGateway dataCompanyEndCaseGateway;
	private IDataCompanyEndCaseService iDataCompanyEndCaseService;

	/**
	 * 执行 企业终本案件 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyEndCaseId dataCompanyEndCaseId = DataCompanyEndCaseId.of(deleteCommand.getId());
		DataCompanyEndCase byId = dataCompanyEndCaseGateway.getById(dataCompanyEndCaseId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyEndCaseGateway.delete(dataCompanyEndCaseId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyEndCaseAppStructMapping.instance.toDataCompanyEndCaseVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyEndCaseGateway
	 */
	@Autowired
	public void setDataCompanyEndCaseGateway(DataCompanyEndCaseGateway dataCompanyEndCaseGateway) {
		this.dataCompanyEndCaseGateway = dataCompanyEndCaseGateway;
	}
	@Autowired
	public void setIDataCompanyEndCaseService(IDataCompanyEndCaseService iDataCompanyEndCaseService) {
		this.iDataCompanyEndCaseService = iDataCompanyEndCaseService;
	}
}
