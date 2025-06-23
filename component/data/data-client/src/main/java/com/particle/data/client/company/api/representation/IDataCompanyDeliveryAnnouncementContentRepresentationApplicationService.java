package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;

/**
 * <p>
 * 企业送达公告内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyDeliveryAnnouncementContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementContentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyDeliveryAnnouncementContentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyDeliveryAnnouncementContentVO> queryList(DataCompanyDeliveryAnnouncementContentQueryListCommand dataCompanyDeliveryAnnouncementContentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyDeliveryAnnouncementContentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyDeliveryAnnouncementContentVO> pageQuery(DataCompanyDeliveryAnnouncementContentPageQueryCommand dataCompanyDeliveryAnnouncementContentPageQueryCommand);


	/**
	 * 企业送达公告内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> exWarehouse(DataCompanyDeliveryAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}