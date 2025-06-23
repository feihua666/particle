package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkPartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkPartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkPartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkPartyVO> queryList(DataCompanyIprTrademarkPartyQueryListCommand dataCompanyIprTrademarkPartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkPartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkPartyVO> pageQuery(DataCompanyIprTrademarkPartyPageQueryCommand dataCompanyIprTrademarkPartyPageQueryCommand);


	/**
	 * 企业知识产权商标当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPartyExWarehouseVO> exWarehouse(DataCompanyIprTrademarkPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
