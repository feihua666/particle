package com.particle.message.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 消息远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@FeignClient(name = "${particle.feign-client.name.message:message}",path = "/rpc/message")
public interface MessageRpcFeignClient {









}
