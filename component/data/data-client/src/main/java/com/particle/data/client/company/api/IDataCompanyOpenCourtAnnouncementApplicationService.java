package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
/**
 * <p>
 * 企业开庭公告 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
public interface IDataCompanyOpenCourtAnnouncementApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyOpenCourtAnnouncementCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementVO> create(DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyOpenCourtAnnouncementUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementVO> update(DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand);

	/**
	 * 企业开庭公告入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand);
}