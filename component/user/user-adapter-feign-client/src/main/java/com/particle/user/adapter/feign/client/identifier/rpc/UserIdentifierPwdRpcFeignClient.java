package com.particle.user.adapter.feign.client.identifier.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户密码远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.user.name:user-start}", contextId = "userIdentifierPwdRpcFeignClient", url = "${particle.feign-client.user.url:}", path = "/rpc/user-identifier-pwd")
public interface UserIdentifierPwdRpcFeignClient {









}
