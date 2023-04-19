package com.particle.tenant.adapter.feign.client.tenantfunc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 租户功能菜单远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant_func")
public interface TenantFuncRpcFeignClient {









}
