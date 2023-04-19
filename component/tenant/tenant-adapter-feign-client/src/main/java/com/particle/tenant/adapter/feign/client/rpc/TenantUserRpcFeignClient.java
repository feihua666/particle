package com.particle.tenant.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 租户用户远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant_user")
public interface TenantUserRpcFeignClient {









}
