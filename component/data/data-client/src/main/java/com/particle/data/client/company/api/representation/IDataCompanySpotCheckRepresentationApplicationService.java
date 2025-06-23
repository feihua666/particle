package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySpotCheckExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;

/**
 * <p>
 * 企业抽查检查 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanySpotCheckRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanySpotCheckVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanySpotCheckVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanySpotCheckQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanySpotCheckVO> queryList(DataCompanySpotCheckQueryListCommand dataCompanySpotCheckQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanySpotCheckPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanySpotCheckVO> pageQuery(DataCompanySpotCheckPageQueryCommand dataCompanySpotCheckPageQueryCommand);


	/**
	 * 企业抽查检查出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanySpotCheckExWarehouseVO> exWarehouse(DataCompanySpotCheckExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
