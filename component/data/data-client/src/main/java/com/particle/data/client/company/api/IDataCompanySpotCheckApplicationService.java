package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySpotCheckWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
/**
 * <p>
 * 企业抽查检查 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
public interface IDataCompanySpotCheckApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanySpotCheckCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanySpotCheckVO> create(DataCompanySpotCheckCreateCommand dataCompanySpotCheckCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanySpotCheckVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanySpotCheckUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanySpotCheckVO> update(DataCompanySpotCheckUpdateCommand dataCompanySpotCheckUpdateCommand);

	/**
	 * 企业抽查检查入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanySpotCheckExWarehouseVO> warehouse(DataCompanySpotCheckWarehouseCommand dataCompanyBasicWarehouseCommand);
}