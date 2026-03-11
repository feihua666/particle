package com.particle.componentadmin.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 组件依赖关系远程调用
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@FeignClient(name = "${particle.feign-client.component-admin.name:component-admin-start}", contextId = "adminComponentDependencyRpcFeignClient", url = "${particle.feign-client.component-admin.url:}", path = "/rpc/admin_component_dependency")
public interface AdminComponentDependencyRpcFeignClient {









}
