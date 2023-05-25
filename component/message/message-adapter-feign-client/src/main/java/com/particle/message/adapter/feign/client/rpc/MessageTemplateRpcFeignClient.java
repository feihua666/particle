package com.particle.message.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 消息模板远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@FeignClient(name = "${particle.feign-client.name.message:message}",path = "/rpc/message_template")
public interface MessageTemplateRpcFeignClient {









}
