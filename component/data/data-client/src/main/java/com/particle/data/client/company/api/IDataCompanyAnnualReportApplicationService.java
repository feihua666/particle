package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
/**
 * <p>
 * 企业年报 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
public interface IDataCompanyAnnualReportApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportVO> create(DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportVO> update(DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand);

	/**
	 * 企业年报入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportExWarehouseVO> warehouse(DataCompanyAnnualReportWarehouseCommand dataCompanyBasicWarehouseCommand);
}