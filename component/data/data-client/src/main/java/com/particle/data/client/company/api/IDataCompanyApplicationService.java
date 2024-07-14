package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyUniqueExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVO;
/**
 * <p>
 * 企业 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
public interface IDataCompanyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVO> create(DataCompanyCreateCommand dataCompanyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVO> update(DataCompanyUpdateCommand dataCompanyUpdateCommand);

	/**
	 * 企业入库
	 * @param dataCompanyWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyUniqueExWarehouseVO> warehouse(DataCompanyWarehouseCommand dataCompanyWarehouseCommand);
}
