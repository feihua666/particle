package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcInvestInstitutionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;

/**
 * <p>
 * 企业投资机构 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyVcInvestInstitutionRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcInvestInstitutionVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcInvestInstitutionVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyVcInvestInstitutionQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyVcInvestInstitutionVO> queryList(DataCompanyVcInvestInstitutionQueryListCommand dataCompanyVcInvestInstitutionQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyVcInvestInstitutionPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyVcInvestInstitutionVO> pageQuery(DataCompanyVcInvestInstitutionPageQueryCommand dataCompanyVcInvestInstitutionPageQueryCommand);


	/**
	 * 企业投资机构出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcInvestInstitutionExWarehouseVO> exWarehouse(DataCompanyVcInvestInstitutionExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
