package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
/**
 * <p>
 * 企业送达公告内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
public interface IDataCompanyDeliveryAnnouncementContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyDeliveryAnnouncementContentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementContentVO> create(DataCompanyDeliveryAnnouncementContentCreateCommand dataCompanyDeliveryAnnouncementContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementContentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyDeliveryAnnouncementContentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementContentVO> update(DataCompanyDeliveryAnnouncementContentUpdateCommand dataCompanyDeliveryAnnouncementContentUpdateCommand);

	/**
	 * 企业送达公告内容入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand);
}