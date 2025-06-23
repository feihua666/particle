package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
/**
 * <p>
 * 企业知识产权集成电路 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
public interface IDataCompanyIprIntegratedCircuitApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprIntegratedCircuitCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprIntegratedCircuitVO> create(DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprIntegratedCircuitVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprIntegratedCircuitUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprIntegratedCircuitVO> update(DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand);

	/**
	 * 企业知识产权集成电路入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> warehouse(DataCompanyIprIntegratedCircuitWarehouseCommand dataCompanyBasicWarehouseCommand);
}