package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPlantVarietyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;

/**
 * <p>
 * 企业知识产权植物新品种 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPlantVarietyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPlantVarietyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPlantVarietyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPlantVarietyVO> queryList(DataCompanyIprPlantVarietyQueryListCommand dataCompanyIprPlantVarietyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPlantVarietyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPlantVarietyVO> pageQuery(DataCompanyIprPlantVarietyPageQueryCommand dataCompanyIprPlantVarietyPageQueryCommand);


	/**
	 * 企业知识产权植物新品种出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouse(DataCompanyIprPlantVarietyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
