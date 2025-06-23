package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffPositionAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.domain.company.DataCompanyPrimeStaffPosition;
import com.particle.data.domain.company.DataCompanyPrimeStaffPositionId;
import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffPositionGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业主要人员职位 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway;
	private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;

	/**
	 * 执行 企业主要人员职位 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyPrimeStaffPositionId dataCompanyPrimeStaffPositionId = DataCompanyPrimeStaffPositionId.of(deleteCommand.getId());
		DataCompanyPrimeStaffPosition byId = dataCompanyPrimeStaffPositionGateway.getById(dataCompanyPrimeStaffPositionId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyPrimeStaffPositionGateway.delete(dataCompanyPrimeStaffPositionId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyPrimeStaffPositionAppStructMapping.instance.toDataCompanyPrimeStaffPositionVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyPrimeStaffPositionGateway
	 */
	@Autowired
	public void setDataCompanyPrimeStaffPositionGateway(DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway) {
		this.dataCompanyPrimeStaffPositionGateway = dataCompanyPrimeStaffPositionGateway;
	}
	@Autowired
	public void setIDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
		this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
	}
}
