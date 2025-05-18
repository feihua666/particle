package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportShareholderQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业年报股东 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportShareholderRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportShareholderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportShareholderVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportShareholderQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportShareholderVO> queryList(DataCompanyAnnualReportShareholderQueryListCommand dataCompanyAnnualReportShareholderQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportShareholderPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportShareholderVO> pageQuery(DataCompanyAnnualReportShareholderPageQueryCommand dataCompanyAnnualReportShareholderPageQueryCommand);


	/**
	 * 企业年报股东出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportShareholderExWarehouseVO> exWarehouse(DataCompanyAnnualReportShareholderExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);

}
