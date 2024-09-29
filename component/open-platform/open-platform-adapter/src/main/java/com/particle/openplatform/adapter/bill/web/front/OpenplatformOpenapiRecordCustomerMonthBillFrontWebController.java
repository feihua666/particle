package com.particle.openplatform.adapter.bill.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordCustomerMonthBillApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台客户月账单前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Tag(name = "开放平台客户月账单pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_openapi_record_customer_month_bill")
public class OpenplatformOpenapiRecordCustomerMonthBillFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordCustomerMonthBillApplicationService iOpenplatformOpenapiRecordCustomerMonthBillApplicationService;


}