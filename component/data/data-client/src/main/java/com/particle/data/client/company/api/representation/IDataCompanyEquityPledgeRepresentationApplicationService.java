package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;

/**
 * <p>
 * 企业股权出质 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyEquityPledgeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyEquityPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyEquityPledgeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyEquityPledgeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyEquityPledgeVO> queryList(DataCompanyEquityPledgeQueryListCommand dataCompanyEquityPledgeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyEquityPledgePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyEquityPledgeVO> pageQuery(DataCompanyEquityPledgePageQueryCommand dataCompanyEquityPledgePageQueryCommand);


	/**
	 * 企业股权出质出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyEquityPledgeExWarehouseVO> exWarehouse(DataCompanyEquityPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
