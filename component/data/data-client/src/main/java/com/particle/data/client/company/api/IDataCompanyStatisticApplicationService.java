package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyStatisticCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyStatisticUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
/**
 * <p>
 * 企业统计 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
public interface IDataCompanyStatisticApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyStatisticCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyStatisticVO> create(DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyStatisticVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyStatisticUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyStatisticVO> update(DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand);

	/**
	 * 企业统计入库
	 * @param dataCompanyStatisticWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyStatisticExWarehouseVO> warehouse(DataCompanyStatisticWarehouseCommand dataCompanyStatisticWarehouseCommand);
}