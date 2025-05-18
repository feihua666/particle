package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyPersonCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPersonUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
/**
 * <p>
 * 企业个人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
public interface IDataCompanyPersonApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyPersonCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPersonVO> create(DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyPersonVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyPersonUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPersonVO> update(DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand);

	/**
	 * 企业个人入库
	 * @param dataCompanyPersonWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyPersonExWarehouseVO> warehouse(DataCompanyPersonWarehouseCommand dataCompanyPersonWarehouseCommand);
}