package com.particle.openplatform.adapter.feign.client.bill.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台供应商接口月汇总远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_provider_record_prd_api_month_summary")
public interface OpenplatformProviderRecordPrdApiMonthSummaryRpcFeignClient {









}
