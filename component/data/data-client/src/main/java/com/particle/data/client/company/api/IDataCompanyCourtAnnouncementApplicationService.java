package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
/**
 * <p>
 * 企业法院公告 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
public interface IDataCompanyCourtAnnouncementApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyCourtAnnouncementCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementVO> create(DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyCourtAnnouncementUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementVO> update(DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand);

	/**
	 * 企业法院公告入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> warehouse(DataCompanyCourtAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand);
}