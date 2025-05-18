package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
/**
 * <p>
 * 企业开庭公告内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
public interface IDataCompanyOpenCourtAnnouncementContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyOpenCourtAnnouncementContentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> create(DataCompanyOpenCourtAnnouncementContentCreateCommand dataCompanyOpenCourtAnnouncementContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyOpenCourtAnnouncementContentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> update(DataCompanyOpenCourtAnnouncementContentUpdateCommand dataCompanyOpenCourtAnnouncementContentUpdateCommand);

	/**
	 * 企业开庭公告内容入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand);
}