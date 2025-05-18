package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
/**
 * <p>
 * 企业年报对外投资 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
public interface IDataCompanyAnnualReportForeignInvestApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportForeignInvestCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignInvestVO> create(DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignInvestVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportForeignInvestUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignInvestVO> update(DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand);

	/**
	 * 企业年报对外投资入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> warehouse(DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyBasicWarehouseCommand);
}