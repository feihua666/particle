package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
public interface IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> create(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> update(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
}
