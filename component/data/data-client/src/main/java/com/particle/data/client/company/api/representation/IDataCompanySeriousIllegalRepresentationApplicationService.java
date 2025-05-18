package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySeriousIllegalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;

/**
 * <p>
 * 企业严重违法 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanySeriousIllegalRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanySeriousIllegalVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanySeriousIllegalVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanySeriousIllegalQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanySeriousIllegalVO> queryList(DataCompanySeriousIllegalQueryListCommand dataCompanySeriousIllegalQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanySeriousIllegalPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanySeriousIllegalVO> pageQuery(DataCompanySeriousIllegalPageQueryCommand dataCompanySeriousIllegalPageQueryCommand);


	/**
	 * 企业严重违法出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanySeriousIllegalExWarehouseVO> exWarehouse(DataCompanySeriousIllegalExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
