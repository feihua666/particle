package com.particle.openplatform.adapter.bill.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiDaySummaryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台供应商接口日汇总前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Tag(name = "开放平台供应商接口日汇总移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_provider_record_prd_api_day_summary")
public class OpenplatformProviderRecordPrdApiDaySummaryFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformProviderRecordPrdApiDaySummaryApplicationService iOpenplatformProviderRecordPrdApiDaySummaryApplicationService;


}