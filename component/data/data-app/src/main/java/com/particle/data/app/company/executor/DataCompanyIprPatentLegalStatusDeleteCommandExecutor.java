package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentLegalStatusAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatus;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatusId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLegalStatusGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利法律状态 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway;
	private IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService;

	/**
	 * 执行 企业知识产权专利法律状态 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentLegalStatusId dataCompanyIprPatentLegalStatusId = DataCompanyIprPatentLegalStatusId.of(deleteCommand.getId());
		DataCompanyIprPatentLegalStatus byId = dataCompanyIprPatentLegalStatusGateway.getById(dataCompanyIprPatentLegalStatusId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentLegalStatusGateway.delete(dataCompanyIprPatentLegalStatusId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentLegalStatusAppStructMapping.instance.toDataCompanyIprPatentLegalStatusVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLegalStatusGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLegalStatusGateway(DataCompanyIprPatentLegalStatusGateway dataCompanyIprPatentLegalStatusGateway) {
		this.dataCompanyIprPatentLegalStatusGateway = dataCompanyIprPatentLegalStatusGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentLegalStatusService(IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService) {
		this.iDataCompanyIprPatentLegalStatusService = iDataCompanyIprPatentLegalStatusService;
	}
}
