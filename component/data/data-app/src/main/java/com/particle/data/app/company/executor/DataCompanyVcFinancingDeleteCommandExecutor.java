package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyVcFinancingAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.domain.company.DataCompanyVcFinancing;
import com.particle.data.domain.company.DataCompanyVcFinancingId;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Component
@Validated
public class DataCompanyVcFinancingDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway;
	private IDataCompanyVcFinancingService iDataCompanyVcFinancingService;

	/**
	 * 执行 企业融资 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyVcFinancingId dataCompanyVcFinancingId = DataCompanyVcFinancingId.of(deleteCommand.getId());
		DataCompanyVcFinancing byId = dataCompanyVcFinancingGateway.getById(dataCompanyVcFinancingId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyVcFinancingGateway.delete(dataCompanyVcFinancingId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyVcFinancingAppStructMapping.instance.toDataCompanyVcFinancingVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingGateway(DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway) {
		this.dataCompanyVcFinancingGateway = dataCompanyVcFinancingGateway;
	}
	@Autowired
	public void setIDataCompanyVcFinancingService(IDataCompanyVcFinancingService iDataCompanyVcFinancingService) {
		this.iDataCompanyVcFinancingService = iDataCompanyVcFinancingService;
	}
}
