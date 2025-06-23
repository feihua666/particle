package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
/**
 * <p>
 * 企业主要人员 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
public interface IDataCompanyPrimeStaffApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyPrimeStaffCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffVO> create(DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyPrimeStaffUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffVO> update(DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand);

	/**
	 * 企业主要人员入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffExWarehouseVO> warehouse(DataCompanyPrimeStaffWarehouseCommand dataCompanyBasicWarehouseCommand);
}