package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPledgeAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.domain.company.DataCompanyIprPatentPledge;
import com.particle.data.domain.company.DataCompanyIprPatentPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利质押信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway;
	private IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService;

	/**
	 * 执行 企业知识产权专利质押信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentPledgeId dataCompanyIprPatentPledgeId = DataCompanyIprPatentPledgeId.of(deleteCommand.getId());
		DataCompanyIprPatentPledge byId = dataCompanyIprPatentPledgeGateway.getById(dataCompanyIprPatentPledgeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentPledgeGateway.delete(dataCompanyIprPatentPledgeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentPledgeAppStructMapping.instance.toDataCompanyIprPatentPledgeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPledgeGateway(DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway) {
		this.dataCompanyIprPatentPledgeGateway = dataCompanyIprPatentPledgeGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentPledgeService(IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService) {
		this.iDataCompanyIprPatentPledgeService = iDataCompanyIprPatentPledgeService;
	}
}
