package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;

/**
 * <p>
 * 企业知识产权地理标识 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprGeograRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprGeograQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprGeograVO> queryList(DataCompanyIprGeograQueryListCommand dataCompanyIprGeograQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprGeograPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprGeograVO> pageQuery(DataCompanyIprGeograPageQueryCommand dataCompanyIprGeograPageQueryCommand);


	/**
	 * 企业知识产权地理标识出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograExWarehouseVO> exWarehouse(DataCompanyIprGeograExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
