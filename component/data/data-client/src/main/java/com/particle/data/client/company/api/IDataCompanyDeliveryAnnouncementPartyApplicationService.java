package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
/**
 * <p>
 * 企业送达公告当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
public interface IDataCompanyDeliveryAnnouncementPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyDeliveryAnnouncementPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> create(DataCompanyDeliveryAnnouncementPartyCreateCommand dataCompanyDeliveryAnnouncementPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyDeliveryAnnouncementPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> update(DataCompanyDeliveryAnnouncementPartyUpdateCommand dataCompanyDeliveryAnnouncementPartyUpdateCommand);

	/**
	 * 企业送达公告当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}