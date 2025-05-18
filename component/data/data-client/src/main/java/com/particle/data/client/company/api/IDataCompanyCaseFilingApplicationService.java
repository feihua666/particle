package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
/**
 * <p>
 * 企业立案信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
public interface IDataCompanyCaseFilingApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyCaseFilingCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingVO> create(DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyCaseFilingUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingVO> update(DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand);

	/**
	 * 企业立案信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingExWarehouseVO> warehouse(DataCompanyCaseFilingWarehouseCommand dataCompanyBasicWarehouseCommand);
}