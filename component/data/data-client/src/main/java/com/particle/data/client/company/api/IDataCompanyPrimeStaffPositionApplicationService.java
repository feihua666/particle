package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffPositionWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
/**
 * <p>
 * 企业主要人员职位 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
public interface IDataCompanyPrimeStaffPositionApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyPrimeStaffPositionCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffPositionVO> create(DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffPositionVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyPrimeStaffPositionUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffPositionVO> update(DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand);

	/**
	 * 企业主要人员职位入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> warehouse(DataCompanyPrimeStaffPositionWarehouseCommand dataCompanyBasicWarehouseCommand);
}