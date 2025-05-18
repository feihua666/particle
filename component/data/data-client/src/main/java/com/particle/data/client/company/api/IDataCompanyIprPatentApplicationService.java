package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
public interface IDataCompanyIprPatentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentVO> create(DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentVO> update(DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand);

	/**
	 * 企业知识产权专利入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentExWarehouseVO> warehouse(DataCompanyIprPatentWarehouseCommand dataCompanyBasicWarehouseCommand);
}