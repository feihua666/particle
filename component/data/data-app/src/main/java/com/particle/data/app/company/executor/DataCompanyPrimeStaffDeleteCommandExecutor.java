package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.domain.company.DataCompanyPrimeStaff;
import com.particle.data.domain.company.DataCompanyPrimeStaffId;
import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业主要人员 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Component
@Validated
public class DataCompanyPrimeStaffDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway;
	private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;

	/**
	 * 执行 企业主要人员 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyPrimeStaffId dataCompanyPrimeStaffId = DataCompanyPrimeStaffId.of(deleteCommand.getId());
		DataCompanyPrimeStaff byId = dataCompanyPrimeStaffGateway.getById(dataCompanyPrimeStaffId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyPrimeStaffGateway.delete(dataCompanyPrimeStaffId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyPrimeStaffAppStructMapping.instance.toDataCompanyPrimeStaffVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyPrimeStaffGateway
	 */
	@Autowired
	public void setDataCompanyPrimeStaffGateway(DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway) {
		this.dataCompanyPrimeStaffGateway = dataCompanyPrimeStaffGateway;
	}
	@Autowired
	public void setIDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
		this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
	}
}
