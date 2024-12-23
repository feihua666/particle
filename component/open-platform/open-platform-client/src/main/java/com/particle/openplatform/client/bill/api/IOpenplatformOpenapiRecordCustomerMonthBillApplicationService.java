package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
/**
 * <p>
 * 开放平台客户月账单 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
public interface IOpenplatformOpenapiRecordCustomerMonthBillApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiRecordCustomerMonthBillCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> create(OpenplatformOpenapiRecordCustomerMonthBillCreateCommand openplatformOpenapiRecordCustomerMonthBillCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiRecordCustomerMonthBillUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> update(OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand openplatformOpenapiRecordCustomerMonthBillUpdateCommand);

	/**
	 * 统计某一月的数据
	 *
	 * @param year
	 * @param month
	 * @param isIncludeMonthSummary
	 * @param isIncludeDaySummary   只有在 isIncludeMonthSummary=true 时生效
	 */
	public Response statistic(Integer year, Integer month, Boolean isIncludeMonthSummary, Boolean isIncludeDaySummary);
}
