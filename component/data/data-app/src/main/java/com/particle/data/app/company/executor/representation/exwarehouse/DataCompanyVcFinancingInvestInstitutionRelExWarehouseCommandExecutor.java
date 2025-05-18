package com.particle.data.app.company.executor.representation.exwarehouse;

import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyVcFinancingInvestInstitutionRelAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业融资历史投资机构关系出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyVcFinancingInvestInstitutionRelExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService;

	/**
	 * 企业融资历史投资机构关系出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> exWarehouse(@Valid DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		return exWarehouseByCompanyVcFinancingIdAndCompanyVcInvestInstitutionId(dataCompanyExWarehouseQueryCommand.getCompanyVcFinancingId(),dataCompanyExWarehouseQueryCommand.getCompanyVcInvestInstitutionId());
	}
	/**
	 * 企业融资历史投资机构关系出库
	 * @param companyVcInvestInstitutionId
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> exWarehouseByCompanyVcFinancingIdAndCompanyVcInvestInstitutionId(Long companyVcFinancingId,Long companyVcInvestInstitutionId) {
		DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelDO = iDataCompanyVcFinancingInvestInstitutionRelService.getByCompanyVcFinancingIdAndCompanyVcInvestInstitutionId(companyVcFinancingId,companyVcInvestInstitutionId);
		if (dataCompanyVcFinancingInvestInstitutionRelDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO dataCompanyVcFinancingInvestInstitutionRelExWarehouseVO = DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRelExWarehouseVO(dataCompanyVcFinancingInvestInstitutionRelDO);
		return SingleResponse.of(dataCompanyVcFinancingInvestInstitutionRelExWarehouseVO);
	}
	/**
	 * 企业融资历史投资机构关系出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelDO = iDataCompanyVcFinancingInvestInstitutionRelService.getById(id);
		if (dataCompanyVcFinancingInvestInstitutionRelDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO dataCompanyVcFinancingInvestInstitutionRelExWarehouseVO = DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRelExWarehouseVO(dataCompanyVcFinancingInvestInstitutionRelDO);
		return SingleResponse.of(dataCompanyVcFinancingInvestInstitutionRelExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyVcFinancingInvestInstitutionRelService(IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService) {
		this.iDataCompanyVcFinancingInvestInstitutionRelService = iDataCompanyVcFinancingInvestInstitutionRelService;
	}
}
