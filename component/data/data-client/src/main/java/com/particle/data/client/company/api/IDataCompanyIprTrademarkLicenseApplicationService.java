package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
/**
 * <p>
 * 企业知识产权商标许可信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
public interface IDataCompanyIprTrademarkLicenseApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprTrademarkLicenseCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicenseVO> create(DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicenseVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprTrademarkLicenseUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicenseVO> update(DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand);

	/**
	 * 企业知识产权商标许可信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> warehouse(DataCompanyIprTrademarkLicenseWarehouseCommand dataCompanyBasicWarehouseCommand);
}