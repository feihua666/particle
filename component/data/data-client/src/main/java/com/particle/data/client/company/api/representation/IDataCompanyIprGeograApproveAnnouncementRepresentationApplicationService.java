package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;

/**
 * <p>
 * 企业知识产权地理标识核准公告 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprGeograApproveAnnouncementQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprGeograApproveAnnouncementVO> queryList(DataCompanyIprGeograApproveAnnouncementQueryListCommand dataCompanyIprGeograApproveAnnouncementQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprGeograApproveAnnouncementPageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprGeograApproveAnnouncementVO> pageQuery(DataCompanyIprGeograApproveAnnouncementPageQueryCommand dataCompanyIprGeograApproveAnnouncementPageQueryCommand);


	/**
	 * 企业知识产权地理标识核准公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> exWarehouse(DataCompanyIprGeograApproveAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
