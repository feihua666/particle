package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标质押信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
public interface IDataCompanyIprTrademarkPledgeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkPledgeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPledgeVO> create(DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPledgeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkPledgeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPledgeVO> update(DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand);

	/**
	 * 企业知识产权商标质押信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> warehouse(DataCompanyIprTrademarkPledgeWarehouseCommand dataCompanyBasicWarehouseCommand);
}