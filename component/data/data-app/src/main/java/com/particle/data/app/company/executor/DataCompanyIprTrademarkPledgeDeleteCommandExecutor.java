package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPledgeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledge;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权商标质押信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway;
	private IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService;

	/**
	 * 执行 企业知识产权商标质押信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprTrademarkPledgeId dataCompanyIprTrademarkPledgeId = DataCompanyIprTrademarkPledgeId.of(deleteCommand.getId());
		DataCompanyIprTrademarkPledge byId = dataCompanyIprTrademarkPledgeGateway.getById(dataCompanyIprTrademarkPledgeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprTrademarkPledgeGateway.delete(dataCompanyIprTrademarkPledgeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprTrademarkPledgeAppStructMapping.instance.toDataCompanyIprTrademarkPledgeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkPledgeGateway(DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway) {
		this.dataCompanyIprTrademarkPledgeGateway = dataCompanyIprTrademarkPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyIprTrademarkPledgeService(IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService) {
		this.iDataCompanyIprTrademarkPledgeService = iDataCompanyIprTrademarkPledgeService;
	}
}
