package com.particle.crm.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 客户公司远程调用
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@FeignClient(name = "${particle.feign-client.crm.name:crm-start}", contextId = "crmCompanyRpcFeignClient", url = "${particle.feign-client.crm.url:}", path = "/rpc/crm_company")
public interface CrmCompanyRpcFeignClient {









}
