package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.domain.company.DataCompanyIprPatentParty;
import com.particle.data.domain.company.DataCompanyIprPatentPartyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Component
@Validated
public class DataCompanyIprPatentPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway;
	private IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService;

	/**
	 * 执行 企业知识产权当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentPartyId dataCompanyIprPatentPartyId = DataCompanyIprPatentPartyId.of(deleteCommand.getId());
		DataCompanyIprPatentParty byId = dataCompanyIprPatentPartyGateway.getById(dataCompanyIprPatentPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentPartyGateway.delete(dataCompanyIprPatentPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentPartyAppStructMapping.instance.toDataCompanyIprPatentPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPartyGateway(DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway) {
		this.dataCompanyIprPatentPartyGateway = dataCompanyIprPatentPartyGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentPartyService(IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService) {
		this.iDataCompanyIprPatentPartyService = iDataCompanyIprPatentPartyService;
	}
}
