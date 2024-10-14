package com.particle.openplatform.adapter.feign.client.bill.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台应用月账单远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_record_app_month_bill")
public interface OpenplatformOpenapiRecordAppMonthBillRpcFeignClient {









}
