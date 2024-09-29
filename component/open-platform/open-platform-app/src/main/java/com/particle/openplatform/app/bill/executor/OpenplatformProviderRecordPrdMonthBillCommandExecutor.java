package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdMonthBillService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdMonthBillDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台供应商月账单 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdMonthBillCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway;
	private IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService;
	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdMonthBillGateway(OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway) {
		this.openplatformProviderRecordPrdMonthBillGateway = openplatformProviderRecordPrdMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformProviderRecordPrdMonthBillService(IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService) {
		this.iOpenplatformProviderRecordPrdMonthBillService = iOpenplatformProviderRecordPrdMonthBillService;
	}
}
