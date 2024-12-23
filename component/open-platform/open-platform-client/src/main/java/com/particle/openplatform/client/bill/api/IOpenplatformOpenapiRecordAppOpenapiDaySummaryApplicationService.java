package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;

import java.time.LocalDate;

/**
 * <p>
 * 开放平台应用开放接口日汇总 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
public interface IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> create(OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> update(OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);

	/**
	 * 统计某一天的数据
	 * @param localDate
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 * @return
	 */
	Response statistic(LocalDate localDate,Long openplatformAppId);
}
