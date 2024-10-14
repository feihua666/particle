package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;

/**
 * <p>
 * 开放平台应用月账单 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRecordAppMonthBillRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiRecordAppMonthBillQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiRecordAppMonthBillVO> queryList(OpenplatformOpenapiRecordAppMonthBillQueryListCommand openplatformOpenapiRecordAppMonthBillQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiRecordAppMonthBillPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiRecordAppMonthBillVO> pageQuery(OpenplatformOpenapiRecordAppMonthBillPageQueryCommand openplatformOpenapiRecordAppMonthBillPageQueryCommand);

}
