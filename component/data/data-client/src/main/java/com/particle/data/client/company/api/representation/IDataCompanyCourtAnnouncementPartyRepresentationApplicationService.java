package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;

/**
 * <p>
 * 企业法院公告当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyCourtAnnouncementPartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementPartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyCourtAnnouncementPartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyCourtAnnouncementPartyVO> queryList(DataCompanyCourtAnnouncementPartyQueryListCommand dataCompanyCourtAnnouncementPartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyCourtAnnouncementPartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyCourtAnnouncementPartyVO> pageQuery(DataCompanyCourtAnnouncementPartyPageQueryCommand dataCompanyCourtAnnouncementPartyPageQueryCommand);


	/**
	 * 企业法院公告当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouse(DataCompanyCourtAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
