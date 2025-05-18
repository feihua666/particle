package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyPersonAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.domain.company.DataCompanyPerson;
import com.particle.data.domain.company.DataCompanyPersonId;
import com.particle.data.domain.company.gateway.DataCompanyPersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业个人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Component
@Validated
public class DataCompanyPersonDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPersonGateway dataCompanyPersonGateway;
	private IDataCompanyPersonService iDataCompanyPersonService;

	/**
	 * 执行 企业个人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyPersonId dataCompanyPersonId = DataCompanyPersonId.of(deleteCommand.getId());
		DataCompanyPerson byId = dataCompanyPersonGateway.getById(dataCompanyPersonId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyPersonGateway.delete(dataCompanyPersonId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyPersonAppStructMapping.instance.toDataCompanyPersonVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyPersonGateway
	 */
	@Autowired
	public void setDataCompanyPersonGateway(DataCompanyPersonGateway dataCompanyPersonGateway) {
		this.dataCompanyPersonGateway = dataCompanyPersonGateway;
	}
	@Autowired
	public void setIDataCompanyPersonService(IDataCompanyPersonService iDataCompanyPersonService) {
		this.iDataCompanyPersonService = iDataCompanyPersonService;
	}
}
