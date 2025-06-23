package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprWorkCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
/**
 * <p>
 * 企业知识产权作品著作 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
public interface IDataCompanyIprWorkCopyrightApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprWorkCopyrightCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprWorkCopyrightVO> create(DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprWorkCopyrightVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprWorkCopyrightUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprWorkCopyrightVO> update(DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand);

	/**
	 * 企业知识产权作品著作入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> warehouse(DataCompanyIprWorkCopyrightWarehouseCommand dataCompanyBasicWarehouseCommand);
}