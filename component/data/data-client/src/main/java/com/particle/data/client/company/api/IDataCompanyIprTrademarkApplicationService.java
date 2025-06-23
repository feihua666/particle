package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
public interface IDataCompanyIprTrademarkApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkVO> create(DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkVO> update(DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand);

	/**
	 * 企业知识产权商标入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkExWarehouseVO> warehouse(DataCompanyIprTrademarkWarehouseCommand dataCompanyBasicWarehouseCommand);
}