package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcFinancingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;

/**
 * <p>
 * 企业融资 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyVcFinancingRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyVcFinancingVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyVcFinancingQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyVcFinancingVO> queryList(DataCompanyVcFinancingQueryListCommand dataCompanyVcFinancingQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyVcFinancingPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyVcFinancingVO> pageQuery(DataCompanyVcFinancingPageQueryCommand dataCompanyVcFinancingPageQueryCommand);


	/**
	 * 企业融资出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcFinancingExWarehouseVO> exWarehouse(DataCompanyVcFinancingExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
