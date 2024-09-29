package com.particle.openplatform.adapter.bill.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformProviderRecordPrdApiDaySummaryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台供应商接口日汇总前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Tag(name = "开放平台供应商接口日汇总wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_provider_record_prd_api_day_summary")
public class OpenplatformProviderRecordPrdApiDaySummaryFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformProviderRecordPrdApiDaySummaryApplicationService iOpenplatformProviderRecordPrdApiDaySummaryApplicationService;


}