package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyCaseFilingPartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.domain.company.DataCompanyCaseFilingParty;
import com.particle.data.domain.company.DataCompanyCaseFilingPartyId;
import com.particle.data.domain.company.gateway.DataCompanyCaseFilingPartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业立案信息当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway;
	private IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService;

	/**
	 * 执行 企业立案信息当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyCaseFilingPartyId dataCompanyCaseFilingPartyId = DataCompanyCaseFilingPartyId.of(deleteCommand.getId());
		DataCompanyCaseFilingParty byId = dataCompanyCaseFilingPartyGateway.getById(dataCompanyCaseFilingPartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyCaseFilingPartyGateway.delete(dataCompanyCaseFilingPartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyCaseFilingPartyAppStructMapping.instance.toDataCompanyCaseFilingPartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyCaseFilingPartyGateway
	 */
	@Autowired
	public void setDataCompanyCaseFilingPartyGateway(DataCompanyCaseFilingPartyGateway dataCompanyCaseFilingPartyGateway) {
		this.dataCompanyCaseFilingPartyGateway = dataCompanyCaseFilingPartyGateway;
	}
	@Autowired
	public void setIDataCompanyCaseFilingPartyService(IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService) {
		this.iDataCompanyCaseFilingPartyService = iDataCompanyCaseFilingPartyService;
	}
}
