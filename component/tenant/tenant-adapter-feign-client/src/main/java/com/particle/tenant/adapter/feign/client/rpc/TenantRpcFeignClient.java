package com.particle.tenant.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 租户远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant")
public interface TenantRpcFeignClient {









}
