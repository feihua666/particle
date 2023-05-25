package com.particle.message.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户消息读取状态远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@FeignClient(name = "${particle.feign-client.name.message:message}",path = "/rpc/message_user_state")
public interface MessageUserStateRpcFeignClient {









}
