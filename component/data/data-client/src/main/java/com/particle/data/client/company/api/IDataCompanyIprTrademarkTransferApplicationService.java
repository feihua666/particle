package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标转让信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
public interface IDataCompanyIprTrademarkTransferApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkTransferCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferVO> create(DataCompanyIprTrademarkTransferCreateCommand dataCompanyIprTrademarkTransferCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkTransferUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferVO> update(DataCompanyIprTrademarkTransferUpdateCommand dataCompanyIprTrademarkTransferUpdateCommand);

	/**
	 * 企业知识产权商标转让信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferExWarehouseVO> warehouse(DataCompanyIprTrademarkTransferWarehouseCommand dataCompanyBasicWarehouseCommand);
}