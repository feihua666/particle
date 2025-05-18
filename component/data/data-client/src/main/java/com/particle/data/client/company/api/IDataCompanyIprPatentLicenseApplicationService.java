package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利许可信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
public interface IDataCompanyIprPatentLicenseApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentLicenseCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLicenseVO> create(DataCompanyIprPatentLicenseCreateCommand dataCompanyIprPatentLicenseCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLicenseVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentLicenseUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLicenseVO> update(DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand);

	/**
	 * 企业知识产权专利许可信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLicenseExWarehouseVO> warehouse(DataCompanyIprPatentLicenseWarehouseCommand dataCompanyBasicWarehouseCommand);
}