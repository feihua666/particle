package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEquityPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
/**
 * <p>
 * 企业股权出质 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
public interface IDataCompanyEquityPledgeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyEquityPledgeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyEquityPledgeVO> create(DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyEquityPledgeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyEquityPledgeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyEquityPledgeVO> update(DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand);

	/**
	 * 企业股权出质入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyEquityPledgeExWarehouseVO> warehouse(DataCompanyEquityPledgeWarehouseCommand dataCompanyBasicWarehouseCommand);
}