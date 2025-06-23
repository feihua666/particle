package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;

/**
 * <p>
 * 企业知识产权出质 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPledgeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPledgeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPledgeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPledgeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPledgeVO> queryList(DataCompanyIprPledgeQueryListCommand dataCompanyIprPledgeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPledgePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPledgeVO> pageQuery(DataCompanyIprPledgePageQueryCommand dataCompanyIprPledgePageQueryCommand);


	/**
	 * 企业知识产权出质出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPledgeExWarehouseVO> exWarehouse(DataCompanyIprPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
