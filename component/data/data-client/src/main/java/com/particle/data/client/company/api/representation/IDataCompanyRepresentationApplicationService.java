package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 企业 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyVO> queryList(DataCompanyQueryListCommand dataCompanyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyVO> pageQuery(DataCompanyPageQueryCommand dataCompanyPageQueryCommand);

	/**
	 * 出库
	 * @param exWarehouseQueryCommand
	 * @return
	 */
	SingleResponse<DataCompanyExWarehouseCandidateVO> exWarehouse(DataCompanyExWarehouseQueryCommand exWarehouseQueryCommand);

}
