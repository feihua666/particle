package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.structmapping.DataCompanyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.data.domain.company.DataCompany;
import com.particle.data.domain.company.DataCompanyId;
import com.particle.data.domain.company.gateway.DataCompanyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
@Validated
public class DataCompanyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyGateway dataCompanyGateway;
	private IDataCompanyService iDataCompanyService;

	/**
	 * 执行 企业 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyId dataCompanyId = DataCompanyId.of(deleteCommand.getId());
		DataCompany byId = dataCompanyGateway.getById(dataCompanyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyGateway.delete(dataCompanyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyAppStructMapping.instance.toDataCompanyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyGateway
	 */
	@Autowired
	public void setDataCompanyGateway(DataCompanyGateway dataCompanyGateway) {
		this.dataCompanyGateway = dataCompanyGateway;
	}
	@Autowired
	public void setIDataCompanyService(IDataCompanyService iDataCompanyService) {
		this.iDataCompanyService = iDataCompanyService;
	}
}
