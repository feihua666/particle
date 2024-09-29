package com.particle.openplatform.adapter.feign.client.bill.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放平台客户月账单远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_openapi_record_customer_month_bill")
public interface OpenplatformOpenapiRecordCustomerMonthBillRpcFeignClient {









}
