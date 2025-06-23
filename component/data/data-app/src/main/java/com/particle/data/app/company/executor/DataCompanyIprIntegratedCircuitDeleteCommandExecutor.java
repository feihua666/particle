package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprIntegratedCircuitAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuit;
import com.particle.data.domain.company.DataCompanyIprIntegratedCircuitId;
import com.particle.data.domain.company.gateway.DataCompanyIprIntegratedCircuitGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权集成电路 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway;
	private IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService;

	/**
	 * 执行 企业知识产权集成电路 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprIntegratedCircuitId dataCompanyIprIntegratedCircuitId = DataCompanyIprIntegratedCircuitId.of(deleteCommand.getId());
		DataCompanyIprIntegratedCircuit byId = dataCompanyIprIntegratedCircuitGateway.getById(dataCompanyIprIntegratedCircuitId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprIntegratedCircuitGateway.delete(dataCompanyIprIntegratedCircuitId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprIntegratedCircuitAppStructMapping.instance.toDataCompanyIprIntegratedCircuitVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprIntegratedCircuitGateway
	 */
	@Autowired
	public void setDataCompanyIprIntegratedCircuitGateway(DataCompanyIprIntegratedCircuitGateway dataCompanyIprIntegratedCircuitGateway) {
		this.dataCompanyIprIntegratedCircuitGateway = dataCompanyIprIntegratedCircuitGateway;
	}
	@Autowired
	public void setIDataCompanyIprIntegratedCircuitService(IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService) {
		this.iDataCompanyIprIntegratedCircuitService = iDataCompanyIprIntegratedCircuitService;
	}
}
