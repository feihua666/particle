package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
/**
 * <p>
 * 企业年报变更 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
public interface IDataCompanyAnnualReportChangeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportChangeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportChangeVO> create(DataCompanyAnnualReportChangeCreateCommand dataCompanyAnnualReportChangeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportChangeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportChangeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportChangeVO> update(DataCompanyAnnualReportChangeUpdateCommand dataCompanyAnnualReportChangeUpdateCommand);

	/**
	 * 企业年报变更入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> warehouse(DataCompanyAnnualReportChangeWarehouseCommand dataCompanyBasicWarehouseCommand);
}