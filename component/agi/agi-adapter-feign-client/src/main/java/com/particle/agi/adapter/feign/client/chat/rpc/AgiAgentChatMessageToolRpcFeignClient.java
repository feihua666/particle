package com.particle.agi.adapter.feign.client.chat.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 智能体对话消息工具远程调用
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@FeignClient(name = "${particle.feign-client.name.agi:agi}",path = "/rpc/agi_agent_chat_message_tool")
public interface AgiAgentChatMessageToolRpcFeignClient {









}
