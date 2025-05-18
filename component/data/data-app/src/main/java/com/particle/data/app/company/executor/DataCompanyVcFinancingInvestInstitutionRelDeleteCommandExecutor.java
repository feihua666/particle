package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyVcFinancingInvestInstitutionRelAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRel;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRelId;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingInvestInstitutionRelGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资历史投资机构关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Component
@Validated
public class DataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway;
	private IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService;

	/**
	 * 执行 企业融资历史投资机构关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyVcFinancingInvestInstitutionRelId dataCompanyVcFinancingInvestInstitutionRelId = DataCompanyVcFinancingInvestInstitutionRelId.of(deleteCommand.getId());
		DataCompanyVcFinancingInvestInstitutionRel byId = dataCompanyVcFinancingInvestInstitutionRelGateway.getById(dataCompanyVcFinancingInvestInstitutionRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyVcFinancingInvestInstitutionRelGateway.delete(dataCompanyVcFinancingInvestInstitutionRelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.toDataCompanyVcFinancingInvestInstitutionRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 根据 companyVcFinancingId 删除
	 * @param companyVcFinancingIdCommand
	 * @return
	 */
	public Response deleteByCompanyVcFinancingId(@Valid IdCommand companyVcFinancingIdCommand) {
		boolean result = iDataCompanyVcFinancingInvestInstitutionRelService.deleteByColumn(companyVcFinancingIdCommand.getId(), DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 companyVcInvestInstitutionId 删除
	 * @param companyVcInvestInstitutionIdCommand
	 * @return
	 */
	public Response deleteByCompanyVcInvestInstitutionId(@Valid IdCommand companyVcInvestInstitutionIdCommand) {
		boolean result = iDataCompanyVcFinancingInvestInstitutionRelService.deleteByColumn(companyVcInvestInstitutionIdCommand.getId(), DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcInvestInstitutionId);
		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingInvestInstitutionRelGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingInvestInstitutionRelGateway(DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway) {
		this.dataCompanyVcFinancingInvestInstitutionRelGateway = dataCompanyVcFinancingInvestInstitutionRelGateway;
	}
	@Autowired
	public void setIDataCompanyVcFinancingInvestInstitutionRelService(IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService) {
		this.iDataCompanyVcFinancingInvestInstitutionRelService = iDataCompanyVcFinancingInvestInstitutionRelService;
	}
}
