package com.particle.user.adapter.feign.client.identifier.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户登录标识远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.userIdentifier:userIdentifier}",path = "/rpc")
public interface UserIdentifierRpcFeignClient {









}
