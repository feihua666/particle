package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
/**
 * <p>
 * 企业融资 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
public interface IDataCompanyVcFinancingApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyVcFinancingCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingVO> create(DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyVcFinancingUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingVO> update(DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand);

	/**
	 * 企业融资入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingExWarehouseVO> warehouse(DataCompanyVcFinancingWarehouseCommand dataCompanyBasicWarehouseCommand);
}