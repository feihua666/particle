package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;

/**
 * <p>
 * 企业年报对外投资 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportForeignInvestRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignInvestVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignInvestVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportForeignInvestQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportForeignInvestVO> queryList(DataCompanyAnnualReportForeignInvestQueryListCommand dataCompanyAnnualReportForeignInvestQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportForeignInvestPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportForeignInvestVO> pageQuery(DataCompanyAnnualReportForeignInvestPageQueryCommand dataCompanyAnnualReportForeignInvestPageQueryCommand);


	/**
	 * 企业年报对外投资出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> exWarehouse(DataCompanyAnnualReportForeignInvestExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
