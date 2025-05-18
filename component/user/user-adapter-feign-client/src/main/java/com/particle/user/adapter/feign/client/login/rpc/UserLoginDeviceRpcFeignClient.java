package com.particle.user.adapter.feign.client.login.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户登录设备远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@FeignClient(name = "${particle.feign-client.name.userLoginDevice:userLoginDevice}",path = "/rpc/userLoginDevice")
public interface UserLoginDeviceRpcFeignClient {









}
