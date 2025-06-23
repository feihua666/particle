package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
/**
 * <p>
 * 企业送达公告 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
public interface IDataCompanyDeliveryAnnouncementApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyDeliveryAnnouncementCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementVO> create(DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyDeliveryAnnouncementUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementVO> update(DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand);

	/**
	 * 企业送达公告入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand);
}