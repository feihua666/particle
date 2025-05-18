package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWebsiteWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
/**
 * <p>
 * 企业年报网站网店 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
public interface IDataCompanyAnnualReportWebsiteApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportWebsiteCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportWebsiteVO> create(DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportWebsiteVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportWebsiteUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportWebsiteVO> update(DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand);

	/**
	 * 企业年报网站网店入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> warehouse(DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyBasicWarehouseCommand);
}