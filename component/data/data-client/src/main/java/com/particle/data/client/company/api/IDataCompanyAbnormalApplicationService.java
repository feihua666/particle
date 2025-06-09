package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAbnormalWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
/**
 * <p>
 * 企业经营异常 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
public interface IDataCompanyAbnormalApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAbnormalCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAbnormalVO> create(DataCompanyAbnormalCreateCommand dataCompanyAbnormalCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAbnormalVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAbnormalUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAbnormalVO> update(DataCompanyAbnormalUpdateCommand dataCompanyAbnormalUpdateCommand);

	/**
	 * 企业经营异常入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAbnormalExWarehouseVO> warehouse(DataCompanyAbnormalWarehouseCommand dataCompanyBasicWarehouseCommand);
}