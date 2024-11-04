package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航网站静态部署远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@FeignClient(name = "${particle.feign-client.name.navigation:navigation}",path = "/rpc/navigation_static_deploy")
public interface NavigationStaticDeployRpcFeignClient {









}
