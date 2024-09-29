package com.particle.openplatform.adapter.bill.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用开放接口日汇总后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Tag(name = "开放平台应用开放接口日汇总wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/openplatform_openapi_record_app_openapi_day_summary")
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiDaySummaryApplicationService;


}