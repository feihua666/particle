package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumePartyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeParty;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumePartyId;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumePartyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业限制高消费当事人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway;
	private IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService;

	/**
	 * 执行 企业限制高消费当事人 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyRestrictHighConsumePartyId dataCompanyRestrictHighConsumePartyId = DataCompanyRestrictHighConsumePartyId.of(deleteCommand.getId());
		DataCompanyRestrictHighConsumeParty byId = dataCompanyRestrictHighConsumePartyGateway.getById(dataCompanyRestrictHighConsumePartyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyRestrictHighConsumePartyGateway.delete(dataCompanyRestrictHighConsumePartyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyRestrictHighConsumePartyAppStructMapping.instance.toDataCompanyRestrictHighConsumePartyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyRestrictHighConsumePartyGateway
	 */
	@Autowired
	public void setDataCompanyRestrictHighConsumePartyGateway(DataCompanyRestrictHighConsumePartyGateway dataCompanyRestrictHighConsumePartyGateway) {
		this.dataCompanyRestrictHighConsumePartyGateway = dataCompanyRestrictHighConsumePartyGateway;
	}
	@Autowired
	public void setIDataCompanyRestrictHighConsumePartyService(IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService) {
		this.iDataCompanyRestrictHighConsumePartyService = iDataCompanyRestrictHighConsumePartyService;
	}
}
