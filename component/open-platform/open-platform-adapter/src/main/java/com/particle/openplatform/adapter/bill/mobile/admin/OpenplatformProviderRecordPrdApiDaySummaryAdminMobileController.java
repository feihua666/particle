package com.particle.openplatform.adapter.bill.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiDaySummaryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台供应商接口日汇总后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Tag(name = "开放平台供应商接口日汇总移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_provider_record_prd_api_day_summary")
public class OpenplatformProviderRecordPrdApiDaySummaryAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformProviderRecordPrdApiDaySummaryApplicationService iOpenplatformProviderRecordPrdApiDaySummaryApplicationService;


}