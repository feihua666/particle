package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCaseFilingQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;

/**
 * <p>
 * 企业立案信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyCaseFilingRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyCaseFilingQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyCaseFilingVO> queryList(DataCompanyCaseFilingQueryListCommand dataCompanyCaseFilingQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyCaseFilingPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyCaseFilingVO> pageQuery(DataCompanyCaseFilingPageQueryCommand dataCompanyCaseFilingPageQueryCommand);


	/**
	 * 企业立案信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingExWarehouseVO> exWarehouse(DataCompanyCaseFilingExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
