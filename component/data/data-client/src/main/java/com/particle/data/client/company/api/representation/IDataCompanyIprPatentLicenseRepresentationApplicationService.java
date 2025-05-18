package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicensePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利许可信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentLicenseRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLicenseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentLicenseVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentLicenseQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentLicenseVO> queryList(DataCompanyIprPatentLicenseQueryListCommand dataCompanyIprPatentLicenseQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentLicensePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentLicenseVO> pageQuery(DataCompanyIprPatentLicensePageQueryCommand dataCompanyIprPatentLicensePageQueryCommand);


	/**
	 * 企业知识产权专利许可信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLicenseExWarehouseVO> exWarehouse(DataCompanyIprPatentLicenseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
