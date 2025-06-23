package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyEquityPledgeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.domain.company.DataCompanyEquityPledge;
import com.particle.data.domain.company.DataCompanyEquityPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyEquityPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业股权出质 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Component
@Validated
public class DataCompanyEquityPledgeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway;
	private IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService;

	/**
	 * 执行 企业股权出质 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyEquityPledgeId dataCompanyEquityPledgeId = DataCompanyEquityPledgeId.of(deleteCommand.getId());
		DataCompanyEquityPledge byId = dataCompanyEquityPledgeGateway.getById(dataCompanyEquityPledgeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyEquityPledgeGateway.delete(dataCompanyEquityPledgeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyEquityPledgeAppStructMapping.instance.toDataCompanyEquityPledgeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyEquityPledgeGateway
	 */
	@Autowired
	public void setDataCompanyEquityPledgeGateway(DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway) {
		this.dataCompanyEquityPledgeGateway = dataCompanyEquityPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyEquityPledgeService(IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService) {
		this.iDataCompanyEquityPledgeService = iDataCompanyEquityPledgeService;
	}
}
