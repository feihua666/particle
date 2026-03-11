package com.particle.user.adapter.feign.client.login.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户登录记录远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@FeignClient(name = "${particle.feign-client.user.name:user-start}", contextId = "userLoginRecordRpcFeignClient", url = "${particle.feign-client.user.url:}", path = "/rpc/userLoginRecord")
public interface UserLoginRecordRpcFeignClient {









}
