package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用开放接口日汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryGateway(OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDaySummaryGateway = openplatformOpenapiRecordAppOpenapiDaySummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDaySummaryService(IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDaySummaryService = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
	}
}
