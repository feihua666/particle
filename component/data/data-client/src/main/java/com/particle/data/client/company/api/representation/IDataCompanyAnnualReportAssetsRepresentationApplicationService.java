package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAssetsQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业资产状况信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportAssetsRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAssetsVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportAssetsVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportAssetsQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportAssetsVO> queryList(DataCompanyAnnualReportAssetsQueryListCommand dataCompanyAnnualReportAssetsQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportAssetsPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportAssetsVO> pageQuery(DataCompanyAnnualReportAssetsPageQueryCommand dataCompanyAnnualReportAssetsPageQueryCommand);


	/**
	 * 企业资产状况信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAssetsExWarehouseVO> exWarehouse(DataCompanyAnnualReportAssetsExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
