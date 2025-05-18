package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentVO> queryList(DataCompanyIprPatentQueryListCommand dataCompanyIprPatentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentVO> pageQuery(DataCompanyIprPatentPageQueryCommand dataCompanyIprPatentPageQueryCommand);


	/**
	 * 企业知识产权专利出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentExWarehouseVO> exWarehouse(DataCompanyIprPatentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
