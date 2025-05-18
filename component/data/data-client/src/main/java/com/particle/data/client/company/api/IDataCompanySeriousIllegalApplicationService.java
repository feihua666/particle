package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
/**
 * <p>
 * 企业严重违法 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
public interface IDataCompanySeriousIllegalApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanySeriousIllegalCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanySeriousIllegalVO> create(DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanySeriousIllegalVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanySeriousIllegalUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanySeriousIllegalVO> update(DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand);

	/**
	 * 企业严重违法入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanySeriousIllegalExWarehouseVO> warehouse(DataCompanySeriousIllegalWarehouseCommand dataCompanyBasicWarehouseCommand);
}