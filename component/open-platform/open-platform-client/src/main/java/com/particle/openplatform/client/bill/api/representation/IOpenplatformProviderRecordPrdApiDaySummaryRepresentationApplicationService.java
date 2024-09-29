package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;

/**
 * <p>
 * 开放平台供应商接口日汇总 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderRecordPrdApiDaySummaryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformProviderRecordPrdApiDaySummaryQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> queryList(OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand openplatformProviderRecordPrdApiDaySummaryQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderRecordPrdApiDaySummaryPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> pageQuery(OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand openplatformProviderRecordPrdApiDaySummaryPageQueryCommand);

}
