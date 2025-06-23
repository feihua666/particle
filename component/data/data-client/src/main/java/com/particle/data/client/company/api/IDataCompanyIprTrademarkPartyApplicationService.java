package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
public interface IDataCompanyIprTrademarkPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPartyVO> create(DataCompanyIprTrademarkPartyCreateCommand dataCompanyIprTrademarkPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPartyVO> update(DataCompanyIprTrademarkPartyUpdateCommand dataCompanyIprTrademarkPartyUpdateCommand);

	/**
	 * 企业知识产权商标当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPartyExWarehouseVO> warehouse(DataCompanyIprTrademarkPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}