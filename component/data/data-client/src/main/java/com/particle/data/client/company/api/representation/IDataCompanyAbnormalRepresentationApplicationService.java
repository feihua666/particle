package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAbnormalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;

/**
 * <p>
 * 企业经营异常 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyAbnormalRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyAbnormalVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyAbnormalVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyAbnormalQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyAbnormalVO> queryList(DataCompanyAbnormalQueryListCommand dataCompanyAbnormalQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyAbnormalPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyAbnormalVO> pageQuery(DataCompanyAbnormalPageQueryCommand dataCompanyAbnormalPageQueryCommand);


	/**
	 * 企业经营异常出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAbnormalExWarehouseVO> exWarehouse(DataCompanyAbnormalExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
