package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentCitedExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利被引证信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentCitedRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCitedVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentCitedVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentCitedQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentCitedVO> queryList(DataCompanyIprPatentCitedQueryListCommand dataCompanyIprPatentCitedQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentCitedPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentCitedVO> pageQuery(DataCompanyIprPatentCitedPageQueryCommand dataCompanyIprPatentCitedPageQueryCommand);


	/**
	 * 企业知识产权专利被引证信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouse(DataCompanyIprPatentCitedExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
