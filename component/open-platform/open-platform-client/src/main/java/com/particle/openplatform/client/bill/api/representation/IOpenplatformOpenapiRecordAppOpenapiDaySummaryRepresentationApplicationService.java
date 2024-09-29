package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;

/**
 * <p>
 * 开放平台应用开放接口日汇总 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRecordAppOpenapiDaySummaryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> queryList(OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> pageQuery(OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand);

}
