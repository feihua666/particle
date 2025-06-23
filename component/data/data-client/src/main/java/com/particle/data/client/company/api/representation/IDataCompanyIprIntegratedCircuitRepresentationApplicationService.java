package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprIntegratedCircuitQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;

/**
 * <p>
 * 企业知识产权集成电路 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprIntegratedCircuitRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprIntegratedCircuitVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprIntegratedCircuitVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprIntegratedCircuitQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprIntegratedCircuitVO> queryList(DataCompanyIprIntegratedCircuitQueryListCommand dataCompanyIprIntegratedCircuitQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprIntegratedCircuitPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprIntegratedCircuitVO> pageQuery(DataCompanyIprIntegratedCircuitPageQueryCommand dataCompanyIprIntegratedCircuitPageQueryCommand);


	/**
	 * 企业知识产权集成电路出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouse(DataCompanyIprIntegratedCircuitExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
