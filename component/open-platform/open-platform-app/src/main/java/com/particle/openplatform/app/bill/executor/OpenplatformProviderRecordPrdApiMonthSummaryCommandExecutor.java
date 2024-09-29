package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台供应商接口月汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiMonthSummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway;
	private IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService;
	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdApiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdApiMonthSummaryGateway(OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway) {
		this.openplatformProviderRecordPrdApiMonthSummaryGateway = openplatformProviderRecordPrdApiMonthSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformProviderRecordPrdApiMonthSummaryService(IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService) {
		this.iOpenplatformProviderRecordPrdApiMonthSummaryService = iOpenplatformProviderRecordPrdApiMonthSummaryService;
	}
}
