package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;

/**
 * <p>
 * 企业开庭公告当事人 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyOpenCourtAnnouncementPartyQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryList(DataCompanyOpenCourtAnnouncementPartyQueryListCommand dataCompanyOpenCourtAnnouncementPartyQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyOpenCourtAnnouncementPartyPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyOpenCourtAnnouncementPartyVO> pageQuery(DataCompanyOpenCourtAnnouncementPartyPageQueryCommand dataCompanyOpenCourtAnnouncementPartyPageQueryCommand);


	/**
	 * 企业开庭公告当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouse(DataCompanyOpenCourtAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
