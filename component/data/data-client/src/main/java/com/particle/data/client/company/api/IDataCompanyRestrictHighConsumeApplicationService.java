package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
/**
 * <p>
 * 企业限制高消费 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
public interface IDataCompanyRestrictHighConsumeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyRestrictHighConsumeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumeVO> create(DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyRestrictHighConsumeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumeVO> update(DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand);

	/**
	 * 企业限制高消费入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> warehouse(DataCompanyRestrictHighConsumeWarehouseCommand dataCompanyBasicWarehouseCommand);
}