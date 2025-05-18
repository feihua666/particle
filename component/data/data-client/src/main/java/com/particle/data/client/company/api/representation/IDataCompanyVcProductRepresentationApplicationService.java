package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;

/**
 * <p>
 * 企业融资产品 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyVcProductRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcProductVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyVcProductQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyVcProductVO> queryList(DataCompanyVcProductQueryListCommand dataCompanyVcProductQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyVcProductPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyVcProductVO> pageQuery(DataCompanyVcProductPageQueryCommand dataCompanyVcProductPageQueryCommand);


	/**
	 * 企业融资产品出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcProductExWarehouseVO> exWarehouse(DataCompanyVcProductExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
