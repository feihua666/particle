package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;

/**
 * <p>
 * 企业送达公告 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyDeliveryAnnouncementRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyDeliveryAnnouncementQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyDeliveryAnnouncementVO> queryList(DataCompanyDeliveryAnnouncementQueryListCommand dataCompanyDeliveryAnnouncementQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyDeliveryAnnouncementPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyDeliveryAnnouncementVO> pageQuery(DataCompanyDeliveryAnnouncementPageQueryCommand dataCompanyDeliveryAnnouncementPageQueryCommand);


	/**
	 * 企业送达公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> exWarehouse(DataCompanyDeliveryAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
