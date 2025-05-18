package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;

/**
 * <p>
 * 企业开庭公告 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyOpenCourtAnnouncementRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyOpenCourtAnnouncementQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyOpenCourtAnnouncementVO> queryList(DataCompanyOpenCourtAnnouncementQueryListCommand dataCompanyOpenCourtAnnouncementQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyOpenCourtAnnouncementPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyOpenCourtAnnouncementVO> pageQuery(DataCompanyOpenCourtAnnouncementPageQueryCommand dataCompanyOpenCourtAnnouncementPageQueryCommand);


	/**
	 * 企业开庭公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> exWarehouse(DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
