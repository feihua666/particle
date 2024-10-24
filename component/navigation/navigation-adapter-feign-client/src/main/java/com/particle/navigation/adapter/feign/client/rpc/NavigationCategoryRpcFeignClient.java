package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航分类远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@FeignClient(name = "${particle.feign-client.name.navigation:navigation}",path = "/rpc/navigation_category")
public interface NavigationCategoryRpcFeignClient {









}
