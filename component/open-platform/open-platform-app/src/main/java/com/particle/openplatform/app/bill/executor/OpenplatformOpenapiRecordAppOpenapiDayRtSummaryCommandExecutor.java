package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway = openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService(IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
	}
}
