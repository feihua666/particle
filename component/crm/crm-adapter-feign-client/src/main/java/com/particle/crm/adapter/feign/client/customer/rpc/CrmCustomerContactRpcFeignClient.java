package com.particle.crm.adapter.feign.client.customer.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户联系方式远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@FeignClient(name = "${particle.feign-client.name.crm:crm}",path = "/rpc/crm_customer_contact")
public interface CrmCustomerContactRpcFeignClient {









}
