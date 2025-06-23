package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;

/**
 * <p>
 * 企业主要人员 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyPrimeStaffRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyPrimeStaffQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyPrimeStaffVO> queryList(DataCompanyPrimeStaffQueryListCommand dataCompanyPrimeStaffQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyPrimeStaffPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyPrimeStaffVO> pageQuery(DataCompanyPrimeStaffPageQueryCommand dataCompanyPrimeStaffPageQueryCommand);


	/**
	 * 企业主要人员出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffExWarehouseVO> exWarehouse(DataCompanyPrimeStaffExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
