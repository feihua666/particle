package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPledgeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.domain.company.DataCompanyIprPledge;
import com.particle.data.domain.company.DataCompanyIprPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权出质 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Component
@Validated
public class DataCompanyIprPledgeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway;
	private IDataCompanyIprPledgeService iDataCompanyIprPledgeService;

	/**
	 * 执行 企业知识产权出质 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPledgeId dataCompanyIprPledgeId = DataCompanyIprPledgeId.of(deleteCommand.getId());
		DataCompanyIprPledge byId = dataCompanyIprPledgeGateway.getById(dataCompanyIprPledgeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPledgeGateway.delete(dataCompanyIprPledgeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPledgeAppStructMapping.instance.toDataCompanyIprPledgeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprPledgeGateway(DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway) {
		this.dataCompanyIprPledgeGateway = dataCompanyIprPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPledgeService(IDataCompanyIprPledgeService iDataCompanyIprPledgeService) {
		this.iDataCompanyIprPledgeService = iDataCompanyIprPledgeService;
	}
}
