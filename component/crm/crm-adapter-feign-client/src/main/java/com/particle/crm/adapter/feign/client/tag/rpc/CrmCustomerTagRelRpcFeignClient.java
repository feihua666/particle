package com.particle.crm.adapter.feign.client.tag.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户标签关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@FeignClient(name = "${particle.feign-client.name.crm:crm}",path = "/rpc/crm_customer_tag_rel")
public interface CrmCustomerTagRelRpcFeignClient {









}
