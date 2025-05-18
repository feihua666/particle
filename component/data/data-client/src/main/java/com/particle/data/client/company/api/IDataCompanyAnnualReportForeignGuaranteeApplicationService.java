package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
/**
 * <p>
 * 企业年报对外担保 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
public interface IDataCompanyAnnualReportForeignGuaranteeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportForeignGuaranteeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> create(DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportForeignGuaranteeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> update(DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand);

	/**
	 * 企业年报对外担保入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> warehouse(DataCompanyAnnualReportForeignGuaranteeWarehouseCommand dataCompanyBasicWarehouseCommand);
}