package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
/**
 * <p>
 * 企业知识产权地理标识 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
public interface IDataCompanyIprGeograApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprGeograCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograVO> create(DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprGeograUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograVO> update(DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand);

	/**
	 * 企业知识产权地理标识入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograExWarehouseVO> warehouse(DataCompanyIprGeograWarehouseCommand dataCompanyBasicWarehouseCommand);
}