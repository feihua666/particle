package com.particle.openplatform.adapter.bill.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService;
import com.particle.openplatform.adapter.feign.client.bill.rpc.OpenplatformOpenapiRecordAppOpenapiMonthSummaryRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用开放接口月汇总远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Tag(name = "开放平台应用开放接口月汇总远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_record_app_openapi_month_summary")
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiRecordAppOpenapiMonthSummaryRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryApplicationService;


}