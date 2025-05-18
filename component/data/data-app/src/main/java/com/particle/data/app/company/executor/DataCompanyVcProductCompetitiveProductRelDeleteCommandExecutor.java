package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyVcProductCompetitiveProductRelAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRel;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRelId;
import com.particle.data.domain.company.gateway.DataCompanyVcProductCompetitiveProductRelGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资产品竞品关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Component
@Validated
public class DataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway;
	private IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService;

	/**
	 * 执行 企业融资产品竞品关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyVcProductCompetitiveProductRelId dataCompanyVcProductCompetitiveProductRelId = DataCompanyVcProductCompetitiveProductRelId.of(deleteCommand.getId());
		DataCompanyVcProductCompetitiveProductRel byId = dataCompanyVcProductCompetitiveProductRelGateway.getById(dataCompanyVcProductCompetitiveProductRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyVcProductCompetitiveProductRelGateway.delete(dataCompanyVcProductCompetitiveProductRelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.toDataCompanyVcProductCompetitiveProductRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 根据 companyVcProductId 删除
	 * @param companyVcProductIdCommand
	 * @return
	 */
	public Response deleteByCompanyVcProductId(@Valid IdCommand companyVcProductIdCommand) {
		boolean result = iDataCompanyVcProductCompetitiveProductRelService.deleteByColumn(companyVcProductIdCommand.getId(), DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 companyVcCompetitiveProductId 删除
	 * @param companyVcCompetitiveProductIdCommand
	 * @return
	 */
	public Response deleteByCompanyVcCompetitiveProductId(@Valid IdCommand companyVcCompetitiveProductIdCommand) {
		boolean result = iDataCompanyVcProductCompetitiveProductRelService.deleteByColumn(companyVcCompetitiveProductIdCommand.getId(), DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcCompetitiveProductId);
		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductCompetitiveProductRelGateway
	 */
	@Autowired
	public void setDataCompanyVcProductCompetitiveProductRelGateway(DataCompanyVcProductCompetitiveProductRelGateway dataCompanyVcProductCompetitiveProductRelGateway) {
		this.dataCompanyVcProductCompetitiveProductRelGateway = dataCompanyVcProductCompetitiveProductRelGateway;
	}
	@Autowired
	public void setIDataCompanyVcProductCompetitiveProductRelService(IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService) {
		this.iDataCompanyVcProductCompetitiveProductRelService = iDataCompanyVcProductCompetitiveProductRelService;
	}
}
