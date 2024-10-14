package com.particle.openplatform.adapter.bill.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.bill.api.IOpenplatformOpenapiRecordAppMonthBillApplicationService;
import com.particle.openplatform.adapter.feign.client.bill.rpc.OpenplatformOpenapiRecordAppMonthBillRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用月账单远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Tag(name = "开放平台应用月账单远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_record_app_month_bill")
public class OpenplatformOpenapiRecordAppMonthBillRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiRecordAppMonthBillRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiRecordAppMonthBillApplicationService iOpenplatformOpenapiRecordAppMonthBillApplicationService;


}