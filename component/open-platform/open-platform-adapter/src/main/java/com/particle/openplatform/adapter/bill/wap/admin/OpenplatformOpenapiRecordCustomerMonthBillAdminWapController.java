package com.particle.openplatform.adapter.bill.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordCustomerMonthBillApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台客户月账单后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Tag(name = "开放平台客户月账单wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/openplatform_openapi_record_customer_month_bill")
public class OpenplatformOpenapiRecordCustomerMonthBillAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordCustomerMonthBillApplicationService iOpenplatformOpenapiRecordCustomerMonthBillApplicationService;


}