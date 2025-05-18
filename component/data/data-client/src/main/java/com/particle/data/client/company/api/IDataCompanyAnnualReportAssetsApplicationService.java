package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAssetsWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
/**
 * <p>
 * 企业资产状况信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
public interface IDataCompanyAnnualReportAssetsApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportAssetsCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAssetsVO> create(DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAssetsVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportAssetsUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAssetsVO> update(DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand);

	/**
	 * 企业资产状况信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> warehouse(DataCompanyAnnualReportAssetsWarehouseCommand dataCompanyBasicWarehouseCommand);
}