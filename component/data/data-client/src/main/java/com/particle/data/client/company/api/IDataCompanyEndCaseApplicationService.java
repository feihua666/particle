package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEndCaseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
/**
 * <p>
 * 企业终本案件 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
public interface IDataCompanyEndCaseApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyEndCaseCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyEndCaseVO> create(DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyEndCaseVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyEndCaseUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyEndCaseVO> update(DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand);

	/**
	 * 企业终本案件入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyEndCaseExWarehouseVO> warehouse(DataCompanyEndCaseWarehouseCommand dataCompanyBasicWarehouseCommand);
}