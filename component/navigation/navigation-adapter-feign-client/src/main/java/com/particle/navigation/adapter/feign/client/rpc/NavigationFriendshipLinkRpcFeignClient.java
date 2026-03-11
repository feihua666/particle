package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航友情链接远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@FeignClient(name = "${particle.feign-client.navigation.name:navigation-start}", contextId = "navigationFriendshipLinkRpcFeignClient", url = "${particle.feign-client.navigation.url:}", path = "/rpc/navigation_friendship_link")
public interface NavigationFriendshipLinkRpcFeignClient {









}
