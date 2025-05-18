package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCitedWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利被引证信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
public interface IDataCompanyIprPatentCitedApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentCitedCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCitedVO> create(DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCitedVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentCitedUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCitedVO> update(DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand);

	/**
	 * 企业知识产权专利被引证信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> warehouse(DataCompanyIprPatentCitedWarehouseCommand dataCompanyBasicWarehouseCommand);
}