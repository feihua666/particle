package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcInvestInstitutionWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
/**
 * <p>
 * 企业投资机构 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
public interface IDataCompanyVcInvestInstitutionApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyVcInvestInstitutionCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcInvestInstitutionVO> create(DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcInvestInstitutionVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyVcInvestInstitutionUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcInvestInstitutionVO> update(DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand);

	/**
	 * 企业投资机构入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> warehouse(DataCompanyVcInvestInstitutionWarehouseCommand dataCompanyBasicWarehouseCommand);
}