package com.particle.crm.adapter.feign.client.customer.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@FeignClient(name = "${particle.feign-client.crm.name:crm-start}", contextId = "crmCustomerRpcFeignClient", url = "${particle.feign-client.crm.url:}", path = "/rpc/crm_customer")
public interface CrmCustomerRpcFeignClient {









}
