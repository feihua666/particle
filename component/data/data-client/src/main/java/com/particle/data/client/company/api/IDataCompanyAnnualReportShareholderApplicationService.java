package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
/**
 * <p>
 * 企业年报股东 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
public interface IDataCompanyAnnualReportShareholderApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportShareholderCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportShareholderVO> create(DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportShareholderVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportShareholderUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportShareholderVO> update(DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand);

	/**
	 * 企业年报股东入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> warehouse(DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyBasicWarehouseCommand);
}