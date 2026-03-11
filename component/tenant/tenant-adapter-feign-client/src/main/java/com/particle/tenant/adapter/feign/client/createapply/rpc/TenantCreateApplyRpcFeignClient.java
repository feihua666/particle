package com.particle.tenant.adapter.feign.client.createapply.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 租户创建申请远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@FeignClient(name = "${particle.feign-client.tenant.name:tenant-start}", contextId = "tenantCreateApplyRpcFeignClient", url = "${particle.feign-client.tenant.url:}", path = "/rpc/tenant_create_apply")
public interface TenantCreateApplyRpcFeignClient {









}
