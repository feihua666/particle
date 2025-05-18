package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyBasicCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyBasicUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 企业基本信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
public interface IDataCompanyBasicApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyBasicCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyBasicVO> create(DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyBasicVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyBasicUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyBasicVO> update(DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand);

	/**
	 * 企业基本信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyBasicExWarehouseVO> warehouse(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand);
}
