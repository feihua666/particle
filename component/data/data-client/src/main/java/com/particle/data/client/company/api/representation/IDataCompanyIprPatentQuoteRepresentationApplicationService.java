package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuotePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuoteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentQuoteExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利引证信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentQuoteRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentQuoteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentQuoteVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentQuoteQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentQuoteVO> queryList(DataCompanyIprPatentQuoteQueryListCommand dataCompanyIprPatentQuoteQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentQuotePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentQuoteVO> pageQuery(DataCompanyIprPatentQuotePageQueryCommand dataCompanyIprPatentQuotePageQueryCommand);


	/**
	 * 企业知识产权专利引证信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouse(DataCompanyIprPatentQuoteExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
