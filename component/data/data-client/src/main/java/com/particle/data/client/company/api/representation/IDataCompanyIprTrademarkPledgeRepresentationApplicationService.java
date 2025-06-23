package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPledgeExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标质押信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkPledgeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPledgeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkPledgeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkPledgeVO> queryList(DataCompanyIprTrademarkPledgeQueryListCommand dataCompanyIprTrademarkPledgeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkPledgePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkPledgeVO> pageQuery(DataCompanyIprTrademarkPledgePageQueryCommand dataCompanyIprTrademarkPledgePageQueryCommand);


	/**
	 * 企业知识产权商标质押信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> exWarehouse(DataCompanyIprTrademarkPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
