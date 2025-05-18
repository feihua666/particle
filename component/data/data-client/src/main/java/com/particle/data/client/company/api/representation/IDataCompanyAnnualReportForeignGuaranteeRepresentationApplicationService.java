package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignGuaranteeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;

/**
 * <p>
 * 企业年报对外担保 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportForeignGuaranteeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportForeignGuaranteeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportForeignGuaranteeVO> queryList(DataCompanyAnnualReportForeignGuaranteeQueryListCommand dataCompanyAnnualReportForeignGuaranteeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportForeignGuaranteePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportForeignGuaranteeVO> pageQuery(DataCompanyAnnualReportForeignGuaranteePageQueryCommand dataCompanyAnnualReportForeignGuaranteePageQueryCommand);


	/**
	 * 企业年报对外担保出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> exWarehouse(DataCompanyAnnualReportForeignGuaranteeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
