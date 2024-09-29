package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
/**
 * <p>
 * 开放平台应用开放接口月汇总 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
public interface IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> create(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> update(OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
}
