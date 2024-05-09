package com.particle.crm.adapter.feign.client.relation.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户与客户关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@FeignClient(name = "${particle.feign-client.name.crm:crm}",path = "/rpc/crm_customer_relation")
public interface CrmCustomerRelationRpcFeignClient {









}
