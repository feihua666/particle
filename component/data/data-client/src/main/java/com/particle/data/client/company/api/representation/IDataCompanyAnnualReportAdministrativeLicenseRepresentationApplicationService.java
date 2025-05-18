package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业年报行政许可 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportAdministrativeLicenseRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportAdministrativeLicenseQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportAdministrativeLicenseVO> queryList(DataCompanyAnnualReportAdministrativeLicenseQueryListCommand dataCompanyAnnualReportAdministrativeLicenseQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportAdministrativeLicensePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportAdministrativeLicenseVO> pageQuery(DataCompanyAnnualReportAdministrativeLicensePageQueryCommand dataCompanyAnnualReportAdministrativeLicensePageQueryCommand);


	/**
	 * 企业年报行政许可出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> exWarehouse(DataCompanyAnnualReportAdministrativeLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
