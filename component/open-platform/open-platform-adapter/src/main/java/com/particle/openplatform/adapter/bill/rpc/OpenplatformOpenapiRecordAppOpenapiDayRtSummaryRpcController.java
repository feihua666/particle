package com.particle.openplatform.adapter.bill.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.adapter.feign.client.bill.rpc.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryRpcFeignClient;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Tag(name = "开放平台应用开放接口日实时汇总远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_record_app_openapi_day_rt_summary")
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiRecordAppOpenapiDayRtSummaryRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryApplicationService;


}
