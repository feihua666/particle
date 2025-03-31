package com.particle.agi.adapter.feign.client.chat.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 智能体对话远程调用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@FeignClient(name = "${particle.feign-client.name.agi:agi}",path = "/rpc/agi_agent_chat")
public interface AgiAgentChatRpcFeignClient {









}
