package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标转让人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkTransferPersonRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferPersonVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferPersonVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkTransferPersonQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkTransferPersonVO> queryList(DataCompanyIprTrademarkTransferPersonQueryListCommand dataCompanyIprTrademarkTransferPersonQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkTransferPersonPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkTransferPersonVO> pageQuery(DataCompanyIprTrademarkTransferPersonPageQueryCommand dataCompanyIprTrademarkTransferPersonPageQueryCommand);


	/**
	 * 企业知识产权商标转让人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> exWarehouse(DataCompanyIprTrademarkTransferPersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
