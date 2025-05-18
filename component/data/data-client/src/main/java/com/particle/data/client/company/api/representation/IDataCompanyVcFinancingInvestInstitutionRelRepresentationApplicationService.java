package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingInvestInstitutionRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;

/**
 * <p>
 * 企业融资历史投资机构关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyVcFinancingInvestInstitutionRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyVcFinancingInvestInstitutionRelQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyVcFinancingInvestInstitutionRelVO> queryList(DataCompanyVcFinancingInvestInstitutionRelQueryListCommand dataCompanyVcFinancingInvestInstitutionRelQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyVcFinancingInvestInstitutionRelVO> pageQuery(DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand dataCompanyVcFinancingInvestInstitutionRelPageQueryCommand);

	/**
	 * 查询企业融资表ID已分配的企业投资机构表id
	 * @param companyVcFinancingIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId(IdCommand companyVcFinancingIdCommand);

	/**
	 * 查询企业投资机构表已分配的企业融资表IDid
	 * @param companyVcInvestInstitutionIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId(IdCommand companyVcInvestInstitutionIdCommand);

	/**
	 * 企业融资历史投资机构关系出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> exWarehouse(DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}