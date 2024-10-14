package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
/**
 * <p>
 * 开放平台应用月账单 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
public interface IOpenplatformOpenapiRecordAppMonthBillApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiRecordAppMonthBillCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> create(OpenplatformOpenapiRecordAppMonthBillCreateCommand openplatformOpenapiRecordAppMonthBillCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiRecordAppMonthBillUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> update(OpenplatformOpenapiRecordAppMonthBillUpdateCommand openplatformOpenapiRecordAppMonthBillUpdateCommand);

	/**
	 * 统计某一月的数据
	 *
	 * @param year
	 * @param month
	 * @param isIncludeMonthSummary
	 * @param isIncludeDaySummary   只有在 isIncludeMonthSummary=true 时生效
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 */
	public Response statistic(Integer year, Integer month, Boolean isIncludeMonthSummary, Boolean isIncludeDaySummary,Long openplatformAppId);
}
