package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkLicensePersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标许可人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkLicensePersonRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicensePersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkLicensePersonVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkLicensePersonQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkLicensePersonVO> queryList(DataCompanyIprTrademarkLicensePersonQueryListCommand dataCompanyIprTrademarkLicensePersonQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkLicensePersonPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkLicensePersonVO> pageQuery(DataCompanyIprTrademarkLicensePersonPageQueryCommand dataCompanyIprTrademarkLicensePersonPageQueryCommand);


	/**
	 * 企业知识产权商标许可人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkLicensePersonExWarehouseVO> exWarehouse(DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
