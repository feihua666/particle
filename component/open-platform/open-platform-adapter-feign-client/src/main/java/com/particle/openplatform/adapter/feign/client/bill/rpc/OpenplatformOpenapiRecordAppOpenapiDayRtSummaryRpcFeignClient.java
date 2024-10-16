package com.particle.openplatform.adapter.feign.client.bill.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_record_app_openapi_day_rt_summary")
public interface OpenplatformOpenapiRecordAppOpenapiDayRtSummaryRpcFeignClient {









}
