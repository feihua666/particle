package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentFamilyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利同族信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentFamilyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentFamilyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentFamilyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentFamilyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentFamilyVO> queryList(DataCompanyIprPatentFamilyQueryListCommand dataCompanyIprPatentFamilyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentFamilyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentFamilyVO> pageQuery(DataCompanyIprPatentFamilyPageQueryCommand dataCompanyIprPatentFamilyPageQueryCommand);


	/**
	 * 企业知识产权专利同族信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouse(DataCompanyIprPatentFamilyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
