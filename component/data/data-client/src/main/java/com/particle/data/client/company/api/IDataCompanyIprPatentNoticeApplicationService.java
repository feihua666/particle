package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentNoticeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
/**
 * <p>
 * 企业知识产权专利通知书信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
public interface IDataCompanyIprPatentNoticeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyIprPatentNoticeCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentNoticeVO> create(DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentNoticeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyIprPatentNoticeUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentNoticeVO> update(DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand);

	/**
	 * 企业知识产权专利通知书信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyIprPatentNoticeExWarehouseVO> warehouse(DataCompanyIprPatentNoticeWarehouseCommand dataCompanyBasicWarehouseCommand);
}