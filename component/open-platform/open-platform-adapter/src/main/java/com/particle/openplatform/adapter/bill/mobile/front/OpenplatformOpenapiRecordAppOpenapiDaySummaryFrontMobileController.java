package com.particle.openplatform.adapter.bill.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用开放接口日汇总前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Tag(name = "开放平台应用开放接口日汇总移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_openapi_record_app_openapi_day_summary")
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;


}