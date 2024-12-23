package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiDaySummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiDaySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台供应商接口日汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiDaySummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway;
	private IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService;
	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdApiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdApiDaySummaryGateway(OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway) {
		this.openplatformProviderRecordPrdApiDaySummaryGateway = openplatformProviderRecordPrdApiDaySummaryGateway;
	}
	@Autowired
	public void setIOpenplatformProviderRecordPrdApiDaySummaryService(IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService) {
		this.iOpenplatformProviderRecordPrdApiDaySummaryService = iOpenplatformProviderRecordPrdApiDaySummaryService;
	}
}
