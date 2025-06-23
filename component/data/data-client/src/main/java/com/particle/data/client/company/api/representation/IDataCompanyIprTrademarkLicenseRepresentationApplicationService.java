package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标许可信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkLicenseRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicenseVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkLicenseQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkLicenseVO> queryList(DataCompanyIprTrademarkLicenseQueryListCommand dataCompanyIprTrademarkLicenseQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkLicensePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkLicenseVO> pageQuery(DataCompanyIprTrademarkLicensePageQueryCommand dataCompanyIprTrademarkLicensePageQueryCommand);


	/**
	 * 企业知识产权商标许可信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> exWarehouse(DataCompanyIprTrademarkLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
