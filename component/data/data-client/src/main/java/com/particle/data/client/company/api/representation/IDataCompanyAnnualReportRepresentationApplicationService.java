package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业年报 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportVO> queryList(DataCompanyAnnualReportQueryListCommand dataCompanyAnnualReportQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportVO> pageQuery(DataCompanyAnnualReportPageQueryCommand dataCompanyAnnualReportPageQueryCommand);


	/**
	 * 企业年报出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportExWarehouseVO> exWarehouse(DataCompanyAnnualReportExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
