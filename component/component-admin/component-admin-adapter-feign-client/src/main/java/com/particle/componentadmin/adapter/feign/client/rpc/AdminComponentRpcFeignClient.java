package com.particle.componentadmin.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 组件远程调用
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@FeignClient(name = "${particle.feign-client.name.component-admin:component-admin}",path = "/rpc/admin_component")
public interface AdminComponentRpcFeignClient {









}
