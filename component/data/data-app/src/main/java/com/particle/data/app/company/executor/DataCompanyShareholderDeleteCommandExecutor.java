package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyShareholderAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.domain.company.DataCompanyShareholder;
import com.particle.data.domain.company.DataCompanyShareholderId;
import com.particle.data.domain.company.gateway.DataCompanyShareholderGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业股东 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Component
@Validated
public class DataCompanyShareholderDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyShareholderGateway dataCompanyShareholderGateway;
	private IDataCompanyShareholderService iDataCompanyShareholderService;

	/**
	 * 执行 企业股东 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyShareholderId dataCompanyShareholderId = DataCompanyShareholderId.of(deleteCommand.getId());
		DataCompanyShareholder byId = dataCompanyShareholderGateway.getById(dataCompanyShareholderId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyShareholderGateway.delete(dataCompanyShareholderId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyShareholderAppStructMapping.instance.toDataCompanyShareholderVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyShareholderGateway
	 */
	@Autowired
	public void setDataCompanyShareholderGateway(DataCompanyShareholderGateway dataCompanyShareholderGateway) {
		this.dataCompanyShareholderGateway = dataCompanyShareholderGateway;
	}
	@Autowired
	public void setIDataCompanyShareholderService(IDataCompanyShareholderService iDataCompanyShareholderService) {
		this.iDataCompanyShareholderService = iDataCompanyShareholderService;
	}
}
