package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentQuoteWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利引证信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
public interface IDataCompanyIprPatentQuoteApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentQuoteCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentQuoteVO> create(DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentQuoteVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentQuoteUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentQuoteVO> update(DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand);

	/**
	 * 企业知识产权专利引证信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> warehouse(DataCompanyIprPatentQuoteWarehouseCommand dataCompanyBasicWarehouseCommand);
}