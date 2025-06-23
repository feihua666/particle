package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkVO> queryList(DataCompanyIprTrademarkQueryListCommand dataCompanyIprTrademarkQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkVO> pageQuery(DataCompanyIprTrademarkPageQueryCommand dataCompanyIprTrademarkPageQueryCommand);


	/**
	 * 企业知识产权商标出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkExWarehouseVO> exWarehouse(DataCompanyIprTrademarkExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
