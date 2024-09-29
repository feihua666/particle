package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordCustomerMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordCustomerMonthBillService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台客户月账单 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordCustomerMonthBillCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway;
	private IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordCustomerMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordCustomerMonthBillGateway(OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway) {
		this.openplatformOpenapiRecordCustomerMonthBillGateway = openplatformOpenapiRecordCustomerMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordCustomerMonthBillService(IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService) {
		this.iOpenplatformOpenapiRecordCustomerMonthBillService = iOpenplatformOpenapiRecordCustomerMonthBillService;
	}
}
