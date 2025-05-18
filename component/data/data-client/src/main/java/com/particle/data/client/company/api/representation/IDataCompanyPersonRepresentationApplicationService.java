package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;

/**
 * <p>
 * 企业个人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyPersonRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyPersonVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyPersonQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyPersonVO> queryList(DataCompanyPersonQueryListCommand dataCompanyPersonQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyPersonPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyPersonVO> pageQuery(DataCompanyPersonPageQueryCommand dataCompanyPersonPageQueryCommand);


	/**
	 * 企业个人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonExWarehouseVO> exWarehouse(DataCompanyPersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}