package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportSocialSecurityQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业年报社保 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAnnualReportSocialSecurityRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportSocialSecurityVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportSocialSecurityVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAnnualReportSocialSecurityQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAnnualReportSocialSecurityVO> queryList(DataCompanyAnnualReportSocialSecurityQueryListCommand dataCompanyAnnualReportSocialSecurityQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAnnualReportSocialSecurityPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAnnualReportSocialSecurityVO> pageQuery(DataCompanyAnnualReportSocialSecurityPageQueryCommand dataCompanyAnnualReportSocialSecurityPageQueryCommand);


	/**
	 * 企业年报社保出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> exWarehouse(DataCompanyAnnualReportSocialSecurityExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);

}
