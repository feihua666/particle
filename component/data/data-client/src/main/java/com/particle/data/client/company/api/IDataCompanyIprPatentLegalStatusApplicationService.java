package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLegalStatusWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利法律状态 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
public interface IDataCompanyIprPatentLegalStatusApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentLegalStatusCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLegalStatusVO> create(DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLegalStatusVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentLegalStatusUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLegalStatusVO> update(DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand);

	/**
	 * 企业知识产权专利法律状态入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> warehouse(DataCompanyIprPatentLegalStatusWarehouseCommand dataCompanyBasicWarehouseCommand);
}