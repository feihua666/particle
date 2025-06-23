package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;

/**
 * <p>
 * 企业行政许可 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAdministrativeLicenseRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAdministrativeLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAdministrativeLicenseVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAdministrativeLicenseQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAdministrativeLicenseVO> queryList(DataCompanyAdministrativeLicenseQueryListCommand dataCompanyAdministrativeLicenseQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAdministrativeLicensePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAdministrativeLicenseVO> pageQuery(DataCompanyAdministrativeLicensePageQueryCommand dataCompanyAdministrativeLicensePageQueryCommand);


	/**
	 * 企业行政许可出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> exWarehouse(DataCompanyAdministrativeLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
