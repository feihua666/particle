package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;

/**
 * <p>
 * 企业统计 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyStatisticRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyStatisticVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyStatisticVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyStatisticQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyStatisticVO> queryList(DataCompanyStatisticQueryListCommand dataCompanyStatisticQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyStatisticPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyStatisticVO> pageQuery(DataCompanyStatisticPageQueryCommand dataCompanyStatisticPageQueryCommand);


	/**
	 * 企业统计出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticExWarehouseVO> exWarehouse(DataCompanyStatisticExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}