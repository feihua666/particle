package com.particle.navigation.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 导航提交远程调用
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@FeignClient(name = "${particle.feign-client.name.navigation:navigation}",path = "/rpc/navigation_submit")
public interface NavigationSubmitRpcFeignClient {









}
