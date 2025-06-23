package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
/**
 * <p>
 * 企业知识产权出质 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
public interface IDataCompanyIprPledgeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPledgeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPledgeVO> create(DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPledgeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPledgeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPledgeVO> update(DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand);

	/**
	 * 企业知识产权出质入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPledgeExWarehouseVO> warehouse(DataCompanyIprPledgeWarehouseCommand dataCompanyBasicWarehouseCommand);
}