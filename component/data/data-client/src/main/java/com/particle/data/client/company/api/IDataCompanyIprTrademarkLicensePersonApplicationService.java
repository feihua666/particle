package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicensePersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标许可人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
public interface IDataCompanyIprTrademarkLicensePersonApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkLicensePersonCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicensePersonVO> create(DataCompanyIprTrademarkLicensePersonCreateCommand dataCompanyIprTrademarkLicensePersonCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicensePersonVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkLicensePersonUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicensePersonVO> update(DataCompanyIprTrademarkLicensePersonUpdateCommand dataCompanyIprTrademarkLicensePersonUpdateCommand);

	/**
	 * 企业知识产权商标许可人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> warehouse(DataCompanyIprTrademarkLicensePersonWarehouseCommand dataCompanyBasicWarehouseCommand);
}