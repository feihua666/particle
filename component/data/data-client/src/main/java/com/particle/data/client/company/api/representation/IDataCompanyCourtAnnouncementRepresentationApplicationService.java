package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;

/**
 * <p>
 * 企业法院公告 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyCourtAnnouncementRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyCourtAnnouncementVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyCourtAnnouncementQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyCourtAnnouncementVO> queryList(DataCompanyCourtAnnouncementQueryListCommand dataCompanyCourtAnnouncementQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyCourtAnnouncementPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyCourtAnnouncementVO> pageQuery(DataCompanyCourtAnnouncementPageQueryCommand dataCompanyCourtAnnouncementPageQueryCommand);


	/**
	 * 企业法院公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> exWarehouse(DataCompanyCourtAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
