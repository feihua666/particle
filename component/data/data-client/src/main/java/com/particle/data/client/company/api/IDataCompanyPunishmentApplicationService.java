package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPunishmentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
/**
 * <p>
 * 企业行政处罚 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
public interface IDataCompanyPunishmentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyPunishmentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPunishmentVO> create(DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyPunishmentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyPunishmentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyPunishmentVO> update(DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand);

	/**
	 * 企业行政处罚入库
	 * @param dataCompanyPunishmentWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyPunishmentExWarehouseVO> warehouse(DataCompanyPunishmentWarehouseCommand dataCompanyPunishmentWarehouseCommand);
}