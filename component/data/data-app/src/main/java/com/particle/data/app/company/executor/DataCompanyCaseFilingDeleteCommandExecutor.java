package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyCaseFilingAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.domain.company.DataCompanyCaseFiling;
import com.particle.data.domain.company.DataCompanyCaseFilingId;
import com.particle.data.domain.company.gateway.DataCompanyCaseFilingGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业立案信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Component
@Validated
public class DataCompanyCaseFilingDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway;
	private IDataCompanyCaseFilingService iDataCompanyCaseFilingService;

	/**
	 * 执行 企业立案信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyCaseFilingId dataCompanyCaseFilingId = DataCompanyCaseFilingId.of(deleteCommand.getId());
		DataCompanyCaseFiling byId = dataCompanyCaseFilingGateway.getById(dataCompanyCaseFilingId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyCaseFilingGateway.delete(dataCompanyCaseFilingId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyCaseFilingAppStructMapping.instance.toDataCompanyCaseFilingVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyCaseFilingGateway
	 */
	@Autowired
	public void setDataCompanyCaseFilingGateway(DataCompanyCaseFilingGateway dataCompanyCaseFilingGateway) {
		this.dataCompanyCaseFilingGateway = dataCompanyCaseFilingGateway;
	}
	@Autowired
	public void setIDataCompanyCaseFilingService(IDataCompanyCaseFilingService iDataCompanyCaseFilingService) {
		this.iDataCompanyCaseFilingService = iDataCompanyCaseFilingService;
	}
}
