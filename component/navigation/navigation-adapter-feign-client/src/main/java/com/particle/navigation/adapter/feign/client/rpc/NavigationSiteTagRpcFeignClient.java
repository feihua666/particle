package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航网站标签远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@FeignClient(name = "${particle.feign-client.name.navigation:navigation}",path = "/rpc/navigation_site_tag")
public interface NavigationSiteTagRpcFeignClient {









}
