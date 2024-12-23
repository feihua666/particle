package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
/**
 * <p>
 * 开放平台供应商接口日汇总 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
public interface IOpenplatformProviderRecordPrdApiDaySummaryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformProviderRecordPrdApiDaySummaryCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> create(OpenplatformProviderRecordPrdApiDaySummaryCreateCommand openplatformProviderRecordPrdApiDaySummaryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformProviderRecordPrdApiDaySummaryUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> update(OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
}
