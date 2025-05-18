package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentFamilyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利同族信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
public interface IDataCompanyIprPatentFamilyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentFamilyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentFamilyVO> create(DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentFamilyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentFamilyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentFamilyVO> update(DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand);

	/**
	 * 企业知识产权专利同族信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> warehouse(DataCompanyIprPatentFamilyWarehouseCommand dataCompanyBasicWarehouseCommand);
}