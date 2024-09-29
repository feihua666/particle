package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用开放接口月汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway(OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryGateway = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	}
}
