package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPunishmentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;

/**
 * <p>
 * 企业行政处罚 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyPunishmentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPunishmentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyPunishmentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyPunishmentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyPunishmentVO> queryList(DataCompanyPunishmentQueryListCommand dataCompanyPunishmentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyPunishmentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyPunishmentVO> pageQuery(DataCompanyPunishmentPageQueryCommand dataCompanyPunishmentPageQueryCommand);


	/**
	 * 企业行政处罚出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPunishmentExWarehouseVO> exWarehouse(DataCompanyPunishmentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
