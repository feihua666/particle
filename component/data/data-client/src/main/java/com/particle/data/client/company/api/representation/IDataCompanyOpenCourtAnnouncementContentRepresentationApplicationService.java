package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;

/**
 * <p>
 * 企业开庭公告内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyOpenCourtAnnouncementContentQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyOpenCourtAnnouncementContentVO> queryList(DataCompanyOpenCourtAnnouncementContentQueryListCommand dataCompanyOpenCourtAnnouncementContentQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyOpenCourtAnnouncementContentPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyOpenCourtAnnouncementContentVO> pageQuery(DataCompanyOpenCourtAnnouncementContentPageQueryCommand dataCompanyOpenCourtAnnouncementContentPageQueryCommand);


	/**
	 * 企业开庭公告内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> exWarehouse(DataCompanyOpenCourtAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}