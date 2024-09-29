package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;

/**
 * <p>
 * 开放平台供应商接口月汇总 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderRecordPrdApiMonthSummaryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformProviderRecordPrdApiMonthSummaryQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> queryList(OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand openplatformProviderRecordPrdApiMonthSummaryQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> pageQuery(OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand);

}
