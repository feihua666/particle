package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
/**
 * <p>
 * 企业知识产权软件著作 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
public interface IDataCompanyIprSoftwareCopyrightApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprSoftwareCopyrightCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprSoftwareCopyrightVO> create(DataCompanyIprSoftwareCopyrightCreateCommand dataCompanyIprSoftwareCopyrightCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprSoftwareCopyrightVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprSoftwareCopyrightUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprSoftwareCopyrightVO> update(DataCompanyIprSoftwareCopyrightUpdateCommand dataCompanyIprSoftwareCopyrightUpdateCommand);

	/**
	 * 企业知识产权软件著作入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> warehouse(DataCompanyIprSoftwareCopyrightWarehouseCommand dataCompanyBasicWarehouseCommand);
}