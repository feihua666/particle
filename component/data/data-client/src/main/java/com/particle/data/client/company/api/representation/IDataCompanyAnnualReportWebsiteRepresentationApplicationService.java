package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsitePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportWebsiteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;

/**
 * <p>
 * 企业年报网站网店 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportWebsiteRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportWebsiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportWebsiteVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportWebsiteQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportWebsiteVO> queryList(DataCompanyAnnualReportWebsiteQueryListCommand dataCompanyAnnualReportWebsiteQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportWebsitePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportWebsiteVO> pageQuery(DataCompanyAnnualReportWebsitePageQueryCommand dataCompanyAnnualReportWebsitePageQueryCommand);


	/**
	 * 企业年报网站网店出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> exWarehouse(DataCompanyAnnualReportWebsiteExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
