package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
/**
 * <p>
 * 企业知识产权植物新品种 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
public interface IDataCompanyIprPlantVarietyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPlantVarietyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyVO> create(DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPlantVarietyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyVO> update(DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand);

	/**
	 * 企业知识产权植物新品种入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> warehouse(DataCompanyIprPlantVarietyWarehouseCommand dataCompanyBasicWarehouseCommand);
}