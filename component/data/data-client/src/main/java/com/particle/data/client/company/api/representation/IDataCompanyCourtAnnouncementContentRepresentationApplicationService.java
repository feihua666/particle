package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;

/**
 * <p>
 * 企业法院公告内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyCourtAnnouncementContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementContentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyCourtAnnouncementContentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyCourtAnnouncementContentVO> queryList(DataCompanyCourtAnnouncementContentQueryListCommand dataCompanyCourtAnnouncementContentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyCourtAnnouncementContentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyCourtAnnouncementContentVO> pageQuery(DataCompanyCourtAnnouncementContentPageQueryCommand dataCompanyCourtAnnouncementContentPageQueryCommand);


	/**
	 * 企业法院公告内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> exWarehouse(DataCompanyCourtAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}