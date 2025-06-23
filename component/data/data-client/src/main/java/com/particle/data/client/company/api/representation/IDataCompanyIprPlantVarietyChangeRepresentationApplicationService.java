package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyChangeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPlantVarietyChangeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyChangeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPlantVarietyChangeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPlantVarietyChangeVO> queryList(DataCompanyIprPlantVarietyChangeQueryListCommand dataCompanyIprPlantVarietyChangeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPlantVarietyChangePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPlantVarietyChangeVO> pageQuery(DataCompanyIprPlantVarietyChangePageQueryCommand dataCompanyIprPlantVarietyChangePageQueryCommand);


	/**
	 * 企业知识产权植物新品种变更信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> exWarehouse(DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
