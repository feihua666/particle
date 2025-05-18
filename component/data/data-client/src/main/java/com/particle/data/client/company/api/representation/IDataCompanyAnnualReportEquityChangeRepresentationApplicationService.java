package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportEquityChangeQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业年报股权变更 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportEquityChangeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportEquityChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportEquityChangeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportEquityChangeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportEquityChangeVO> queryList(DataCompanyAnnualReportEquityChangeQueryListCommand dataCompanyAnnualReportEquityChangeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportEquityChangePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportEquityChangeVO> pageQuery(DataCompanyAnnualReportEquityChangePageQueryCommand dataCompanyAnnualReportEquityChangePageQueryCommand);


	/**
	 * 企业年报股权变更出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> exWarehouse(DataCompanyAnnualReportEquityChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
