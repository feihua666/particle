package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
/**
 * <p>
 * 企业年报行政许可 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
public interface IDataCompanyAnnualReportAdministrativeLicenseApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportAdministrativeLicenseCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> create(DataCompanyAnnualReportAdministrativeLicenseCreateCommand dataCompanyAnnualReportAdministrativeLicenseCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportAdministrativeLicenseUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> update(DataCompanyAnnualReportAdministrativeLicenseUpdateCommand dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);

	/**
	 * 企业年报行政许可入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> warehouse(DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand dataCompanyBasicWarehouseCommand);
}