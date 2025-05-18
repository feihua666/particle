package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyVcInvestInstitutionAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.domain.company.DataCompanyVcInvestInstitution;
import com.particle.data.domain.company.DataCompanyVcInvestInstitutionId;
import com.particle.data.domain.company.gateway.DataCompanyVcInvestInstitutionGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业投资机构 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcInvestInstitutionGateway dataCompanyVcInvestInstitutionGateway;
	private IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService;

	/**
	 * 执行 企业投资机构 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyVcInvestInstitutionId dataCompanyVcInvestInstitutionId = DataCompanyVcInvestInstitutionId.of(deleteCommand.getId());
		DataCompanyVcInvestInstitution byId = dataCompanyVcInvestInstitutionGateway.getById(dataCompanyVcInvestInstitutionId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyVcInvestInstitutionGateway.delete(dataCompanyVcInvestInstitutionId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyVcInvestInstitutionAppStructMapping.instance.toDataCompanyVcInvestInstitutionVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyVcInvestInstitutionGateway
	 */
	@Autowired
	public void setDataCompanyVcInvestInstitutionGateway(DataCompanyVcInvestInstitutionGateway dataCompanyVcInvestInstitutionGateway) {
		this.dataCompanyVcInvestInstitutionGateway = dataCompanyVcInvestInstitutionGateway;
	}
	@Autowired
	public void setIDataCompanyVcInvestInstitutionService(IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService) {
		this.iDataCompanyVcInvestInstitutionService = iDataCompanyVcInvestInstitutionService;
	}
}
