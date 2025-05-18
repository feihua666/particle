package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
/**
 * <p>
 * 企业法院公告内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
public interface IDataCompanyCourtAnnouncementContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyCourtAnnouncementContentCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementContentVO> create(DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementContentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyCourtAnnouncementContentUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementContentVO> update(DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand);

	/**
	 * 企业法院公告内容入库
	 * @param dataCompanyCourtAnnouncementContentWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> warehouse(DataCompanyCourtAnnouncementContentWarehouseCommand dataCompanyCourtAnnouncementContentWarehouseCommand);
}