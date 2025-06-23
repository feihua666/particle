package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograApproveAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
/**
 * <p>
 * 企业知识产权地理标识核准公告 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
public interface IDataCompanyIprGeograApproveAnnouncementApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprGeograApproveAnnouncementCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> create(DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprGeograApproveAnnouncementUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> update(DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand);

	/**
	 * 企业知识产权地理标识核准公告入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> warehouse(DataCompanyIprGeograApproveAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand);
}