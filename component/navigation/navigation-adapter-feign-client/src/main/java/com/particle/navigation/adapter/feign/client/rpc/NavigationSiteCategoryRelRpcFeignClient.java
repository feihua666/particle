package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航网站分类关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@FeignClient(name = "${particle.feign-client.name.navigation:navigation}",path = "/rpc/navigation_site_category_rel")
public interface NavigationSiteCategoryRelRpcFeignClient {









}
