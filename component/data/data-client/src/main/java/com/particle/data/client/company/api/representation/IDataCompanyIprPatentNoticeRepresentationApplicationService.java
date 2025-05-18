package com.particle.data.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentNoticeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;

/**
 * <p>
 * 企业知识产权专利通知书信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataCompanyIprPatentNoticeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentNoticeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentNoticeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataCompanyIprPatentNoticeQueryListCommand
	 * @return
	 */
	MultiResponse<DataCompanyIprPatentNoticeVO> queryList(DataCompanyIprPatentNoticeQueryListCommand dataCompanyIprPatentNoticeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataCompanyIprPatentNoticePageQueryCommand
	 * @return
	 */
	PageResponse<DataCompanyIprPatentNoticeVO> pageQuery(DataCompanyIprPatentNoticePageQueryCommand dataCompanyIprPatentNoticePageQueryCommand);


	/**
	 * 企业知识产权专利通知书信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentNoticeExWarehouseVO> exWarehouse(DataCompanyIprPatentNoticeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand);
}
