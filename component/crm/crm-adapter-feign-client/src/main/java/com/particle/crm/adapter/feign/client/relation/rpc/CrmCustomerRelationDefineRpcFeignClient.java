package com.particle.crm.adapter.feign.client.relation.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户关系定义远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@FeignClient(name = "${particle.feign-client.name.crm:crm}",path = "/rpc/crm_customer_relation_define")
public interface CrmCustomerRelationDefineRpcFeignClient {









}
