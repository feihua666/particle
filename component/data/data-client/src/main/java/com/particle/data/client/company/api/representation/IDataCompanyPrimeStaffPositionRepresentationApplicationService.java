package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPrimeStaffPositionQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;

/**
 * <p>
 * 企业主要人员职位 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyPrimeStaffPositionRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffPositionVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyPrimeStaffPositionVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyPrimeStaffPositionQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyPrimeStaffPositionVO> queryList(DataCompanyPrimeStaffPositionQueryListCommand dataCompanyPrimeStaffPositionQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyPrimeStaffPositionPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyPrimeStaffPositionVO> pageQuery(DataCompanyPrimeStaffPositionPageQueryCommand dataCompanyPrimeStaffPositionPageQueryCommand);


	/**
	 * 企业主要人员职位出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPrimeStaffPositionExWarehouseVO> exWarehouse(DataCompanyPrimeStaffPositionExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
