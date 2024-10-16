package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> pageQuery(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand);

}
