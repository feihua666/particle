package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
/**
 * <p>
 * 开放平台供应商接口月汇总 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
public interface IOpenplatformProviderRecordPrdApiMonthSummaryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformProviderRecordPrdApiMonthSummaryCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> create(OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand openplatformProviderRecordPrdApiMonthSummaryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformProviderRecordPrdApiMonthSummaryUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> update(OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
}
