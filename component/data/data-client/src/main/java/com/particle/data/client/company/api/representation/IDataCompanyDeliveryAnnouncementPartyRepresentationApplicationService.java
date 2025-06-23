package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;

/**
 * <p>
 * 企业送达公告当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyDeliveryAnnouncementPartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyDeliveryAnnouncementPartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyDeliveryAnnouncementPartyVO> queryList(DataCompanyDeliveryAnnouncementPartyQueryListCommand dataCompanyDeliveryAnnouncementPartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyDeliveryAnnouncementPartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyDeliveryAnnouncementPartyVO> pageQuery(DataCompanyDeliveryAnnouncementPartyPageQueryCommand dataCompanyDeliveryAnnouncementPartyPageQueryCommand);


	/**
	 * 企业送达公告当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouse(DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
