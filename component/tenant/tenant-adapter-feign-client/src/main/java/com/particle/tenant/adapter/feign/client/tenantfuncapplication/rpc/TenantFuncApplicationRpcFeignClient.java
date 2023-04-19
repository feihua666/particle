package com.particle.tenant.adapter.feign.client.tenantfuncapplication.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 租户功能应用远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant_func_application")
public interface TenantFuncApplicationRpcFeignClient {









}
