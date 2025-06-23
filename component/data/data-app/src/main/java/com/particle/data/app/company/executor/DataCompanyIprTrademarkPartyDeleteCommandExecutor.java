package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkParty;
import com.particle.data.domain.company.DataCompanyIprTrademarkPartyId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway;
	private IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService;

	/**
	 * 执行 企业知识产权商标当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkPartyId dataCompanyIprTrademarkPartyId = DataCompanyIprTrademarkPartyId.of(deleteCommand.getId());
		DataCompanyIprTrademarkParty byId = dataCompanyIprTrademarkPartyGateway.getById(dataCompanyIprTrademarkPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkPartyGateway.delete(dataCompanyIprTrademarkPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkPartyAppStructMapping.instance.toDataCompanyIprTrademarkPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkPartyGateway(DataCompanyIprTrademarkPartyGateway dataCompanyIprTrademarkPartyGateway) {
		this.dataCompanyIprTrademarkPartyGateway = dataCompanyIprTrademarkPartyGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkPartyService(IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService) {
		this.iDataCompanyIprTrademarkPartyService = iDataCompanyIprTrademarkPartyService;
	}
}
