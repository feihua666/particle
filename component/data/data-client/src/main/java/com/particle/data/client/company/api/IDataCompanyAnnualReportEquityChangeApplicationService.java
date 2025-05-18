package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
/**
 * <p>
 * 企业年报股权变更 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
public interface IDataCompanyAnnualReportEquityChangeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportEquityChangeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportEquityChangeVO> create(DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportEquityChangeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportEquityChangeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportEquityChangeVO> update(DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand);

	/**
	 * 企业年报股权变更入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> warehouse(DataCompanyAnnualReportEquityChangeWarehouseCommand dataCompanyBasicWarehouseCommand);
}