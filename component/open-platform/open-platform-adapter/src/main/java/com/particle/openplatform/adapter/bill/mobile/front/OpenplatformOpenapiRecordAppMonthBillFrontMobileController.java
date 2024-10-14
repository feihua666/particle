package com.particle.openplatform.adapter.bill.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppMonthBillApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用月账单前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Tag(name = "开放平台应用月账单移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_openapi_record_app_month_bill")
public class OpenplatformOpenapiRecordAppMonthBillFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordAppMonthBillApplicationService iOpenplatformOpenapiRecordAppMonthBillApplicationService;


}