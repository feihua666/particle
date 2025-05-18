package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利统计 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentStatisticRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentStatisticVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentStatisticVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentStatisticQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentStatisticVO> queryList(DataCompanyIprPatentStatisticQueryListCommand dataCompanyIprPatentStatisticQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentStatisticPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentStatisticVO> pageQuery(DataCompanyIprPatentStatisticPageQueryCommand dataCompanyIprPatentStatisticPageQueryCommand);


	/**
	 * 企业知识产权专利统计出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> exWarehouse(DataCompanyIprPatentStatisticExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}