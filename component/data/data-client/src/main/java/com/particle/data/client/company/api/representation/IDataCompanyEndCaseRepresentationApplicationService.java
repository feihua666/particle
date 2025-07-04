package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCasePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyEndCaseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEndCaseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;

/**
 * <p>
 * 企业终本案件 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyEndCaseRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyEndCaseVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyEndCaseVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyEndCaseQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyEndCaseVO> queryList(DataCompanyEndCaseQueryListCommand dataCompanyEndCaseQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyEndCasePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyEndCaseVO> pageQuery(DataCompanyEndCasePageQueryCommand dataCompanyEndCasePageQueryCommand);


	/**
	 * 企业终本案件出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyEndCaseExWarehouseVO> exWarehouse(DataCompanyEndCaseExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
