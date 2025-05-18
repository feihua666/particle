package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.client.company.dto.command.CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand;
import com.particle.data.client.company.dto.command.CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;
/**
 * <p>
 * 企业融资历史投资机构关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
public interface IDataCompanyVcFinancingInvestInstitutionRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyVcFinancingInvestInstitutionRelCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> create(DataCompanyVcFinancingInvestInstitutionRelCreateCommand dataCompanyVcFinancingInvestInstitutionRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyVcFinancingInvestInstitutionRelUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> update(DataCompanyVcFinancingInvestInstitutionRelUpdateCommand dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);


	/**
	 * 企业融资表ID分配企业投资机构表
	 * @param cf
	 * @return
	 */
	Response companyVcFinancingAssignCompanyVcInvestInstitution(CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand cf);

	/**
	 * 企业投资机构表分配企业融资表ID
	 * @param cf
	 * @return
	 */
	Response companyVcInvestInstitutionAssignCompanyVcFinancing(CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand cf);

	/**
	 * 根据企业融资表IDid删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByCompanyVcFinancingId(IdCommand idCommand);

	/**
	 * 根据企业投资机构表id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByCompanyVcInvestInstitutionId(IdCommand idCommand);


	/**
	 * 企业融资历史投资机构关系入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> warehouse(DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand dataCompanyBasicWarehouseCommand);
}