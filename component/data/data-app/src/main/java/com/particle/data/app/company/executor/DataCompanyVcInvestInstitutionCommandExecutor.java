package com.particle.data.app.company.executor;

import com.particle.data.domain.company.gateway.DataCompanyVcInvestInstitutionGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业投资机构 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcInvestInstitutionGateway dataCompanyVcInvestInstitutionGateway;
	private IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService;
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
