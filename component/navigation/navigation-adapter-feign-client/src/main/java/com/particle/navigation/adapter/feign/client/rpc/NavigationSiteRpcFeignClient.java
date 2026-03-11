package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航网站远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@FeignClient(name = "${particle.feign-client.navigation.name:navigation-start}", contextId = "navigationSiteRpcFeignClient", url = "${particle.feign-client.navigation.url:}", path = "/rpc/navigation_site")
public interface NavigationSiteRpcFeignClient {









}
