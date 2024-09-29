package com.particle.openplatform.adapter.feign.client.bill.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台应用开放接口月汇总远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_record_app_openapi_month_summary")
public interface OpenplatformOpenapiRecordAppOpenapiMonthSummaryRpcFeignClient {









}
