package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAdministrativeLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
/**
 * <p>
 * 企业行政许可 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
public interface IDataCompanyAdministrativeLicenseApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyAdministrativeLicenseCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAdministrativeLicenseVO> create(DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyAdministrativeLicenseVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyAdministrativeLicenseUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAdministrativeLicenseVO> update(DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand);

	/**
	 * 企业行政许可入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyAdministrativeLicenseExWarehouseVO> warehouse(DataCompanyAdministrativeLicenseWarehouseCommand dataCompanyBasicWarehouseCommand);
}