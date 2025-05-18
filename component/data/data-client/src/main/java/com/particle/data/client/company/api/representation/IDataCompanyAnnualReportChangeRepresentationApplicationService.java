package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportChangeQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业年报变更 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportChangeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportChangeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportChangeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportChangeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportChangeVO> queryList(DataCompanyAnnualReportChangeQueryListCommand dataCompanyAnnualReportChangeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportChangePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportChangeVO> pageQuery(DataCompanyAnnualReportChangePageQueryCommand dataCompanyAnnualReportChangePageQueryCommand);


	/**
	 * 企业年报变更出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportChangeExWarehouseVO> exWarehouse(DataCompanyAnnualReportChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
