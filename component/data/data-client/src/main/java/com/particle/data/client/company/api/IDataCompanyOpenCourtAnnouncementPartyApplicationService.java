package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
/**
 * <p>
 * 企业开庭公告当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
public interface IDataCompanyOpenCourtAnnouncementPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyOpenCourtAnnouncementPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> create(DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyOpenCourtAnnouncementPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> update(DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand);

	/**
	 * 企业开庭公告当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}