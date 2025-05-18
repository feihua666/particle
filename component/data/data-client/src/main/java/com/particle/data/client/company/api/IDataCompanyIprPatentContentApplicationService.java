package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
public interface IDataCompanyIprPatentContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentContentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentContentVO> create(DataCompanyIprPatentContentCreateCommand dataCompanyIprPatentContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentContentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentContentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentContentVO> update(DataCompanyIprPatentContentUpdateCommand dataCompanyIprPatentContentUpdateCommand);

	/**
	 * 企业知识产权专利内容入库
	 * @param dataCompanyIprPatentContentWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentContentExWarehouseVO> warehouse(DataCompanyIprPatentContentWarehouseCommand dataCompanyIprPatentContentWarehouseCommand);
}