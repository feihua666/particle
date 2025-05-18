package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportSocialSecurityWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 企业年报社保 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
public interface IDataCompanyAnnualReportSocialSecurityApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAnnualReportSocialSecurityCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportSocialSecurityVO> create(DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportSocialSecurityVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAnnualReportSocialSecurityUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportSocialSecurityVO> update(DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand);

	/**
	 * 企业年报社保入库
	 * @param dataCompanyAnnualReportSocialSecurityWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> warehouse(DataCompanyAnnualReportSocialSecurityWarehouseCommand dataCompanyAnnualReportSocialSecurityWarehouseCommand);

}
