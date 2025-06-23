package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;

/**
 * <p>
 * 企业知识产权商标转让信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprTrademarkTransferRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprTrademarkTransferVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprTrademarkTransferQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprTrademarkTransferVO> queryList(DataCompanyIprTrademarkTransferQueryListCommand dataCompanyIprTrademarkTransferQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprTrademarkTransferPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprTrademarkTransferVO> pageQuery(DataCompanyIprTrademarkTransferPageQueryCommand dataCompanyIprTrademarkTransferPageQueryCommand);


	/**
	 * 企业知识产权商标转让信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferExWarehouseVO> exWarehouse(DataCompanyIprTrademarkTransferExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
