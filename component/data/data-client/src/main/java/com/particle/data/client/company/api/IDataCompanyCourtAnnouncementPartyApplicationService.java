package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
/**
 * <p>
 * 企业法院公告当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
public interface IDataCompanyCourtAnnouncementPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyCourtAnnouncementPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementPartyVO> create(DataCompanyCourtAnnouncementPartyCreateCommand dataCompanyCourtAnnouncementPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyCourtAnnouncementPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementPartyVO> update(DataCompanyCourtAnnouncementPartyUpdateCommand dataCompanyCourtAnnouncementPartyUpdateCommand);

	/**
	 * 企业法院公告当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> warehouse(DataCompanyCourtAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}