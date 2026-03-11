package com.particle.user.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户扩展信息远程调用
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@FeignClient(name = "${particle.feign-client.user.name:user-start}", contextId = "userExtraInfoRpcFeignClient", url = "${particle.feign-client.user.url:}", path = "/rpc/user_extra_info")
public interface UserExtraInfoRpcFeignClient {









}
