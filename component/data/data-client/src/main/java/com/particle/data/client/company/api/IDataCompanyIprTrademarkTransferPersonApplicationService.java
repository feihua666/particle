package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkTransferPersonUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferPersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标转让人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
public interface IDataCompanyIprTrademarkTransferPersonApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkTransferPersonCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferPersonVO> create(DataCompanyIprTrademarkTransferPersonCreateCommand dataCompanyIprTrademarkTransferPersonCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferPersonVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkTransferPersonUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferPersonVO> update(DataCompanyIprTrademarkTransferPersonUpdateCommand dataCompanyIprTrademarkTransferPersonUpdateCommand);

	/**
	 * 企业知识产权商标转让人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> warehouse(DataCompanyIprTrademarkTransferPersonWarehouseCommand dataCompanyBasicWarehouseCommand);
}