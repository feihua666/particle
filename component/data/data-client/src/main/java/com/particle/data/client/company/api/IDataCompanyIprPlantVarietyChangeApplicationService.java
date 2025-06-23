package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
/**
 * <p>
 * 企业知识产权植物新品种变更信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
public interface IDataCompanyIprPlantVarietyChangeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPlantVarietyChangeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyChangeVO> create(DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyChangeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPlantVarietyChangeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyChangeVO> update(DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand);

	/**
	 * 企业知识产权植物新品种变更信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> warehouse(DataCompanyIprPlantVarietyChangeWarehouseCommand dataCompanyBasicWarehouseCommand);
}